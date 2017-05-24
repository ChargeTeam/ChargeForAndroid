package com.example.zhangqi.charge.util;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhangqi.charge.bean.AppInfo;
import com.example.zhangqi.charge.constant.Constant;
import com.example.zhangqi.charge.global.AppCenter;
import com.example.zhangqi.charge.logger.Logger;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.adapter.rxjava.HttpException;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

import static com.example.zhangqi.charge.constant.Constant.REGEX_MOBILE_EXACT;


/**
 * Created by zhangqi on 2016/9/26.
 */


public class UtilsCollection {


    public static int getBarHeight(Context context) {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, sbar = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            sbar = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return sbar;
    }

    public static boolean isNumberAndCode(String password){
        String match = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,12}$";
        boolean match1 = UtilsCollection.isMatch(match, password);
        return match1;
    }

    public static void setEditTextReadOnly(TextView view){
        if (view instanceof EditText){
            view.setCursorVisible(false);      //设置输入框中的光标不可见
            view.setFocusable(false);           //无焦点
            view.setFocusableInTouchMode(false);     //触摸时也得不到焦点
        }
    }

    /**
     * 正则：邮箱
     */
    public static final String REGEX_EMAIL = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";

    /**
     * 验证邮箱
     *
     * @param string 待验证文本
     * @return {@code true}: 匹配<br>{@code false}: 不匹配
     */
    public static boolean isEmail(String string) {
        return isMatch(REGEX_EMAIL, string);
    }

    /**
     * 判断身份证格式
     *
     * @param idNum
     * @return
     */
    public static boolean isIdNum(String idNum) {

        // 中国公民身份证格式：长度为15或18位，最后一位可以为字母
        Pattern idNumPattern = Pattern.compile("(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])");

        // 格式验证
        if (!idNumPattern.matcher(idNum).matches())
            return false;

        // 合法性验证

        int year = 0;
        int month = 0;
        int day = 0;

        if (idNum.length() == 15) {

            // 一代身份证

            System.out.println("一代身份证：" + idNum);

            // 提取身份证上的前6位以及出生年月日
            Pattern birthDatePattern = Pattern.compile("\\d{6}(\\d{2})(\\d{2})(\\d{2}).*");

            Matcher birthDateMather = birthDatePattern.matcher(idNum);

            if (birthDateMather.find()) {

                year = Integer.valueOf("19" + birthDateMather.group(1));
                month = Integer.valueOf(birthDateMather.group(2));
                day = Integer.valueOf(birthDateMather.group(3));

            }

        } else if (idNum.length() == 18) {

            // 二代身份证

            System.out.println("二代身份证：" + idNum);

            // 提取身份证上的前6位以及出生年月日
            Pattern birthDatePattern = Pattern.compile("\\d{6}(\\d{4})(\\d{2})(\\d{2}).*");

            Matcher birthDateMather = birthDatePattern.matcher(idNum);

            if (birthDateMather.find()) {

                year = Integer.valueOf(birthDateMather.group(1));
                month = Integer.valueOf(birthDateMather.group(2));
                day = Integer.valueOf(birthDateMather.group(3));
            }

        }

        // 年份判断，100年前至今

        Calendar cal = Calendar.getInstance();

        // 当前年份
        int currentYear = cal.get(Calendar.YEAR);

        if (year <= currentYear - 100 || year > currentYear)
            return false;

        // 月份判断
        if (month < 1 || month > 12)
            return false;

        // 日期判断

        // 计算月份天数

        int dayCount = 31;

        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                dayCount = 31;
                break;
            case 2:
                // 2月份判断是否为闰年
                if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
                    dayCount = 29;
                    break;
                } else {
                    dayCount = 28;
                    break;
                }
            case 4:
            case 6:
            case 9:
            case 11:
                dayCount = 30;
                break;
        }

        if (day < 1 || day > dayCount)
            return false;

        return true;
    }

    public static boolean isFixedPhone(String fixedPhone){
        String reg="(?:(\\(\\+?86\\))(0[0-9]{2,3}\\-?)?([2-9][0-9]{6,7})+(\\-[0-9]{1,4})?)|" +
                "(?:(86-?)?(0[0-9]{2,3}\\-?)?([2-9][0-9]{6,7})+(\\-[0-9]{1,4})?)";
        return Pattern.matches(reg, fixedPhone);
    }
    /**
     * 验证码倒计时
     * @param time
     * @return
     */
    public static Observable<String> countdown(int time) {
        if (time < 0) time = 0;
        final int countTime = time;
        return Observable.interval(0, 1, TimeUnit.SECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map(increaseTime -> (countTime - increaseTime.intValue())+"")
                .take(countTime + 1);
    }


    public static String Sign(String ...sign){
        sign = sort(sign);
        if(sign == null){
            ToastUtil.showShort(AppCenter.sContext,"网络错误,请连接网络");
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0;i < sign.length;i++){
            sb.append(sign[i]);
        }
        sb.append(Constant.SIGN);
        return encodeToMd5(sb.toString());
    }

    public static String Sign(Map<String,String> map , String ...sign){
        List<String> list = new ArrayList<>();

        for (Map.Entry<String, String> stringStringEntry : map.entrySet()) {
            list.add(stringStringEntry.getValue());
        }
        for (int i = 0; i < sign.length; i++) {
            list.add(sign[i]);
        }
        sign = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            sign[i] = list.get(i);
        }
        sign = sort(sign);
        if(sign == null){
            ToastUtil.showShort(AppCenter.sContext,"网络错误,请连接网络");
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0;i < sign.length;i++){
            sb.append(sign[i]);
        }
        sb.append(Constant.SIGN);
        return encodeToMd5(sb.toString());
    }

    public static String[] sort(String ...sign){
        for (int i = 0; i < sign.length; i++) {
            if(sign[i] == null){
                return null;
            }
        }
        Arrays.sort(sign);
        return sign;
    }

    public static String encodeToMd5(String source) {
        if(source==null)
            return null;

        try{
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");

            char hexDigits[] = {
                    '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
                    'e', 'f'};

            byte[] strTemp=source.getBytes("utf-8");
            mdTemp.update(strTemp);

            byte[] md = mdTemp.digest();
            int k = 0;
            int j = md.length;char str[] = new char[j * 2];

            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>>4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    /**
     * string是否匹配regex
     *
     * @param regex  正则表达式字符串
     * @param string 要匹配的字符串
     * @return {@code true}: 匹配<br>{@code false}: 不匹配
     */
    public static boolean isMatch(String regex, String string) {
        return !StringUtils.isEmpty(string) && Pattern.matches(regex, string);
    }
    /**
     * 验证手机号（精确）
     *
     * @param string 待验证文本
     * @return {@code true}: 匹配<br>{@code false}: 不匹配
     */
    public static boolean isMobileExact(String string) {
        return isMatch(REGEX_MOBILE_EXACT, string);
    }

    /**
     * 集中处理 网络请求的错误
     * @param error
     */
    public static void errorUtil(Throwable error){
        StringWriter stringWriter = new StringWriter();
        error.printStackTrace(new PrintWriter(stringWriter));
        Logger.i(stringWriter.toString());

        if(error instanceof SocketTimeoutException){
            Toast.makeText(AppCenter.sContext,"访问服务器超时( ⊙ _ ⊙ )", Toast.LENGTH_SHORT).show();
        }else if(error instanceof NullPointerException){
            Toast.makeText(AppCenter.sContext,"一点小故障/(ㄒoㄒ)/~~", Toast.LENGTH_SHORT).show();
        }else if(error instanceof UnknownHostException){
            Toast.makeText(AppCenter.sContext,"网络错误,请检查网络", Toast.LENGTH_SHORT).show();
        }else if(error instanceof IllegalArgumentException){
            Toast.makeText(AppCenter.sContext,"未知的错误", Toast.LENGTH_SHORT).show();
        }else if(error instanceof HttpException){
            Toast.makeText(AppCenter.sContext,"网络错误,请检查网络", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(AppCenter.sContext,"网络错误,请检查网络", Toast.LENGTH_SHORT).show();
        }
    }
    public static boolean isNumber(String str)
    {
        Pattern pattern= Pattern.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$"); // 判断小数点后2位的数字的正则表达式
        Matcher match=pattern.matcher(str);
        if(match.matches()==false)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    /**
     * 改变状态栏的颜色
     * @param activity
     * @param color
     * @param statusBarAlpha
     */
    public static void setColor(Activity activity, @ColorInt int color, int statusBarAlpha) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            activity.getWindow().setStatusBarColor(calculateStatusColor(color, statusBarAlpha));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();
            int count = decorView.getChildCount();
            if (count > 0 && decorView.getChildAt(count - 1) instanceof StatusBarView) {
                decorView.getChildAt(count - 1).setBackgroundColor(calculateStatusColor(color, statusBarAlpha));
            } else {
                StatusBarView statusView = createStatusBarView(activity, color, statusBarAlpha);
                decorView.addView(statusView);
            }
            setRootView(activity);
        }
    }


    /**
     * 设置根布局参数
     */
    private static void setRootView(Activity activity) {
        ViewGroup rootView = (ViewGroup) ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0);
        rootView.setFitsSystemWindows(true);
        rootView.setClipToPadding(true);
    }
    /**
     * 生成一个和状态栏大小相同的半透明矩形条
     *
     * @param activity 需要设置的activity
     * @param color    状态栏颜色值
     * @param alpha    透明值
     * @return 状态栏矩形条
     */
    private static StatusBarView createStatusBarView(Activity activity, @ColorInt int color, int alpha) {
        // 绘制一个和状态栏一样高的矩形
        StatusBarView statusBarView = new StatusBarView(activity);
        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight(activity));
        statusBarView.setLayoutParams(params);
        statusBarView.setBackgroundColor(calculateStatusColor(color, alpha));
        return statusBarView;
    }
    /**
     * 计算状态栏颜色
     *
     * @param color color值
     * @param alpha alpha值
     * @return 最终的状态栏颜色
     */
    private static int calculateStatusColor(@ColorInt int color, int alpha) {
        float a = 1 - alpha / 255f;
        int red = color >> 16 & 0xff;
        int green = color >> 8 & 0xff;
        int blue = color & 0xff;
        red = (int) (red * a + 0.5);
        green = (int) (green * a + 0.5);
        blue = (int) (blue * a + 0.5);
        return 0xff << 24 | red << 16 | green << 8 | blue;
    }

    /**
     * 获取状态栏高度
     *
     * @param context context
     * @return 状态栏高度
     */
    private static int getStatusBarHeight(Context context) {
        // 获得状态栏高度
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return context.getResources().getDimensionPixelSize(resourceId);
    }


    /**
     * 获取当前App信息
     * <p>AppInfo（名称，图标，包名，版本号，版本Code，是否安装在SD卡，是否是用户程序）</p>
     *
     * @param context 上下文
     * @return 当前应用的AppInfo
     */
    public static AppInfo getAppInfo(Context context) {
        PackageManager pm = context.getPackageManager();
        PackageInfo pi = null;
        try {
            pi = pm.getPackageInfo(context.getApplicationContext().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return pi != null ? getBean(pm, pi) : null;
    }
    /**
     * 得到AppInfo的Bean
     *
     * @param pm 包的管理
     * @param pi 包的信息
     * @return AppInfo类
     */
    private static AppInfo getBean(PackageManager pm, PackageInfo pi) {
        ApplicationInfo ai = pi.applicationInfo;
        String name = ai.loadLabel(pm).toString();
        Drawable icon = ai.loadIcon(pm);
        String packageName = pi.packageName;
        String packagePath = ai.sourceDir;
        String versionName = pi.versionName;
        int versionCode = pi.versionCode;
        boolean isSD = (ApplicationInfo.FLAG_SYSTEM & ai.flags) != ApplicationInfo.FLAG_SYSTEM;
        boolean isUser = (ApplicationInfo.FLAG_SYSTEM & ai.flags) != ApplicationInfo.FLAG_SYSTEM;
        return new AppInfo(name, icon, packageName, packagePath, versionName, versionCode, isSD, isUser);
    }
}

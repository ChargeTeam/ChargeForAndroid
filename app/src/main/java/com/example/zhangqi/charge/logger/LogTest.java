package com.example.zhangqi.charge.logger;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by zhangqi on 2016/11/8.
 */

public class LogTest extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.log);
//        try {
//            Process process = Runtime.getRuntime().exec("logcat -d");
//            BufferedReader bufferedReader = new BufferedReader(
//                    new InputStreamReader(process.getInputStream()));
//            File file = new File(getFilesDir()+ File.separator+"log.txt");
//            BufferedWriter bos = new BufferedWriter(new FileWriter(file));
//
//            StringBuilder log=new StringBuilder();
//            String line;
//            while ((line = bufferedReader.readLine()) != null) {
//                log.append(line);
//                bos.write(line);
//                bos.newLine();
//            }
////可以写到TextView中
//            TextView tv = (TextView)findViewById(R.id.log);
//            tv.setText(log.toString());
//
//
//
////也可以写到app指定的文件中去
////            String logPath = Environment.getExternalStorageDirectory().getPath() + "/logcat.log";
////            LogUtils.f(logPath, log.toString(), false);
//        } catch (IOException e) {
//        }
    }
}
package com.example.zhangqi.charge.api;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

import static android.R.attr.data;

/**
 * Created by zhangqi on 2016/11/2.
 */

public final class CustomConverter extends Converter.Factory{
    public static CustomConverter create() {
        return create(new Gson());
    }

    public static CustomConverter create(Gson gson) {
        return new CustomConverter(gson);
    }

    private final Gson gson;

    private CustomConverter(Gson gson) {
        if (gson == null) throw new NullPointerException("gson == null");
        this.gson = gson;
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new DecodeResponseBodyConverter<>(adapter);
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new DecodeRequestBodyConverter<>(gson, adapter);
    }

    public class DecodeResponseBodyConverter<T> implements Converter<ResponseBody, T> {
        private final TypeAdapter<T> adapter;

        DecodeResponseBodyConverter(TypeAdapter<T> adapter) {
            this.adapter = adapter;
        }

        @Override
        public T convert(ResponseBody value) throws IOException {
            String decode = null;
            try {
//                decode = Des3.decode(value.string());
            } catch (Exception e) {
                e.printStackTrace();
            }
            T t = adapter.fromJson(decode);
            return t;
        }
    }

    public class DecodeRequestBodyConverter<T> implements Converter<T, RequestBody> {
        private final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
        private final Charset UTF_8 = Charset.forName("UTF-8");

        private final Gson gson;
        private final TypeAdapter<T> adapter;
        DecodeRequestBodyConverter(Gson gson, TypeAdapter<T> adapter){
            this.gson = gson;
            this.adapter = adapter;
        }

        @Override
        public RequestBody convert(T value) throws IOException {
//            Buffer buffer = new Buffer();
//            Writer writer = new OutputStreamWriter(buffer.outputStream(),UTF_8);
//            JsonWriter jsonWriter = gson.newJsonWriter(writer);
//            adapter.write(jsonWriter,value);
//            jsonWriter.flush();
//            return RequestBody.create(MEDIA_TYPE,buffer.readByteString());
            //加密


            String postBody = gson.toJson(data); //对象转化成json
            try {
//                postBody = Des3.decode(postBody);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return RequestBody.create(MEDIA_TYPE, postBody);

        }
    }
}

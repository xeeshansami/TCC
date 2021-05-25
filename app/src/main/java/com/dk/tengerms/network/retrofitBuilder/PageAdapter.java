package com.dk.tengerms.network.retrofitBuilder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.HttpUrl;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Retrofit;


final class PageAdapter implements Converter<ResponseBody, String> {
    static final Converter.Factory FACTORY = new Converter.Factory() {
        @Override
        public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
            if (type == String.class) return new PageAdapter();
            return null;
        }
    };

    @Override
    public String convert(ResponseBody responseBody) throws IOException {
        Document document = Jsoup.parse(responseBody.string());
        Element value = document.select("script").get(1);
        String content = value.html();
        return content;
    }
}

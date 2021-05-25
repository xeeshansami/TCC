package com.dk.tengerms.network.apiInterface;


import com.dk.tengerms.network.retrofitBuilder.Page;

import okhttp3.HttpUrl;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface PageService {
    @GET
    Call<Page> get(@Url HttpUrl url);
}
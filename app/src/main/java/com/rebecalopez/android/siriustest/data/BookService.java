package com.rebecalopez.android.siriustest.data;

import android.util.Log;

import com.rebecalopez.android.siriustest.data.entities.BookInfo;

import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BookService {

    String BASE_URL = "https://www.googleapis.com/";

    @GET("/books/v1/volumes")
    Single<BookInfo> getForecast(@Query("q") String bookTitle/*, @Query("key") String api_key*/);

    class Factory{
        private Retrofit createRetrofit(){

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();

            Log.d("BookService","url: "+retrofit.baseUrl());
            return retrofit;
        }

        public BookService create(){return createRetrofit().create(BookService.class);}
    }
}

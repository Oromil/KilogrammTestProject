package com.example.oromil.kilogrammtestproject.network;

import com.example.oromil.kilogrammtestproject.network.model.MyResponse;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Oromil on 05.04.2017.
 */

public class ServerManager {

    private String BASE_URL = "http://tomcat.kilograpp.com";
    private Request request;

    public ServerManager(){
        createRetrofit();
    }

    private void createRetrofit(){

        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .build();

        request = retrofit.create(Request.class);
    }

    public void getData(Subscriber<List<MyResponse>> subscriber){
        request.getSongs()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(subscriber);
    }
}

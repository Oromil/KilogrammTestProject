package com.example.oromil.kilogrammtestproject.network;

import com.example.oromil.kilogrammtestproject.network.model.MyResponse;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Oromil on 05.04.2017.
 */

public interface Request {

    @GET("/songs/api/songs")
    Observable<List<MyResponse>> getSongs();
}

package com.vladikinc.sunrisesunset.api;

import com.vladikinc.sunrisesunset.model.SunriceSunsetResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("/json")
    Single<SunriceSunsetResponse> getResponse(
            @Query("lat") float lat,
            @Query("lng") float lng,
            @Query("formatted") int formatted
    );
}

package com.example.ha_test;

import android.app.DownloadManager;

import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface HA_API {
    @Headers("Authorization: Bearer {accessToken}")
    @POST("/api/services/switch.pi_test_led/turn_on")
    Call<Void> turnOnSwitch(@Path("accessToken") String accessToken, @Body RequestBody requestBody);

    @Headers("Authorization: Bearer {accessToken}")
    @POST("/api/services/switch.pi_test_led/turn_on")
    Call<Void> turnOffSwitch(@Path("accessToken") String accessToken, @Body RequestBody requestBody);
}

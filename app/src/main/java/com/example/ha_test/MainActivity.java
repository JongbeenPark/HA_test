package com.example.ha_test;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;

import java.security.Provider;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    Button btnOn;
    Button btnOff;
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String ACCESS_TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiIyNDgxODExZGYxYTE0NjZmYWViOTRkYjkxZTc2MTM3OSIsImlhdCI6MTY4NTI3MDI4MSwiZXhwIjoyMDAwNjMwMjgxfQ.fS10KcebQcHR9sR4pzcPtOqXk5g5MbTrS1w2YzYaALM";
    private HA_API service;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnOn = findViewById(R.id.btnOn);
        btnOff = findViewById(R.id.btnOff);

        service = ApiClient.getClient().create(HA_API.class);

        btnOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, Object> payload = new HashMap<>();
                payload.put("entity_id","pi_test_led");

                Gson gson = new Gson();
                String jsonPayload = gson.toJson(payload);
                RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonPayload);

                Call<Void> call = service.turnOnSwitch(ACCESS_TOKEN,requestBody);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"스위치 켜기 성공",Toast.LENGTH_LONG);
                        } else {
                            Toast.makeText(getApplicationContext(),"에러",Toast.LENGTH_LONG);
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"실패",Toast.LENGTH_LONG);
                    }
                });
            }
        });

        btnOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, Object> payload = new HashMap<>();
                payload.put("entity_id","pi_test_led");

                Gson gson = new Gson();
                String jsonPayload = gson.toJson(payload);
                RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonPayload);

                Call<Void> call = service.turnOffSwitch(ACCESS_TOKEN,requestBody);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"스위치 끄기 성공",Toast.LENGTH_LONG);
                        } else {
                            Toast.makeText(getApplicationContext(),"에러",Toast.LENGTH_LONG);
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"실패",Toast.LENGTH_LONG);
                    }
                });
            }
        });




    }
}
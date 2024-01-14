package com.SelfParkingSystems.SelfPark;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

import okhttp3.OkHttpClient;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity{

    OkHttpClient client;
    TextView tvMessage;
    Button btnMotorTest, btnIntegrationTest;

    ApiCaller apiCaller;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        client = new OkHttpClient();
        tvMessage = findViewById(R.id.tvMessage);
        btnIntegrationTest = findViewById(R.id.btnIntegrationTest);
        btnMotorTest = findViewById(R.id.btnMotorTest);

//        btnGet.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                test();
//            }
//        });
        apiCaller = new ApiCaller();
        btnMotorTest.setOnClickListener(v -> {
            CompletableFuture<Response> responseFuture = apiCaller.get("http://192.168.1.100:8080/test/motor/1");
            responseFuture.thenAcceptAsync(response -> {
                runOnUiThread(() -> {
                    try {
                        String responseBody = response.body().string();
                        tvMessage.setText(responseBody);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }).exceptionally(throwable -> {
                throwable.printStackTrace();
                return null;
            });
        });

        btnIntegrationTest.setOnClickListener(v -> {
            CompletableFuture<Response> responseFuture = apiCaller.get("http://192.168.1.100:8080/test/integration");
            responseFuture.thenAcceptAsync(response -> {
                runOnUiThread(() -> {
                    try {
                        String responseBody = response.body().string();
                        tvMessage.setText(responseBody);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }).exceptionally(throwable -> {
                throwable.printStackTrace();
                return null;
            });
        });
    }

//    public void test(){
//        Request request = new Request
//                .Builder()
//                .url("http://192.168.1.102:8080/test/integration")
//                .build();
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(@NonNull Call call, @NonNull IOException e) {
//                e.printStackTrace();
//            }
//
//            @Override
//            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        try {
//                            tvMessage.setText(response.body().string());
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                });
//            }
//        });
//    }
}
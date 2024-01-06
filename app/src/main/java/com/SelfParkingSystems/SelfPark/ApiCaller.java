package com.SelfParkingSystems.SelfPark;

import androidx.annotation.NonNull;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

import okhttp3.*;


public class ApiCaller {
    private OkHttpClient client;

    public ApiCaller() {
        this.client = new OkHttpClient();
    }

    public CompletableFuture<Response> get(String url) {
        CompletableFuture<Response> future = new CompletableFuture<>();

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                future.completeExceptionally(e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                future.complete(response);
            }
        });

        return future;
    }

    // Buraya diğer HTTP metotlarını (POST, DELETE, UPDATE) ekleyebilirsin.
    // İhtiyacına göre gerekli metotları oluşturabilirsin.
}
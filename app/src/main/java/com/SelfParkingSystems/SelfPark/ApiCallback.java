package com.SelfParkingSystems.SelfPark;

import okhttp3.Response;

public interface ApiCallback {
    void onResponse(Response response);
    void onError(String errorMessage);
}

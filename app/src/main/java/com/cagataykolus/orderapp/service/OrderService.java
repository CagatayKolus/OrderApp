package com.cagataykolus.orderapp.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OrderService {
    private Retrofit retrofit = null;

    public OrderAPI getAPI() {
        String BASE_URL = "http://kariyertechchallenge.mockable.io/";
        if (retrofit == null) {
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit.create(OrderAPI.class);
    }
}
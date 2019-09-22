package com.cagataykolus.orderapp.service;

import com.cagataykolus.orderapp.model.Order;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * This class represents the Orders API.
 */
public interface OrderAPI {
    @GET
    Call<List<Order>> getOrders(@Url String anEmptyString);
}

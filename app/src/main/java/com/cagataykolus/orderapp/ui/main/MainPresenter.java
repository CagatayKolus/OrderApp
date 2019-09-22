package com.cagataykolus.orderapp.ui.main;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.cagataykolus.orderapp.R;
import com.cagataykolus.orderapp.model.Order;
import com.cagataykolus.orderapp.util.AppConfig;
import com.cagataykolus.orderapp.service.OrderService;
import com.cagataykolus.orderapp.ui.main.MainView.View;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter implements MainView.Presenter<View> {

    private MainView.View view;
    private OrderService orderService;

    @Override
    public void attachView(MainView.View view) {
        this.view = view;

        if (this.orderService == null) {
            this.orderService = new OrderService();
        }
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public boolean isViewAttached() {
        return this.view != null;
    }

    @Override
    public void doExit(final Integer delay) {
        new AlertDialog.Builder(view.getContext())
                .setTitle(view.getContext().getString(R.string.dialog_exit_title))
                .setMessage(view.getContext().getString(R.string.dialog_exit_message))
                .setPositiveButton(view.getContext().getString(R.string.dialog_exit_positive), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AppConfig.init(view.getContext());
                        AppConfig.setRememberStatus(false);

                        Runnable r = new Runnable() {
                            @Override
                            public void run() {
                                ((Activity) view.getContext()).finish();
                                ((Activity) view.getContext()).moveTaskToBack(true);
                                System.exit(0);
                            }
                        };
                        new Handler().postDelayed(r, delay);
                    }

                })
                .setNegativeButton(view.getContext().getString(R.string.dialog_exit_negative), null)
                .show();
    }

    @Override
    public void getOrders() {
        orderService
                .getAPI()
                .getOrders("")
                .enqueue(new Callback<List<Order>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<Order>> call, @NonNull Response<List<Order>> response) {
                        List<Order> products = response.body();
                        view.ordersResponseReady(products);
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<Order>> call, @NonNull  Throwable t) {
                        try {
                            throw new InterruptedException("Something went wrong!");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}
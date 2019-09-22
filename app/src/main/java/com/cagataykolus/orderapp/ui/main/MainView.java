package com.cagataykolus.orderapp.ui.main;

import com.cagataykolus.orderapp.model.Order;
import com.cagataykolus.orderapp.util.BasePresenter;
import com.cagataykolus.orderapp.util.BaseView;

import java.util.List;

public interface MainView {
    interface View extends BaseView {
        void ordersResponseReady(List<Order> orders);
    }

    interface Presenter<V extends BaseView> extends BasePresenter<MainView.View> {
        void doExit(Integer delay);

        void getOrders();
    }
}
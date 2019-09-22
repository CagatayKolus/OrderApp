package com.cagataykolus.orderapp.util;

public interface BasePresenter<V> {
    void attachView(V view);

    void detachView();

    boolean isViewAttached();
}
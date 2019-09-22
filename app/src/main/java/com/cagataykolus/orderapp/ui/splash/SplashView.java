package com.cagataykolus.orderapp.ui.splash;

import com.cagataykolus.orderapp.util.BasePresenter;
import com.cagataykolus.orderapp.util.BaseView;

public interface SplashView {
    interface View extends BaseView {
        void showLoginActivity();

        void showMainActivity();
    }

    interface Presenter<V extends BaseView> extends BasePresenter<View> {
        void doSplash(Integer delay);
    }
}

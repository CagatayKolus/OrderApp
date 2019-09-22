package com.cagataykolus.orderapp.ui.splash;

import android.os.Handler;

import com.cagataykolus.orderapp.util.AppConfig;

public class SplashPresenter implements SplashView.Presenter<SplashView.View> {

    private SplashView.View view;

    @Override
    public void attachView(SplashView.View view) {
        this.view = view;
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
    public void doSplash(Integer delay) {

        // If remember status true, skip the login page
        // If remember status false, open the login page
        AppConfig.init(view.getContext());
        final boolean isRememberActive = AppConfig.getRememberStatus();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                if(isRememberActive){
                    view.showMainActivity();
                } else{
                    view.showLoginActivity();
                }
            }
        };
        new Handler().postDelayed(r,delay);
    }
}

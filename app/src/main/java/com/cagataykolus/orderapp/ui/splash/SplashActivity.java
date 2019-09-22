package com.cagataykolus.orderapp.ui.splash;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.cagataykolus.orderapp.R;
import com.cagataykolus.orderapp.ui.login.LoginActivity;
import com.cagataykolus.orderapp.ui.main.MainActivity;

public class SplashActivity extends AppCompatActivity implements SplashView.View {

    private SplashPresenter splashPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        splashPresenter = new SplashPresenter();
        splashPresenter.attachView(this);
        splashPresenter.doSplash(1000);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showLoginActivity() {
        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void showMainActivity() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        splashPresenter.detachView();
    }
}

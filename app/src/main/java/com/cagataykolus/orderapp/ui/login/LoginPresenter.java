package com.cagataykolus.orderapp.ui.login;

import com.cagataykolus.orderapp.R;
import com.cagataykolus.orderapp.util.AppConfig;
import com.cagataykolus.orderapp.model.User;
import com.cagataykolus.orderapp.ui.login.LoginView.View;
import com.cagataykolus.orderapp.util.Util;

public class LoginPresenter implements LoginView.Presenter<View> {

    private View view;

    @Override
    public void attachView(View view) {
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
    public void doLogin(String username, String password, boolean remember) {
        if (isViewAttached()) {
            if (username != null && password != null) {
                if (username.equalsIgnoreCase(Util.TEST_USERNAME) && password.equalsIgnoreCase(Util.TEST_PASSWORD)) {
                    // If remember status true, set remember status as true
                    if (remember){
                        AppConfig.init(view.getContext());
                        AppConfig.setRememberStatus(true);
                    }
                    view.onSuccess(new User(username));
                    view.startMainActivity();
                } else if (username.isEmpty() || password.isEmpty()) {
                    view.onFailed(view.getContext().getString(R.string.login_fields_empty));
                } else {
                    view.onFailed(view.getContext().getString(R.string.login_failed));
                }
            } else {
                view.onFailed(view.getContext().getString(R.string.login_failed));
            }
        }
    }
}
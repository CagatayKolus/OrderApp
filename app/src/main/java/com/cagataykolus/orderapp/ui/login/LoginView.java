package com.cagataykolus.orderapp.ui.login;

import com.cagataykolus.orderapp.model.User;
import com.cagataykolus.orderapp.util.BasePresenter;
import com.cagataykolus.orderapp.util.BaseView;

public interface LoginView {

    interface View extends BaseView {
        void startMainActivity();

        void onSuccess(User user);

        void onFailed(String msg);
    }

    interface Presenter<V extends BaseView> extends BasePresenter<View> {
        void doLogin(String username, String password, boolean remember);
    }
}

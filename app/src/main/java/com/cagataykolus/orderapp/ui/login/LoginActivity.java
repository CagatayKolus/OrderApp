package com.cagataykolus.orderapp.ui.login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cagataykolus.orderapp.R;
import com.cagataykolus.orderapp.model.User;
import com.cagataykolus.orderapp.ui.main.MainActivity;
import com.cagataykolus.orderapp.util.Util;

public class LoginActivity extends AppCompatActivity implements LoginView.View, View.OnClickListener {

    private EditText etUsername;
    private EditText etPassword;
    private Switch swRemember;
    private Button btnLogin;
    private ImageButton ibFill;
    private ProgressDialog progressDialog;

    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        swRemember = findViewById(R.id.sw_remember);
        btnLogin = findViewById(R.id.btn_login);
        ibFill = findViewById(R.id.ib_fill);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.loading));

        btnLogin.setOnClickListener(this);
        ibFill.setOnClickListener(this);
    }

    public void loginAction() {
        loginPresenter = new LoginPresenter();
        loginPresenter.attachView(this);
        loginPresenter.doLogin(etUsername.getText().toString(), etPassword.getText().toString(), swRemember.isChecked());
        clearFields();
    }

    public void clearFields() {
        etUsername.setText("");
        etPassword.setText("");
        swRemember.setChecked(false);
    }

    public void fillFields() {
        etUsername.setText(Util.TEST_USERNAME);
        etPassword.setText(Util.TEST_PASSWORD);
        swRemember.setChecked(false);
    }

    public void showSuccessMessage(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public void showErrorMessage(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void startMainActivity() {
        finishAffinity();
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public void onSuccess(User user) {
        showSuccessMessage(this, getContext().getString(R.string.login_successful) + " " + user.getUsername());
    }

    @Override
    public void onFailed(String msg) {
        showErrorMessage(this, msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginPresenter.detachView();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                loginAction();
                break;
            case R.id.ib_fill:
                fillFields();
                break;
        }
    }
}
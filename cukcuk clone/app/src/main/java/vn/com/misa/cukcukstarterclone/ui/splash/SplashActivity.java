package vn.com.misa.cukcukstarterclone.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import vn.com.misa.cukcukstarterclone.R;
import vn.com.misa.cukcukstarterclone.ui.login.LoginActivity;
import vn.com.misa.cukcukstarterclone.ui.main.MainActivity;
import vn.com.misa.cukcukstarterclone.utils.SharedPreferenceHelper;

import static vn.com.misa.cukcukstarterclone.utils.Constants.DELAY_TIME;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        checkLogin();
    }

    private void checkLogin() {
        boolean isLoggedIn = SharedPreferenceHelper.getSharedPreferenceBoolean(this, SharedPreferenceHelper.KEY_LOGIN, false);
        if (isLoggedIn) {
            goToMain();
        } else {
            login();
        }
    }

    public void login() {
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            this.finish();
        }, DELAY_TIME);
    }

    public void goToMain() {
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            this.finish();
        }, DELAY_TIME);
    }
}

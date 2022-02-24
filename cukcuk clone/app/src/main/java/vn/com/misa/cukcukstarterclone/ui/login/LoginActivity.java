package vn.com.misa.cukcukstarterclone.ui.login;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentTransaction;

import vn.com.misa.cukcukstarterclone.R;
import vn.com.misa.cukcukstarterclone.base.BaseActivity;
import vn.com.misa.cukcukstarterclone.data.repository.IUserRepository;
import vn.com.misa.cukcukstarterclone.di.Injector;
import vn.com.misa.cukcukstarterclone.ui.login.register.RegisterFragment;
import vn.com.misa.cukcukstarterclone.ui.main.MainActivity;
import vn.com.misa.cukcukstarterclone.ui.setup.SetupActivity;
import vn.com.misa.cukcukstarterclone.utils.SharedPreferenceHelper;

public class LoginActivity extends BaseActivity<LoginContract.View, LoginPresenter>
        implements LoginContract.View, RegisterFragment.RegisterFragmentCallback {

    private EditText edtUsername;
    private EditText edtPassword;
    private ImageView ivClearUsername;
    private ImageView ivClearPassword;

    private LoginPresenter mPresenter;

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void bindViews() {
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        ivClearUsername = findViewById(R.id.ivClearUsername);
        ivClearPassword = findViewById(R.id.ivClearPassword);
        Button btnLogin = findViewById(R.id.btnLogin);
        TextView tvForgetPassword = findViewById(R.id.tvForgetPassword);
        TextView tvRegister = findViewById(R.id.tvRegister);
        ImageView ivInfo = findViewById(R.id.ivInfo);

        edtUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 0) {
                    ivClearUsername.setVisibility(View.VISIBLE);
                } else {
                    ivClearUsername.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        edtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 0) {
                    ivClearPassword.setVisibility(View.VISIBLE);
                } else {
                    ivClearPassword.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        ivClearUsername.setOnClickListener(view -> edtUsername.setText(""));
        ivClearPassword.setOnClickListener(view -> edtPassword.setText(""));

        btnLogin.setOnClickListener(view -> checkValid());
        tvForgetPassword.setOnClickListener(view -> forgetPassword());
        tvRegister.setOnClickListener(view -> showRegisterFragment());
        ivInfo.setOnClickListener(view -> showInfo());

        initPresenter();
    }

    private void initPresenter() {
        IUserRepository userRepository = Injector.getUserRepository();

        mPresenter = new LoginPresenter(userRepository);
        mPresenter.attach(this);
    }

    private void checkValid() {
        if (edtUsername.getText().toString().isEmpty()) {
            showWarningMessage(R.string.email_must_not_be_empty);
        } else if (edtPassword.getText().toString().isEmpty()) {
            showWarningMessage(R.string.password_must_not_be_empty);
        } else {
            mPresenter.login(edtUsername.getText().toString(), edtPassword.getText().toString());
        }
    }

    private void showRegisterFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.container, new RegisterFragment(this), RegisterFragment.TAG).addToBackStack(RegisterFragment.TAG);
        fragmentTransaction.commit();
    }

    private void forgetPassword() {

    }

    private void showInfo() {

    }

    @Override
    protected void initData() {
    }

    @Override
    public void loggedInSuccess() {
        showNormalMessage("Login Success");
        checkInitDatabase();
    }

    @Override
    public void registerSuccess() {
        checkInitDatabase();
    }

    private void checkInitDatabase() {
        SharedPreferenceHelper.setSharedPreferenceBoolean(this, SharedPreferenceHelper.KEY_LOGIN, true);

        boolean isInitDatabase = SharedPreferenceHelper.getSharedPreferenceBoolean(this, SharedPreferenceHelper.KEY_INIT_DATABASE, false);
        if (isInitDatabase) {
            goToMain();
        } else {
            setUpMenu();
        }
    }

    private void setUpMenu() {
        startActivity(new Intent(this, SetupActivity.class));
        this.finish();
    }

    private void goToMain() {
        startActivity(new Intent(this, MainActivity.class));
        this.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mPresenter.detach();
    }
}

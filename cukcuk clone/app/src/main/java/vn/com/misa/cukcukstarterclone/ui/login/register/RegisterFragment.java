package vn.com.misa.cukcukstarterclone.ui.login.register;

import android.view.View;
import android.widget.Button;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.textfield.TextInputEditText;

import vn.com.misa.cukcukstarterclone.R;
import vn.com.misa.cukcukstarterclone.base.BaseFragment;
import vn.com.misa.cukcukstarterclone.data.repository.IUserRepository;
import vn.com.misa.cukcukstarterclone.di.Injector;

public class RegisterFragment extends BaseFragment<RegisterContract.View, RegisterPresenter>
        implements RegisterContract.View {

    public static final String TAG = "RegisterFragment";

    private TextInputEditText edtEmail;
    private TextInputEditText edtPhoneNumber;
    private TextInputEditText edtPassword;
    private TextInputEditText edtPasswordConfirm;

    private RegisterPresenter mPresenter;

    private final RegisterFragmentCallback callback;

    public RegisterFragment(RegisterFragmentCallback callback) {
        this.callback = callback;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_register;
    }

    @Override
    protected void bindViews(View view) {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(v ->
                requireActivity().getSupportFragmentManager().popBackStack(TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        );

        edtEmail = view.findViewById(R.id.edtEmail);
        edtPhoneNumber = view.findViewById(R.id.edtPhoneNumber);
        edtPassword = view.findViewById(R.id.edtPassword);
        edtPasswordConfirm = view.findViewById(R.id.edtPasswordConfirm);
        Button btnRegister = view.findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(v -> validate());

        iniPresenter();
    }

    private void iniPresenter() {
        IUserRepository userRepository = Injector.getUserRepository();

        mPresenter = new RegisterPresenter(userRepository);
        mPresenter.attach(this);
    }

    private void validate() {
        if (edtEmail.getText().toString().isEmpty()) {
            showWarningMessage(R.string.email_must_not_be_empty);
        } else if (edtPassword.getText().toString().isEmpty() || edtPasswordConfirm.getText().toString().isEmpty()) {
            showWarningMessage(R.string.password_must_not_be_empty);
        } else if (!edtPassword.getText().toString().equals(edtPasswordConfirm.getText().toString())) {
            showWarningMessage(R.string.password_confirm_wrong);
        } else {
            mPresenter.register(edtEmail.getText().toString(), edtPassword.getText().toString());
        }
    }

    @Override
    protected void initData() {
    }

    @Override
    public void registerSuccess() {
        showNormalMessage("Register Success");
        callback.registerSuccess();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        mPresenter.detach();
    }

    public interface RegisterFragmentCallback {
        void registerSuccess();
    }
}

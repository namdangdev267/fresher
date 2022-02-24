package vn.com.misa.cukcukstarterclone.base;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;
import androidx.appcompat.app.AppCompatActivity;

import vn.com.misa.cukcukstarterclone.R;

/**
 * @created_by KhanhNQ on 07-Jan-21.
 */
abstract public class BaseActivity<V extends BaseContract.View, P extends BaseContract.Presenter<V>> extends AppCompatActivity implements BaseContract.View {

    protected abstract @LayoutRes
    int getLayout();

    protected @StyleRes
    int getStyle() {
        return R.style.Theme_MISACukcukStarterClone;
    }

    protected abstract void bindViews();

    protected abstract void initData();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(getStyle());
        super.onCreate(savedInstanceState);
        setContentView(getLayout());

        bindViews();
        initData();
    }

    @Override
    public void showErrorMessage(String msg) {
        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP | Gravity.FILL_HORIZONTAL, 0, 0);
        View toastView = toast.getView();
        toastView.setBackgroundResource(R.drawable.bg_default_toast_error);
        TextView v = toastView.findViewById(android.R.id.message);
        v.setTextColor(Color.WHITE);
        toast.show();
    }

    @Override
    public void showErrorMessage(int resId) {
        Toast toast = Toast.makeText(this, getResources().getString(resId), Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP | Gravity.FILL_HORIZONTAL, 0, 0);
        View toastView = toast.getView();
        toastView.setBackgroundResource(R.drawable.bg_default_toast_error);
        TextView v = toastView.findViewById(android.R.id.message);
        v.setTextColor(Color.WHITE);
        toast.show();
    }

    @Override
    public void showNormalMessage(String msg) {
        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP | Gravity.FILL_HORIZONTAL, 0, 0);
        View toastView = toast.getView();
        toastView.setBackgroundResource(R.drawable.bg_default_toast);
        TextView v = toastView.findViewById(android.R.id.message);
        v.setTextColor(Color.WHITE);
        toast.show();
    }

    @Override
    public void showNormalMessage(int resId) {
        Toast toast = Toast.makeText(this, getResources().getString(resId), Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP | Gravity.FILL_HORIZONTAL, 0, 0);
        View toastView = toast.getView();
        toastView.setBackgroundResource(R.drawable.bg_default_toast);
        TextView v = toastView.findViewById(android.R.id.message);
        v.setTextColor(Color.WHITE);
        toast.show();
    }

    @Override
    public void showWarningMessage(String msg) {
        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP | Gravity.FILL_HORIZONTAL, 0, 0);
        View toastView = toast.getView();
        toastView.setBackgroundResource(R.drawable.bg_default_toast_warning);
        TextView v = toastView.findViewById(android.R.id.message);
        v.setTextColor(Color.WHITE);
        toast.show();
    }

    @Override
    public void showWarningMessage(int resId) {
        Toast toast = Toast.makeText(this, getResources().getString(resId), Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP | Gravity.FILL_HORIZONTAL, 0, 0);
        View toastView = toast.getView();
        toastView.setBackgroundResource(R.drawable.bg_default_toast_warning);
        TextView v = toastView.findViewById(android.R.id.message);
        v.setTextColor(Color.WHITE);
        toast.show();
    }
}

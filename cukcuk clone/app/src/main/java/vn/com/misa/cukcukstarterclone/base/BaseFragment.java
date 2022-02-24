package vn.com.misa.cukcukstarterclone.base;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import vn.com.misa.cukcukstarterclone.R;

/**
 * @created_by KhanhNQ on 07-Jan-21.
 */
public abstract class BaseFragment<V extends BaseContract.View, P extends BaseContract.Presenter<V>> extends Fragment implements BaseContract.View {

    protected abstract @LayoutRes
    int getLayout();

    protected abstract void bindViews(View view);

    protected abstract void initData();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(getLayout(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bindViews(view);
        initData();
    }

    @Override
    public void showErrorMessage(String msg) {
        if (null != getContext()) {
            Toast toast = Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP | Gravity.FILL_HORIZONTAL, 0, 0);
            View toastView = toast.getView();
            toastView.setBackgroundResource(R.drawable.bg_default_toast_error);
            TextView v = toastView.findViewById(android.R.id.message);
            v.setTextColor(Color.WHITE);
            toast.show();
        }
    }

    @Override
    public void showErrorMessage(int resId) {
        if (null != getContext()) {
            Toast toast = Toast.makeText(getContext(), getResources().getString(resId), Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP | Gravity.FILL_HORIZONTAL, 0, 0);
            View toastView = toast.getView();
            toastView.setBackgroundResource(R.drawable.bg_default_toast_error);
            TextView v = toastView.findViewById(android.R.id.message);
            v.setTextColor(Color.WHITE);
            toast.show();
        }
    }

    @Override
    public void showNormalMessage(String msg) {
        Toast toast = Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP | Gravity.FILL_HORIZONTAL, 0, 0);
        View toastView = toast.getView();
        toastView.setBackgroundResource(R.drawable.bg_default_toast);
        TextView v = toastView.findViewById(android.R.id.message);
        v.setTextColor(Color.WHITE);
        toast.show();
    }

    @Override
    public void showNormalMessage(int resId) {
        Toast toast = Toast.makeText(getContext(), getResources().getString(resId), Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP | Gravity.FILL_HORIZONTAL, 0, 0);
        View toastView = toast.getView();
        toastView.setBackgroundResource(R.drawable.bg_default_toast);
        TextView v = toastView.findViewById(android.R.id.message);
        v.setTextColor(Color.WHITE);
        toast.show();
    }

    @Override
    public void showWarningMessage(String msg) {
        Toast toast = Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP | Gravity.FILL_HORIZONTAL, 0, 0);
        View toastView = toast.getView();
        toastView.setBackgroundResource(R.drawable.bg_default_toast_warning);
        TextView v = toastView.findViewById(android.R.id.message);
        v.setTextColor(Color.WHITE);
        toast.show();
    }

    @Override
    public void showWarningMessage(int resId) {
        Toast toast = Toast.makeText(getContext(), getResources().getString(resId), Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP | Gravity.FILL_HORIZONTAL, 0, 0);
        View toastView = toast.getView();
        toastView.setBackgroundResource(R.drawable.bg_default_toast_warning);
        TextView v = toastView.findViewById(android.R.id.message);
        v.setTextColor(Color.WHITE);
        toast.show();
    }
}

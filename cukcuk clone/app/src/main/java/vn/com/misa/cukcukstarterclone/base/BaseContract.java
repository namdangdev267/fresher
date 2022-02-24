package vn.com.misa.cukcukstarterclone.base;

import androidx.annotation.StringRes;

/**
 * @created_by KhanhNQ on 07-Jan-21.
 */
public interface BaseContract {
    interface Presenter<T> {
        void attach(T view);

        void detach();
    }

    interface View {
        void showErrorMessage(String msg);
        void showErrorMessage(@StringRes int resId);

        void showNormalMessage(String msg);
        void showNormalMessage(@StringRes int resId);

        void showWarningMessage(String msg);
        void showWarningMessage(@StringRes int resId);
    }
}

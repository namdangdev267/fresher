package vn.com.misa.cukcukstarterclone.data;

import android.os.AsyncTask;

import vn.com.misa.cukcukstarterclone.utils.Utils;

/**
 * - Mục đích Class: Xử lý các công việc cần thực hiện bất đồng bộ
 * - Sử dụng khi: Gọi Network Call, gọi Database Request,...
 *
 * @created_by KhanhNQ on 09-Jan-21.
 */
public class LoadingAsyncTask<P, R> extends AsyncTask<P, Void, R> {

    private final IOnLoadedCallback<R> callback;
    private final Lambda<P, R> lambda;

    public LoadingAsyncTask(IOnLoadedCallback<R> callback, Lambda<P, R> lambda) {
        this.callback = callback;
        this.lambda = lambda;
    }

    @SafeVarargs
    @Override
    protected final R doInBackground(P... ps) {
        try {
            return lambda.handler(ps[0]);
        } catch (Exception e) {
            Utils.handleException(e);
            return null;
        }
    }

    @Override
    protected void onPostExecute(R r) {
        super.onPostExecute(r);

        if (null != r) {
            callback.onSuccess(r);
        } else {
            callback.onFailure(new Exception("LoadingAsyncTask Exception"));
        }
    }

    public interface Lambda<P, R> {
        R handler(P param);
    }
}

package vn.com.misa.cukcukstarterclone.data;

/**
 * - Mục đích Class: Interface Callback để xử lý dữ liệu khi dùng {@link LoadingAsyncTask}
 *
 * @created_by KhanhNQ on 09-Jan-21.
 */
public interface IOnLoadedCallback<T> {
    void onSuccess(T data);

    void onFailure(Exception e);
}

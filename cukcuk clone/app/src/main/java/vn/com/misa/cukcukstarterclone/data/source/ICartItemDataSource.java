package vn.com.misa.cukcukstarterclone.data.source;

import java.util.List;

import vn.com.misa.cukcukstarterclone.data.IOnLoadedCallback;
import vn.com.misa.cukcukstarterclone.data.model.CartItem;

/**
 * - Mục đích Class: Interface chứa các phương thức xử lý data liên quan đến {@link CartItem}
 *
 * @created_by KhanhNQ on 09-Jan-21.
 */
public interface ICartItemDataSource {
    interface Local {
        /**
         * - Mục đích method: Lấy tất cả CartItem trong Local
         *
         * @param callback Callback để xử lý sau khi lấy được dữ liệu
         * @created_by KhanhNQ on 12-Jan-21
         */
        void getAllCartItems(IOnLoadedCallback<List<CartItem>> callback);

        /**
         * - Mục đích method: Lấy ra các CartItem theo groupId trong Local
         *
         * @param cartId   id của group cần lấy ra các CartItem
         * @param callback Callback để xử lý sau khi lấy được dữ liệu
         * @created_by KhanhNQ on 12-Jan-21
         */
        void getItemsByCartId(String cartId, IOnLoadedCallback<List<CartItem>> callback);

        /**
         * - Mục đích method: Thêm mới một CartItem vào Local
         *
         * @param item     thông tin của item cần thêm mới
         * @param callback Callback để xử lý sau khi thêm
         * @created_by KhanhNQ on 12-Jan-21
         */
        void addNewCartItem(CartItem item, IOnLoadedCallback<Boolean> callback);

        /**
         * - Mục đích method: Chỉnh sửa một CartItem trong Local
         *
         * @param item     thông tin của item cần sửa
         * @param callback Callback để xử lý sau khi sửa
         * @created_by KhanhNQ on 12-Jan-21
         */
        void updateCartItem(CartItem item, IOnLoadedCallback<Boolean> callback);

        /**
         * - Mục đích method: Xóa một CartItem trong Local
         *
         * @param item     thông tin của item cần xóa
         * @param callback Callback để xử lý sau khi xóa
         * @created_by KhanhNQ on 12-Jan-21
         */
        void deleteCartItem(CartItem item, IOnLoadedCallback<Boolean> callback);
    }

    interface Remote {
    }
}

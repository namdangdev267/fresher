package vn.com.misa.cukcukstarterclone.data.repository;

import java.util.List;

import vn.com.misa.cukcukstarterclone.data.IOnLoadedCallback;
import vn.com.misa.cukcukstarterclone.data.model.CartItem;

/**
 * - Mục đích Class: Interface cho Repository chứa các phương thức tương tác với {@link CartItem}
 * - Sử dụng khi: tạo Class để implement lại Interface này
 *
 * @created_by KhanhNQ on 25-Jan-21.
 */
public interface ICartItemRepository {
    /**
     * - Mục đích method: Cần lấy ra tất cả CartItem
     *
     * @param callback Callback để xử lý List {@link CartItem}
     * @created_by KhanhNQ on 25-Jan-21
     */
    void getAllCartItems(IOnLoadedCallback<List<CartItem>> callback);

    /**
     * - Mục đích method: Lấy ra các CartItem thuộc 1 group
     *
     * @param cartId   id của group muốn lấy
     * @param callback Callback để xử lý List {@link CartItem}
     * @created_by KhanhNQ on 25-Jan-21
     */
    void getItemsByCartId(String cartId, IOnLoadedCallback<List<CartItem>> callback);

    /**
     * - Mục đích method: Thêm mới một CartItem
     *
     * @param item     thông tin của CartItem cần thêm mới
     * @param callback Callback để xử lý sau khi thêm
     * @created_by KhanhNQ on 25-Jan-21
     */
    void addNewCartItem(CartItem item, IOnLoadedCallback<Boolean> callback);

    /**
     * - Mục đích method: Xóa một CartItem khỏi Local
     *
     * @param item     thông tin của CartItem cần xóa
     * @param callback Callback để xử lý sau khi xóa
     * @created_by KhanhNQ on 25-Jan-21
     */
    void deleteCartItem(CartItem item, IOnLoadedCallback<Boolean> callback);
}

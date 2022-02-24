package vn.com.misa.cukcukstarterclone.data.source.local.dao;

import java.util.List;

import vn.com.misa.cukcukstarterclone.data.model.CartItem;

/**
 * - Mục đích Class: Interface chứa các phương thức tương tác với Local Database liên quan đến {@link CartItem}
 *
 * @created_by KhanhNQ on 09-Jan-21.
 */
public interface ICartItemDao {
    /**
     * - Mục đích method: Lấy ra tất cả CartItem trong Local
     *
     * @created_by KhanhNQ on 25-Jan-21
     */
    List<CartItem> getAllItems();

    /**
     * - Mục đích method: Lấy ra các CartItem với cartId cho trước
     *
     * @created_by KhanhNQ on 25-Jan-21
     */
    List<CartItem> getItemsByCartId(String cartId);

    /**
     * - Mục đích method: Lấy ra CartItem với id cho trước
     *
     * @created_by KhanhNQ on 25-Jan-21
     */
    CartItem getItemById(String id);

    /**
     * - Mục đích method: Thêm mới một CartItem
     *
     * @created_by KhanhNQ on 25-Jan-21
     */
    boolean addNewCartItem(CartItem item);

    /**
     * - Mục đích method: Chỉnh sửa một CartItem
     *
     * @created_by KhanhNQ on 25-Jan-21
     */
    boolean updateCartItem(CartItem item);

    /**
     * - Mục đích method: Xóa một CartItem
     *
     * @created_by KhanhNQ on 25-Jan-21
     */
    boolean deleteCartItem(CartItem item);

}

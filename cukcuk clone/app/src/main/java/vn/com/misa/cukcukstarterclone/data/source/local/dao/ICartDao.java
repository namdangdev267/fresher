package vn.com.misa.cukcukstarterclone.data.source.local.dao;

import java.util.List;

import vn.com.misa.cukcukstarterclone.data.model.Cart;

/**
 * - Mục đích Class: Interface chứa các phương thức tương tác với Local Database liên quan đến {@link Cart}
 *
 * @created_by KhanhNQ on 25-Jan-21.
 */
public interface ICartDao {
    /**
     * - Mục đích method: Lấy ra tất cả Cart trong Local
     *
     * @created_by KhanhNQ on 25-Jan-21
     */
    List<Cart> getAllCarts();

    /**
     * - Mục đích method: Lấy ra Cart với id cho trước
     *
     * @param cartId id của Cart cần lấy thông tin
     * @created_by KhanhNQ on 25-Jan-21
     */
    Cart getCartById(String cartId);

    /**
     * - Mục đích method: Thêm mới một Cart
     *
     * @param cart thông tin của Cart cần thêm mới
     * @created_by KhanhNQ on 25-Jan-21
     */
    boolean addNewCart(Cart cart);

    /**
     * - Mục đích method: Cập nhật một Cart
     *
     * @param cart thông tin của Cart cần thay đổi
     * @created_by KhanhNQ on 25-Jan-21
     */
    boolean updateCart(Cart cart);

    /**
     * - Mục đích method: Thêm mới một Cart
     *
     * @param cart Cart cần xóa
     * @created_by KhanhNQ on 25-Jan-21
     */
    boolean deleteCart(Cart cart);
}

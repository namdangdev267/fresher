package vn.com.misa.cukcukstarterclone.data.repository;

import java.util.List;

import vn.com.misa.cukcukstarterclone.data.IOnLoadedCallback;
import vn.com.misa.cukcukstarterclone.data.model.Cart;

/**
 * - Mục đích Class: Interface cho Repository chứa các phương thức tương tác với {@link Cart}
 * - Sử dụng khi: tạo Class để implement lại Interface này
 *
 * @created_by KhanhNQ on 25-Jan-21.
 */
public interface ICartRepository {

    /**
     * - Mục đích method: Lấy tất cả Cart trong Local
     *
     * @param callback Callback để xử lý sau khi lấy được dữ liệu
     * @created_by KhanhNQ on 25-Jan-21
     */
    void getAllCarts(IOnLoadedCallback<List<Cart>> callback);

    /**
     * - Mục đích method: Lấy ra Cart theo id trong Local
     *
     * @param cartId   id của Cart cần lấy ra
     * @param callback Callback để xử lý sau khi lấy được dữ liệu
     * @created_by KhanhNQ on 25-Jan-21
     */
    void getCartById(String cartId, IOnLoadedCallback<Cart> callback);

    /**
     * - Mục đích method: Thêm mới một Cart vào Local
     *
     * @param item     thông tin của item cần thêm mới
     * @param callback Callback để xử lý sau khi thêm
     * @created_by KhanhNQ on 25-Jan-21
     */
    void addNewCart(Cart item, IOnLoadedCallback<Boolean> callback);

    /**
     * - Mục đích method: Chỉnh sửa Cart trong CSDL
     *
     * @param cart thông tin của cart cần chỉnh sửa
     * @param callback Callback sau khi chỉnh sửa thành công
     *
     * @created_by KhanhNQ on 27-Jan-21
     */
    void updateCart(Cart cart, IOnLoadedCallback<Boolean> callback);

    /**
     * - Mục đích method: Xóa một Cart khỏi Local
     *
     * @param item     thông tin của item cần xóa
     * @param callback Callback để xử lý sau khi xóa
     * @created_by KhanhNQ on 25-Jan-21
     */
    void deleteCart(Cart item, IOnLoadedCallback<Boolean> callback);
}

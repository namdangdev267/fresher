package vn.com.misa.cukcukstarterclone.data.repository;

import java.util.List;

import vn.com.misa.cukcukstarterclone.data.IOnLoadedCallback;
import vn.com.misa.cukcukstarterclone.data.model.Cart;
import vn.com.misa.cukcukstarterclone.data.source.ICartDataSource;

/**
 * - Mục đích Class: Repository để xử lý dữ liệu liên quan đến {@link Cart}
 *
 * @created_by KhanhNQ on 25-Jan-21.
 */
public class CartRepository implements ICartRepository {

    private final ICartDataSource.Local localDataSource;

    private static CartRepository instance;

    private CartRepository(ICartDataSource.Local localDataSource) {
        this.localDataSource = localDataSource;
    }

    public static CartRepository getInstance(ICartDataSource.Local localDataSource) {
        if (null == instance) {
            instance = new CartRepository(localDataSource);
        }
        return instance;
    }

    /**
     * - Mục đích method: Lấy tất cả Cart trong Local
     *
     * @param callback Callback để xử lý sau khi lấy được dữ liệu
     * @created_by KhanhNQ on 25-Jan-21
     */
    @Override
    public void getAllCarts(IOnLoadedCallback<List<Cart>> callback) {
        localDataSource.getAllCarts(callback);
    }

    /**
     * - Mục đích method: Lấy ra Cart theo id trong Local
     *
     * @param cartId   id của Cart cần lấy ra
     * @param callback Callback để xử lý sau khi lấy được dữ liệu
     * @created_by KhanhNQ on 25-Jan-21
     */
    @Override
    public void getCartById(String cartId, IOnLoadedCallback<Cart> callback) {
        localDataSource.getCartById(cartId, callback);
    }

    /**
     * - Mục đích method: Thêm mới một Cart vào Local
     *
     * @param item     thông tin của item cần thêm mới
     * @param callback Callback để xử lý sau khi thêm
     * @created_by KhanhNQ on 25-Jan-21
     */
    @Override
    public void addNewCart(Cart item, IOnLoadedCallback<Boolean> callback) {
        localDataSource.addNewCart(item, callback);
    }

    /**
     * - Mục đích method: Chỉnh sửa Cart trong CSDL
     *
     * @param cart thông tin của cart cần chỉnh sửa
     * @param callback Callback sau khi chỉnh sửa thành công
     *
     * @created_by KhanhNQ on 27-Jan-21
     */
    @Override
    public void updateCart(Cart cart, IOnLoadedCallback<Boolean> callback) {
        localDataSource.updateCart(cart, callback);
    }

    /**
     * - Mục đích method: Xóa một CartItem khỏi Local
     *
     * @param item     thông tin của CartItem cần xóa
     * @param callback Callback để xử lý sau khi xóa
     * @created_by KhanhNQ on 25-Jan-21
     */
    @Override
    public void deleteCart(Cart item, IOnLoadedCallback<Boolean> callback) {
        localDataSource.deleteCart(item, callback);
    }
}

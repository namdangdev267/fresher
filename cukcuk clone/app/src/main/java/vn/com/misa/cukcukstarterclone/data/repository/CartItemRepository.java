package vn.com.misa.cukcukstarterclone.data.repository;

import java.util.List;

import vn.com.misa.cukcukstarterclone.data.IOnLoadedCallback;
import vn.com.misa.cukcukstarterclone.data.model.CartItem;
import vn.com.misa.cukcukstarterclone.data.source.ICartItemDataSource;

/**
 * - Mục đích Class: Repository để xử lý dữ liệu liên quan đến {@link CartItem}
 *
 * @created_by KhanhNQ on 25-Jan-21.
 */
public class CartItemRepository implements ICartItemRepository {

    private final ICartItemDataSource.Local localDataSource;

    private static CartItemRepository instance;

    private CartItemRepository(ICartItemDataSource.Local localDataSource) {
        this.localDataSource = localDataSource;
    }

    public static CartItemRepository getInstance(ICartItemDataSource.Local localDataSource) {
        if (null == instance) {
            instance = new CartItemRepository(localDataSource);
        }
        return instance;
    }

    /**
     * - Mục đích method: Cần lấy ra tất cả CartItem
     *
     * @param callback Callback để xử lý List {@link CartItem}
     * @created_by KhanhNQ on 25-Jan-21
     */
    @Override
    public void getAllCartItems(IOnLoadedCallback<List<CartItem>> callback) {
        localDataSource.getAllCartItems(callback);
    }

    /**
     * - Mục đích method: Lấy ra các CartItem thuộc 1 group
     *
     * @param cartId   id của group muốn lấy
     * @param callback Callback để xử lý List {@link CartItem}
     * @created_by KhanhNQ on 25-Jan-21
     */
    @Override
    public void getItemsByCartId(String cartId, IOnLoadedCallback<List<CartItem>> callback) {
        localDataSource.getItemsByCartId(cartId, callback);
    }

    /**
     * - Mục đích method: Thêm mới một CartItem
     *
     * @param item     thông tin của CartItem cần thêm mới
     * @param callback Callback để xử lý sau khi thêm
     * @created_by KhanhNQ on 25-Jan-21
     */
    @Override
    public void addNewCartItem(CartItem item, IOnLoadedCallback<Boolean> callback) {
        localDataSource.addNewCartItem(item, callback);
    }

    /**
     * - Mục đích method: Thêm mới một CartItem
     *
     * @param item     thông tin của CartItem cần thêm mới
     * @param callback Callback để xử lý sau khi thêm
     * @created_by KhanhNQ on 25-Jan-21
     */
    @Override
    public void deleteCartItem(CartItem item, IOnLoadedCallback<Boolean> callback) {
        localDataSource.deleteCartItem(item, callback);
    }
}

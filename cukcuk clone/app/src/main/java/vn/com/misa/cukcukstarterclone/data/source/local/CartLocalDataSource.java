package vn.com.misa.cukcukstarterclone.data.source.local;

import java.util.List;

import vn.com.misa.cukcukstarterclone.data.IOnLoadedCallback;
import vn.com.misa.cukcukstarterclone.data.LoadingAsyncTask;
import vn.com.misa.cukcukstarterclone.data.model.Cart;
import vn.com.misa.cukcukstarterclone.data.source.ICartDataSource;
import vn.com.misa.cukcukstarterclone.data.source.Unit;
import vn.com.misa.cukcukstarterclone.data.source.local.dao.ICartDao;
import vn.com.misa.cukcukstarterclone.utils.Utils;

/**
 * - Mục đích Class: LocalDataSource để tương tác với database liên quan tới {@link Cart}
 *
 * @created_by KhanhNQ on 25-Jan-21.
 */
public class CartLocalDataSource implements ICartDataSource.Local {
    private static CartLocalDataSource instance = null;

    private ICartDao cartDao;

    private CartLocalDataSource(ICartDao cartDao) {
        this.cartDao = cartDao;
    }

    /**
     * - Mục đích method: Singleton pattern để lấy instance của class
     *
     * @param cartDao DAO của {@link Cart}
     * @return Instance của {@link CartLocalDataSource}
     * @created_by KhanhNQ on 25-Jan-21
     */
    public static CartLocalDataSource getInstance(ICartDao cartDao) {
        if (null == instance) {
            instance = new CartLocalDataSource(cartDao);
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
        try {
            new LoadingAsyncTask<Unit, List<Cart>>(callback, param -> cartDao.getAllCarts()).execute(new Unit());
        } catch (Exception e) {
            Utils.handleException(e);
        }
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
        try {
            new LoadingAsyncTask<String, Cart>(callback, param -> cartDao.getCartById(cartId)).execute(cartId);
        } catch (Exception e) {
            Utils.handleException(e);
        }
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
        try {
            new LoadingAsyncTask<Cart, Boolean>(callback, param -> cartDao.addNewCart(item)).execute(item);
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    @Override
    public void updateCart(Cart cart, IOnLoadedCallback<Boolean> callback) {
        try {
            new LoadingAsyncTask<Cart, Boolean>(callback, param -> cartDao.updateCart(cart)).execute(cart);
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    /**
     * - Mục đích method: Xóa một Cart khỏi Local
     *
     * @param item     thông tin của item cần xóa
     * @param callback Callback để xử lý sau khi xóa
     * @created_by KhanhNQ on 25-Jan-21
     */
    @Override
    public void deleteCart(Cart item, IOnLoadedCallback<Boolean> callback) {
        try {
            new LoadingAsyncTask<Cart, Boolean>(callback, param -> cartDao.deleteCart(item)).execute(item);
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }
}

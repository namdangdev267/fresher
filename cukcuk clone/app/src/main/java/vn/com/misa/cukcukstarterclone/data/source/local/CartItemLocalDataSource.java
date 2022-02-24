package vn.com.misa.cukcukstarterclone.data.source.local;

import java.util.List;

import vn.com.misa.cukcukstarterclone.data.IOnLoadedCallback;
import vn.com.misa.cukcukstarterclone.data.LoadingAsyncTask;
import vn.com.misa.cukcukstarterclone.data.model.CartItem;
import vn.com.misa.cukcukstarterclone.data.source.ICartItemDataSource;
import vn.com.misa.cukcukstarterclone.data.source.Unit;
import vn.com.misa.cukcukstarterclone.data.source.local.dao.ICartItemDao;
import vn.com.misa.cukcukstarterclone.utils.Utils;

/**
 * - Mục đích Class: LocalDataSource để tương tác với database liên quan tới {@link CartItem}
 *
 * @created_by KhanhNQ on 09-Jan-21.
 */
public class CartItemLocalDataSource implements ICartItemDataSource.Local {

    private static CartItemLocalDataSource instance = null;

    private final ICartItemDao cartItemDao;

    private CartItemLocalDataSource(ICartItemDao cartItemDao) {
        this.cartItemDao = cartItemDao;
    }

    /**
     * - Mục đích method: Singleton pattern để lấy instance của class
     *
     * @param cartItemDao DAO của {@link CartItem}
     * @return Instance của {@link CartItemLocalDataSource}
     * @created_by KhanhNQ on 12-Jan-21
     */
    public static CartItemLocalDataSource getInstance(ICartItemDao cartItemDao) {
        if (null == instance) {
            instance = new CartItemLocalDataSource(cartItemDao);
        }
        return instance;
    }

    @Override
    public void getAllCartItems(IOnLoadedCallback<List<CartItem>> callback) {
        try {
            new LoadingAsyncTask<Unit, List<CartItem>>(callback, param -> cartItemDao.getAllItems()).execute(new Unit());
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    @Override
    public void getItemsByCartId(String cartId, IOnLoadedCallback<List<CartItem>> callback) {
        try {
            new LoadingAsyncTask<String, List<CartItem>>(callback, param -> cartItemDao.getItemsByCartId(cartId)).execute(cartId);
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    @Override
    public void addNewCartItem(CartItem item, IOnLoadedCallback<Boolean> callback) {
        try {
            new LoadingAsyncTask<CartItem, Boolean>(callback, param -> cartItemDao.addNewCartItem(item)).execute(item);
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    @Override
    public void updateCartItem(CartItem item, IOnLoadedCallback<Boolean> callback) {
        try {
            new LoadingAsyncTask<CartItem, Boolean>(callback, param -> cartItemDao.updateCartItem(item)).execute(item);
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    @Override
    public void deleteCartItem(CartItem item, IOnLoadedCallback<Boolean> callback) {
        try {
            new LoadingAsyncTask<CartItem, Boolean>(callback, param -> cartItemDao.deleteCartItem(item)).execute(item);
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }
}

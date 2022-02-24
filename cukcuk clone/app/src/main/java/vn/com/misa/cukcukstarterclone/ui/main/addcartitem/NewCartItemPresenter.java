package vn.com.misa.cukcukstarterclone.ui.main.addcartitem;

import vn.com.misa.cukcukstarterclone.R;
import vn.com.misa.cukcukstarterclone.data.IOnLoadedCallback;
import vn.com.misa.cukcukstarterclone.data.model.CartItem;
import vn.com.misa.cukcukstarterclone.data.model.MenuItem;
import vn.com.misa.cukcukstarterclone.data.repository.ICartItemRepository;
import vn.com.misa.cukcukstarterclone.data.repository.IMenuItemRepository;

/**
 * @created_by KhanhNQ on 27-Jan-21
 */
public class NewCartItemPresenter implements NewCartItemContract.Presenter {

    private NewCartItemContract.View view;

    private final IMenuItemRepository menuItemRepository;
    private final ICartItemRepository cartItemRepository;

    public NewCartItemPresenter(IMenuItemRepository menuItemRepository, ICartItemRepository cartItemRepository) {
        this.menuItemRepository = menuItemRepository;
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    public void attach(NewCartItemContract.View view) {
        this.view = view;
    }

    @Override
    public void detach() {
        this.view = null;
    }

    /**
     * - Mục đích method: Lấy thông tin của product theo id
     *
     * @param id id của product cần lấy thông tin
     * @created_by KhanhNQ on 27-Jan-21
     */
    @Override
    public void getProductInfo(String id) {
        menuItemRepository.getItemById(id, new IOnLoadedCallback<MenuItem>() {
            @Override
            public void onSuccess(MenuItem data) {
                view.updateProductInfo(data);
            }

            @Override
            public void onFailure(Exception e) {
                view.showErrorMessage(e.getLocalizedMessage());
            }
        });
    }

    /**
     * - Mục đích method: lưu CartItem mới vào CSDL
     *
     * @param cartItem thông tin của CartItem cần thêm
     * @created_by KhanhNQ on 27-Jan-21
     */
    @Override
    public void saveNewCartItem(CartItem cartItem) {
        cartItemRepository.addNewCartItem(cartItem, new IOnLoadedCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean isSuccess) {
                if (isSuccess) {
                    view.onAddNewCartSuccess(cartItem);
                } else {
                    view.showErrorMessage(R.string.error_add_new_cart_item);
                }
            }

            @Override
            public void onFailure(Exception e) {
                view.showErrorMessage(e.getLocalizedMessage());
            }
        });
    }
}

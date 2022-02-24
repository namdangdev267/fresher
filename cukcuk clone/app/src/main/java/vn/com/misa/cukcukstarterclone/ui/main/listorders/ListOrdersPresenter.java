package vn.com.misa.cukcukstarterclone.ui.main.listorders;

import java.util.List;

import vn.com.misa.cukcukstarterclone.data.IOnLoadedCallback;
import vn.com.misa.cukcukstarterclone.data.model.Cart;
import vn.com.misa.cukcukstarterclone.data.repository.ICartRepository;

/**
 * @created_by KhanhNQ on 25-Jan-21.
 */
public class ListOrdersPresenter implements ListOrdersContract.Presenter {

    private ListOrdersContract.View view;

    private final ICartRepository cartRepository;

    public ListOrdersPresenter(ICartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public void attach(ListOrdersContract.View view) {
        this.view = view;
    }

    @Override
    public void detach() {
        this.view = null;
    }

    @Override
    public void getAllCarts() {
        cartRepository.getAllCarts(new IOnLoadedCallback<List<Cart>>() {
            @Override
            public void onSuccess(List<Cart> data) {
                view.updateData(data);
            }

            @Override
            public void onFailure(Exception e) {
                view.showErrorMessage(e.getMessage());
            }
        });
    }

    @Override
    public void removeCart(Cart cart) {
        cartRepository.deleteCart(cart, new IOnLoadedCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean data) {
                view.deleteCartSuccess(cart);
            }

            @Override
            public void onFailure(Exception e) {
                view.showErrorMessage(e.getLocalizedMessage());
            }
        });
    }
}

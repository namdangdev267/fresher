package vn.com.misa.cukcukstarterclone.ui.order.orderdetails;

import java.util.List;

import vn.com.misa.cukcukstarterclone.data.IOnLoadedCallback;
import vn.com.misa.cukcukstarterclone.data.model.CartItem;
import vn.com.misa.cukcukstarterclone.data.model.MenuItem;
import vn.com.misa.cukcukstarterclone.data.repository.ICartItemRepository;
import vn.com.misa.cukcukstarterclone.data.repository.IMenuItemRepository;
import vn.com.misa.cukcukstarterclone.ui.order.dto.CartItemDto;

/**
 * @created_by KhanhNQ on 29-Jan-2021.
 */
public class OrderDetailsFragmentPresenter implements OrderDetailsFragmentContract.Presenter {

    private OrderDetailsFragmentContract.View view;

    private final ICartItemRepository cartItemRepository;
    private final IMenuItemRepository menuItemRepository;

    public OrderDetailsFragmentPresenter(ICartItemRepository cartItemRepository, IMenuItemRepository menuItemRepository) {
        this.cartItemRepository = cartItemRepository;
        this.menuItemRepository = menuItemRepository;
    }

    @Override
    public void getCartItems(String cartId) {
        cartItemRepository.getItemsByCartId(cartId, new IOnLoadedCallback<List<CartItem>>() {
            @Override
            public void onSuccess(List<CartItem> items) {
                getItemNames(items);
            }

            @Override
            public void onFailure(Exception e) {
                view.showErrorMessage(e.getLocalizedMessage());
            }
        });
    }

    private void getItemNames(List<CartItem> cartItems) {
        for (CartItem cartItem : cartItems) {
            menuItemRepository.getItemById(cartItem.getProductId(), new IOnLoadedCallback<MenuItem>() {
                @Override
                public void onSuccess(MenuItem data) {
                    view.insertCartItem(new CartItemDto(data, cartItem));
                }

                @Override
                public void onFailure(Exception e) {
                    view.showErrorMessage(e.getLocalizedMessage());
                }
            });
        }
    }

    @Override
    public void attach(OrderDetailsFragmentContract.View view) {
        this.view = view;
    }

    @Override
    public void detach() {
        this.view = null;
    }
}

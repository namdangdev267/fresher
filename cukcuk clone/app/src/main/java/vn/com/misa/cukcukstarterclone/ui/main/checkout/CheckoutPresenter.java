package vn.com.misa.cukcukstarterclone.ui.main.checkout;

import vn.com.misa.cukcukstarterclone.R;
import vn.com.misa.cukcukstarterclone.data.IOnLoadedCallback;
import vn.com.misa.cukcukstarterclone.data.model.Cart;
import vn.com.misa.cukcukstarterclone.data.model.Order;
import vn.com.misa.cukcukstarterclone.data.repository.ICartRepository;
import vn.com.misa.cukcukstarterclone.data.repository.IOrderRepository;

/**
 * @created_by KhanhNQ on 28-Jan-2021.
 */
public class CheckoutPresenter implements CheckoutContract.Presenter {

    private CheckoutContract.View view;

    private final ICartRepository cartRepository;
    private final IOrderRepository orderRepository;

    public CheckoutPresenter(ICartRepository cartRepository, IOrderRepository orderRepository) {
        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public void attach(CheckoutContract.View view) {
        this.view = view;
    }

    @Override
    public void detach() {
        this.view = null;
    }

    @Override
    public void addNewOrder(Cart cart, Order order) {
        cartRepository.addNewCart(cart, new IOnLoadedCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean data) {
                orderRepository.addNewOrder(order, new IOnLoadedCallback<Boolean>() {
                    @Override
                    public void onSuccess(Boolean isSuccess) {
                        if (isSuccess) {
                            view.addOrderSuccess();
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

            @Override
            public void onFailure(Exception e) {
                view.showErrorMessage(e.getLocalizedMessage());
            }
        });
    }
}

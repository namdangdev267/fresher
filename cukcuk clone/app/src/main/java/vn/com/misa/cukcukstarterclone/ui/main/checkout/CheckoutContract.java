package vn.com.misa.cukcukstarterclone.ui.main.checkout;

import vn.com.misa.cukcukstarterclone.base.BaseContract;
import vn.com.misa.cukcukstarterclone.data.model.Cart;
import vn.com.misa.cukcukstarterclone.data.model.Order;

/**
 * @created_by KhanhNQ on 28-Jan-2021.
 */
public class CheckoutContract {
    interface View extends BaseContract.View {
        void addOrderSuccess();
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void addNewOrder(Cart cart, Order order);
    }
}

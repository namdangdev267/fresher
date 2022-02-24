package vn.com.misa.cukcukstarterclone.ui.main.listorders;

import java.util.List;

import vn.com.misa.cukcukstarterclone.base.BaseContract;
import vn.com.misa.cukcukstarterclone.data.model.Cart;

/**
 * @created_by KhanhNQ on 25-Jan-21.
 */
public class ListOrdersContract {
    interface View extends BaseContract.View {
        void updateData(List<Cart> carts);

        void deleteCartSuccess(Cart cart);
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void getAllCarts();

        void removeCart(Cart cart);
    }
}

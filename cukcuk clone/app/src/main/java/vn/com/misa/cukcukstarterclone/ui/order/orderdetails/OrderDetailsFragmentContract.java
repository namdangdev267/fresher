package vn.com.misa.cukcukstarterclone.ui.order.orderdetails;

import vn.com.misa.cukcukstarterclone.base.BaseContract;
import vn.com.misa.cukcukstarterclone.ui.order.dto.CartItemDto;

/**
 * @created_by KhanhNQ on 29-Jan-2021.
 */
public class OrderDetailsFragmentContract {
    interface View extends BaseContract.View {
        void insertCartItem(CartItemDto newCartItem);
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void getCartItems(String cartId);
    }
}

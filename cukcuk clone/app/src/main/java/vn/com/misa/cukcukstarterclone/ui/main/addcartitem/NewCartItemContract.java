package vn.com.misa.cukcukstarterclone.ui.main.addcartitem;

import vn.com.misa.cukcukstarterclone.base.BaseContract;
import vn.com.misa.cukcukstarterclone.data.model.CartItem;
import vn.com.misa.cukcukstarterclone.data.model.MenuItem;

/**
 * @created_by KhanhNQ on 27-Jan-21
 */
public class NewCartItemContract {
    interface View extends BaseContract.View {
        /**
         * - Mục đích method: cập nhật thông tin sản phẩm
         *
         * @param item sản phẩm cần cập nhật
         * @created_by KhanhNQ on 27-Jan-21
         */
        void updateProductInfo(MenuItem item);

        /**
         * - Mục đích method: update UI sau khi thêm vào giỏ hàng thành công
         *
         * @param cartItem thông tin CartItem đã thêm thành công
         * @created_by KhanhNQ on 27-Jan-21
         */
        void onAddNewCartSuccess(CartItem cartItem);
    }

    interface Presenter extends BaseContract.Presenter<View> {

        /**
         * - Mục đích method: Lấy thông tin của product theo id
         *
         * @param id id của product cần lấy thông tin
         * @created_by KhanhNQ on 27-Jan-21
         */
        void getProductInfo(String id);

        /**
         * - Mục đích method: lưu CartItem mới vào CSDL
         *
         * @param cartItem thông tin của CartItem cần thêm
         * @created_by KhanhNQ on 27-Jan-21
         */
        void saveNewCartItem(CartItem cartItem);
    }
}

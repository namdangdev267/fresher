package vn.com.misa.cukcukstarterclone.ui.main.addorder;

import java.util.List;

import vn.com.misa.cukcukstarterclone.base.BaseContract;
import vn.com.misa.cukcukstarterclone.data.model.Cart;
import vn.com.misa.cukcukstarterclone.data.model.CartItem;
import vn.com.misa.cukcukstarterclone.data.model.Order;
import vn.com.misa.cukcukstarterclone.ui.main.addorder.dto.CartItemDto;
import vn.com.misa.cukcukstarterclone.ui.main.addorder.dto.MenuGroupDto;
import vn.com.misa.cukcukstarterclone.ui.main.addorder.dto.MenuItemDto;

/**
 * @created_by KhanhNQ on 25-Jan-21.
 */
public class AddOrderContract {
    interface View extends BaseContract.View {
        /**
         * - Mục đích method: Update thông tin danh sách MenuGroup lên view
         *
         * @param groups danh sách groups đã được load từ presenter
         * @created_by KhanhNQ on 25-Jan-21
         */
        void updateMenuGroup(List<MenuGroupDto> groups);

        /**
         * - Mục đích method: Hiển thị danh sách MenuItemDto lên view
         *
         * @param items danh sách MenuItemDto đã được load
         * @created_by KhanhNQ on 25-Jan-21
         */
        void loadMenuItems(List<MenuItemDto> items);

        /**
         * - Mục đích method: Cập nhật danh sách MenuItemDto
         *
         * @param items danh sách MenuItemDto đã được load
         * @created_by KhanhNQ on 25-Jan-21
         */
        void updateMenuItem(List<MenuItemDto> items);

        /**
         * - Mục đích method: Cập nhật danh sách CartItem
         *
         * @param cartItems danh sách CartItem
         * @created_by KhanhNQ on 25-Jan-21
         */
        void updateCartItems(List<CartItem> cartItems);

        void updateCartItemDtoList(List<CartItemDto> itemDtoList);

        /**
         * - Mục đích method: Tiếp tục thêm mới các item trong Cart
         * - Sử dụng khi: được gọi từ Presenter để thông báo với View đã thêm mới Cart thành công
         *
         * @created_by KhanhNQ on 27-Jan-21
         */
        void addCartSuccess();

        /**
         * - Mục đích method: Cập nhật giao diện sau khi thêm mới CartItems thành công vào CSDL
         *
         * @created_by KhanhNQ on 27-Jan-21
         */
        void addCartItemsDone();
    }

    interface Presenter extends BaseContract.Presenter<View> {

        /**
         * - Mục đích method: Lấy ra tất cả MenuGroup từ CSDL
         *
         * @created_by KhanhNQ on 25-Jan-21
         */
        void getAllGroups();

        /**
         * - Mục đích method: Lấy ra tất cả MenuItem từ CSDL
         *
         * @created_by KhanhNQ on 25-Jan-21
         */
        void getAllItems();

        /**
         * - Mục đích method: Lấy ra các item trong Card
         *
         * @param cartId cartId của các item
         * @created_by KhanhNQ on 25-Jan-21
         */
        void getCartItems(String cartId);

        /**
         * - Mục đích method: Lọc danh sách items theo groupId
         *
         * @param groupId id của group muốn lấy ra items
         * @param items   danh sách các items muốn lọc
         * @created_by KhanhNQ on 25-Jan-21
         */
        void filterItems(String groupId, List<MenuItemDto> items);

        /**
         * - Mục đích method: Thêm mới một Cart vào CSDL
         *
         * @param cart thông tin của Cart cần thêm mới
         * @created_by KhanhNQ on 25-Jan-21
         */
        void addNewCart(Cart cart);

        /**
         * - Mục đích method: Cập nhật thông tin của Cart trong CSDL
         *
         * @param cart thông tin của Cart cần cập nhật
         * @created_by KhanhNQ on 25-Jan-21
         */
        void updateCart(Cart cart);

        /**
         * - Mục đích method: Thêm một danh sách các CartItem vào CSDL
         *
         * @param cartItems danh sách cartItem cần thêm
         * @created_by KhanhNQ on 27-Jan-21
         */
        void addCartItems(List<CartItem> cartItems);

        /**
         * - Mục đích method: Thêm mới một đơn hàng vào CSDL
         *
         * @param order Thông tin của đơn hàng cần thêm
         * @created_by KhanhNQ on 25-Jan-21
         */
        void addNewOrder(Order order);
    }
}

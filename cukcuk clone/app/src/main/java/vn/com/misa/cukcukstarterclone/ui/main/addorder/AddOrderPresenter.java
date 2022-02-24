package vn.com.misa.cukcukstarterclone.ui.main.addorder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import vn.com.misa.cukcukstarterclone.data.IOnLoadedCallback;
import vn.com.misa.cukcukstarterclone.data.model.Cart;
import vn.com.misa.cukcukstarterclone.data.model.CartItem;
import vn.com.misa.cukcukstarterclone.data.model.MenuGroup;
import vn.com.misa.cukcukstarterclone.data.model.MenuItem;
import vn.com.misa.cukcukstarterclone.data.model.Order;
import vn.com.misa.cukcukstarterclone.data.repository.ICartItemRepository;
import vn.com.misa.cukcukstarterclone.data.repository.ICartRepository;
import vn.com.misa.cukcukstarterclone.data.repository.IMenuGroupRepository;
import vn.com.misa.cukcukstarterclone.data.repository.IMenuItemRepository;
import vn.com.misa.cukcukstarterclone.ui.main.addorder.dto.CartItemDto;
import vn.com.misa.cukcukstarterclone.ui.main.addorder.dto.MenuGroupDto;
import vn.com.misa.cukcukstarterclone.ui.main.addorder.dto.MenuItemDto;

/**
 * @created_by KhanhNQ on 25-Jan-21.
 */
public class AddOrderPresenter implements AddOrderContract.Presenter {

    private AddOrderContract.View view;

    private final IMenuGroupRepository menuGroupRepository;
    private final IMenuItemRepository menuItemRepository;
    private final ICartRepository cartRepository;
    private final ICartItemRepository cartItemRepository;

    public AddOrderPresenter(IMenuGroupRepository menuGroupRepository,
                             IMenuItemRepository menuItemRepository,
                             ICartRepository cartRepository,
                             ICartItemRepository cartItemRepository) {
        this.menuGroupRepository = menuGroupRepository;
        this.menuItemRepository = menuItemRepository;
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
    }

    /**
     * - Mục đích method: Lấy ra tất cả MenuGroup từ CSDL
     *
     * @created_by KhanhNQ on 25-Jan-21
     */
    @Override
    public void getAllGroups() {
        menuGroupRepository.getAllMenuGroups(new IOnLoadedCallback<List<MenuGroup>>() {
            @Override
            public void onSuccess(List<MenuGroup> data) {
                // TODO: Apply to API < 24
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    List<MenuGroupDto> menuGroupDtoList = data.stream()
                            .map(group -> new MenuGroupDto(group, 0))
                            .collect(Collectors.toList());
                    view.updateMenuGroup(menuGroupDtoList);
                }
            }

            @Override
            public void onFailure(Exception e) {
                view.showErrorMessage(e.getLocalizedMessage());
            }
        });
    }

    /**
     * - Mục đích method: Lấy ra tất cả MenuItem từ CSDL
     *
     * @created_by KhanhNQ on 25-Jan-21
     */
    @Override
    public void getAllItems() {
        menuItemRepository.getAllMenuItems(new IOnLoadedCallback<List<MenuItem>>() {
            @Override
            public void onSuccess(List<MenuItem> data) {
                // TODO: Apply to API < 24
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    List<MenuItemDto> menuItemDtoList = data.stream()
                            .map(item -> new MenuItemDto(item, 0))
                            .collect(Collectors.toList());
                    view.loadMenuItems(menuItemDtoList);
                }
            }

            @Override
            public void onFailure(Exception e) {
                view.showErrorMessage(e.getMessage());
            }
        });
    }

    /**
     * - Mục đích method: Lấy ra các item trong Card
     *
     * @param cartId cartId của các item
     * @created_by KhanhNQ on 25-Jan-21
     */
    @Override
    public void getCartItems(String cartId) {
        cartItemRepository.getItemsByCartId(cartId, new IOnLoadedCallback<List<CartItem>>() {
            @Override
            public void onSuccess(List<CartItem> data) {
                view.updateCartItems(data);

                List<CartItemDto> cartItemDtoList = new ArrayList<>();
                for (CartItem item : data) {
                    cartItemDtoList.add(new CartItemDto(item));
                }
                view.updateCartItemDtoList(cartItemDtoList);
            }

            @Override
            public void onFailure(Exception e) {
                view.showErrorMessage(e.getLocalizedMessage());
            }
        });
    }

    /**
     * - Mục đích method: Lọc danh sách items theo groupId
     *
     * @param groupId id của group muốn lấy ra items
     * @param items   danh sách các items muốn lọc
     * @created_by KhanhNQ on 25-Jan-21
     */
    @Override
    public void filterItems(String groupId, List<MenuItemDto> items) {
        List<MenuItemDto> list = new ArrayList<>();
        for (MenuItemDto item : items) {
            if (groupId.equals(item.getGroupId())) list.add(item);
        }
        view.updateMenuItem(list);
    }

    /**
     * - Mục đích method: Thêm mới một Cart vào CSDL
     *
     * @param cart thông tin của Cart cần thêm mới
     * @created_by KhanhNQ on 25-Jan-21
     */
    @Override
    public void addNewCart(Cart cart) {
        cartRepository.addNewCart(cart, new IOnLoadedCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean data) {
                view.addCartSuccess();
            }

            @Override
            public void onFailure(Exception e) {
                view.showErrorMessage(e.getLocalizedMessage());
            }
        });
    }

    /**
     * - Mục đích method: Cập nhật thông tin của Cart trong CSDL
     *
     * @param cart thông tin của Cart cần cập nhật
     * @created_by KhanhNQ on 25-Jan-21
     */
    @Override
    public void updateCart(Cart cart) {
        cartRepository.updateCart(cart, new IOnLoadedCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean data) {
                view.addCartSuccess();
            }

            @Override
            public void onFailure(Exception e) {
                view.showErrorMessage(e.getLocalizedMessage());
            }
        });
    }

    /**
     * - Mục đích method: Thêm một danh sách các CartItem vào CSDL
     *
     * @param cartItems danh sách cartItem cần thêm
     * @created_by KhanhNQ on 27-Jan-21
     */
    @Override
    public void addCartItems(List<CartItem> cartItems) {
        for (int i = 0; i < cartItems.size(); i++) {
            int index = i;
            cartItemRepository.addNewCartItem(cartItems.get(i), new IOnLoadedCallback<Boolean>() {
                @Override
                public void onSuccess(Boolean data) {
                    if (index == cartItems.size() - 1) view.addCartItemsDone();
                }

                @Override
                public void onFailure(Exception e) {
                    view.showErrorMessage(e.getLocalizedMessage());
                    if (index == cartItems.size() - 1) view.addCartItemsDone();
                }
            });
        }
    }

    /**
     * - Mục đích method: Thêm mới một đơn hàng vào CSDL
     *
     * @param order Thông tin của đơn hàng cần thêm
     * @created_by KhanhNQ on 25-Jan-21
     */
    @Override
    public void addNewOrder(Order order) {

    }

    @Override
    public void attach(AddOrderContract.View view) {
        this.view = view;
    }

    @Override
    public void detach() {
        this.view = null;
    }
}

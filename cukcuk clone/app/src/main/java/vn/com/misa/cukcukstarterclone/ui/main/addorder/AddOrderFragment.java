package vn.com.misa.cukcukstarterclone.ui.main.addorder;

import android.content.res.Configuration;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vn.com.misa.cukcukstarterclone.R;
import vn.com.misa.cukcukstarterclone.base.BaseFragment;
import vn.com.misa.cukcukstarterclone.data.model.Cart;
import vn.com.misa.cukcukstarterclone.data.model.Cart.CartType;
import vn.com.misa.cukcukstarterclone.data.model.CartItem;
import vn.com.misa.cukcukstarterclone.data.repository.CartItemRepository;
import vn.com.misa.cukcukstarterclone.data.repository.CartRepository;
import vn.com.misa.cukcukstarterclone.data.repository.MenuGroupRepository;
import vn.com.misa.cukcukstarterclone.data.repository.MenuItemRepository;
import vn.com.misa.cukcukstarterclone.di.Injector;
import vn.com.misa.cukcukstarterclone.ui.main.addorder.adapter.CartItemAdapter;
import vn.com.misa.cukcukstarterclone.ui.main.addorder.adapter.MenuGroupAdapter;
import vn.com.misa.cukcukstarterclone.ui.main.addorder.adapter.MenuItemAdapter;
import vn.com.misa.cukcukstarterclone.ui.main.addorder.dto.CartItemDto;
import vn.com.misa.cukcukstarterclone.ui.main.addorder.dto.MenuGroupDto;
import vn.com.misa.cukcukstarterclone.ui.main.addorder.dto.MenuItemDto;
import vn.com.misa.cukcukstarterclone.utils.Utils;

import static vn.com.misa.cukcukstarterclone.ui.main.addcartitem.NewCartItemFragment.ARG_NEW_CART_ITEM;
import static vn.com.misa.cukcukstarterclone.ui.main.addcartitem.NewCartItemFragment.KEY_BUNDLE_NEW_CART_ITEM;
import static vn.com.misa.cukcukstarterclone.ui.main.checkout.CheckoutFragment.KEY_ADD_ORDER_SUCCESS;

/**
 * @created_by KhanhNQ on 25-Jan-21
 */
public class AddOrderFragment extends BaseFragment<AddOrderContract.View, AddOrderPresenter>
        implements AddOrderContract.View {

    public static final String TAG = "AddOrderFragment";

    private static final String ARG_CART = "cart";

    public static final String KEY_ADD_CART_SUCCESS = "KEY_ADD_CART_SUCCESS";

    private final int FLAG_CREATE_NEW_CART = 0;
    private final int FLAG_UPDATE_CART = 1;
    private int FLAG_CART_TYPE = -1;

    private boolean isCheckout = false;

    private Cart mCart;
    private List<CartItem> mCartItems = new ArrayList<>();

    private ImageView ivEmptyTableNumber;
    private TextView tvTableNumber;
    private ImageView ivTakeAway;
    private ImageView ivPromotion;
    private TextView tvTotalAmount;
    private ImageView ivOrderList;
    private TextView tvItemCount;

    private LinearLayout llCartQuantity;
    private RecyclerView rcvCartItem;
    private TextView tvCartQuantity;
    private TextView tvCartTotal;

    private final CartItemAdapter cartItemAdapter = new CartItemAdapter();

    private AddOrderPresenter mPresenter;

    private final MenuGroupAdapter groupAdapter = new MenuGroupAdapter();
    private final MenuItemAdapter itemAdapter = new MenuItemAdapter();

    private String currentGroupId;
    private List<MenuItemDto> menuItems = new ArrayList<>();

    private DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

    private IAddOrderFragmentListener callback;

    public AddOrderFragment() {
    }

    public static AddOrderFragment newInstance(Cart cart, boolean isCheckout) {
        AddOrderFragment fragment = new AddOrderFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_CART, cart);
        fragment.setArguments(args);
        fragment.isCheckout = isCheckout;
        return fragment;
    }

    public void setCallback(IAddOrderFragmentListener callback) {
        this.callback = callback;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCart = getArguments().getParcelable(ARG_CART);
        }

        getParentFragmentManager().setFragmentResultListener(KEY_BUNDLE_NEW_CART_ITEM, this, (requestKey, bundle) -> {
            CartItem cartItem = bundle.getParcelable(ARG_NEW_CART_ITEM);
            onAddNewCartItem(cartItem);
        });

        getParentFragmentManager().setFragmentResultListener(KEY_ADD_ORDER_SUCCESS, this, (requestKey, bundle) -> {
            addCartItemsDone();
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_add_order;
    }

    /**
     * - Mục đích method: Ánh xạ View
     *
     * @param view View để ánh xạ
     * @created_by KhanhNQ on 25-Jan-21
     */
    @Override
    protected void bindViews(View view) {
        try {
            Toolbar toolbar = view.findViewById(R.id.toolbar);
            toolbar.setNavigationIcon(R.drawable.ic_back);
            toolbar.setNavigationOnClickListener(v -> popFragment());

            RecyclerView rcvMenuGroup = view.findViewById(R.id.rcvMenuGroup);
            RecyclerView rcvMenuItem = view.findViewById(R.id.rcvMenuItem);
            ivEmptyTableNumber = view.findViewById(R.id.ivEmptyTableNumber);
            tvTableNumber = view.findViewById(R.id.tvTableNumber);
            ivTakeAway = view.findViewById(R.id.ivTakeAway);
            ivPromotion = view.findViewById(R.id.ivPromotion);
            tvTotalAmount = view.findViewById(R.id.tvTotalAmount);
            ivOrderList = view.findViewById(R.id.ivOrderList);
            tvItemCount = view.findViewById(R.id.tvItemCount);
            Button btnSave = view.findViewById(R.id.btnSave);
            Button btnCheckout = view.findViewById(R.id.btnCheckout);

            llCartQuantity = view.findViewById(R.id.llCartQuantity);
            rcvCartItem = view.findViewById(R.id.rcvCartItem);
            tvCartQuantity = view.findViewById(R.id.tvCartQuantity);
            tvCartTotal = view.findViewById(R.id.tvCartTotal);

            cartItemAdapter.setCartItemActionListener(
                    (item, position) -> decreaseQuantity(item),
                    (item, position) -> increaseQuantity(item));
            rcvCartItem.setAdapter(cartItemAdapter);

            groupAdapter.setItemClickListener((item, position) -> selectGroup(item));
            rcvMenuGroup.setAdapter(groupAdapter);

            itemAdapter.setItemClickListener((item, position) -> addNewCartItem(item));
            rcvMenuItem.setAdapter(itemAdapter);

            btnSave.setOnClickListener(v -> saveCart());
            btnCheckout.setOnClickListener(v -> checkoutCart());

            ivEmptyTableNumber.setOnClickListener(v -> selectTable());
            tvTableNumber.setOnClickListener(v -> selectTable());
            ivTakeAway.setOnClickListener(v -> changeType());

            ivOrderList.setOnClickListener(v -> openListCart());
        } catch (Exception e) {
            Utils.handleException(e);
        }

        initPresenter();
    }

    private void openListCart() {
        try {
            isCheckout = !isCheckout;
            if (isCheckout) {
                llCartQuantity.setVisibility(View.VISIBLE);
                TranslateAnimation animate = new TranslateAnimation(
                        0,                 // fromXDelta
                        0,                 // toXDelta
                        llCartQuantity.getHeight(),  // fromYDelta
                        0);                // toYDelta
                animate.setDuration(400);
                llCartQuantity.startAnimation(animate);
            } else {
                llCartQuantity.setVisibility(View.INVISIBLE);
                TranslateAnimation animate = new TranslateAnimation(
                        0,                 // fromXDelta
                        0,                 // toXDelta
                        0,                 // fromYDelta
                        llCartQuantity.getHeight()); // toYDelta
                animate.setDuration(200);
                llCartQuantity.startAnimation(animate);
            }
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    /**
     * - Mục đích method: Xử lý sự kiện người dùng bấm nút tăng số lượng
     *
     * @param item Món ăn được tăng số lượng
     * @created_by KhanhNQ on 25-Jan-21
     */
    private void increaseQuantity(CartItemDto item) {
        try {
            item.setQuantity(item.getQuantity() + 1);
            cartItemAdapter.updateItem(item);
            for (CartItem cartItem : mCartItems) {
                if (item.getId().equals(cartItem.getId())) {
                    increase(cartItem);
                    break;
                }
            }
            updateView();
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    /**
     * - Mục đích method: Xử lý sự kiện người dùng bấm nút giảm số lượng
     *
     * @param item Món ăn bị giảm số lượng
     * @created_by KhanhNQ on 25-Jan-21
     */
    private void decreaseQuantity(CartItemDto item) {
        try {
            int quantity = item.getQuantity();
            if (quantity > 1) {
                item.setQuantity(item.getQuantity() - 1);
                cartItemAdapter.updateItem(item);
                for (CartItem cartItem : mCartItems) {
                    if (item.getId().equals(cartItem.getId())) {
                        decrease(cartItem);
                        break;
                    }
                }
                updateView();
            }
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    /**
     * - Mục đích method: Xử lý logic khi tăng số lượng item
     *
     * @created_by KhanhNQ on 25-Jan-21
     */
    private void increase(CartItem item) {
        try {
            item.setQuantity(item.getQuantity() + 1);
            for (MenuItemDto menuItem : menuItems) {
                if (menuItem.getId().equals(item.getProductId())) {
                    item.setAmount(item.getAmount() + menuItem.getPrice());
                    item.setPrice(item.getPrice() + menuItem.getPrice());
                    break;
                }
            }
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    /**
     * - Mục đích method: Xử lý logic khi giảm số lượng item
     *
     * @created_by KhanhNQ on 25-Jan-21
     */
    private void decrease(CartItem item) {
        try {
            item.setQuantity(item.getQuantity() - 1);
            for (MenuItemDto menuItem : menuItems) {
                if (menuItem.getId().equals(item.getProductId())) {
                    item.setAmount(item.getAmount() - menuItem.getPrice());
                    item.setPrice(item.getPrice() - menuItem.getPrice());
                    break;
                }
            }
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    /**
     * - Mục đích method: Chọn số bàn
     *
     * @created_by KhanhNQ on 28-Jan-21
     */
    private void selectTable() {
        try {
            AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
            alert.setTitle("Nhập số bàn");
            final EditText input = new EditText(getContext());
            input.setInputType(InputType.TYPE_CLASS_NUMBER);
            input.setRawInputType(Configuration.KEYBOARD_12KEY);
            alert.setView(input);
            alert.setPositiveButton("Đồng ý", (dialog, whichButton) -> {
                int number = Integer.parseInt(input.getText().toString());
                number = Math.max(number, 0);
                mCart.setTableNumber(number);

                if (number > 0) {
                    ivEmptyTableNumber.setVisibility(View.INVISIBLE);
                    tvTableNumber.setVisibility(View.VISIBLE);
                    tvTableNumber.setText(String.valueOf(number));
                } else {
                    ivEmptyTableNumber.setVisibility(View.VISIBLE);
                    tvTableNumber.setVisibility(View.INVISIBLE);
                }
            });

            alert.setNegativeButton("Huỷ", (dialog, whichButton) -> {
                dialog.dismiss();
            });
            if (mCart.getTableNumber() > 0)
                alert.setNeutralButton("Xoá số bàn", (dialogInterface, i) -> {
                    mCart.setTableNumber(0);
                    ivEmptyTableNumber.setVisibility(View.VISIBLE);
                    tvTableNumber.setVisibility(View.INVISIBLE);
                });

            alert.show();
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    /**
     * - Mục đích method: Chuyển đổi kiểu của Cart giữa ORDER và TAKE AWAY
     *
     * @created_by KhanhNQ on 28-Jan-21
     */
    private void changeType() {
        try {
            switch (mCart.getType()) {
                case ORDER:
                    mCart.setType(CartType.TAKE_AWAY);
                    ivTakeAway.setBackgroundResource(R.drawable.take_away_fill);
                    break;
                case TAKE_AWAY:
                    mCart.setType(CartType.ORDER);
                    ivTakeAway.setBackgroundResource(R.drawable.take_away);
                    break;
            }
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    /**
     * - Mục đích method: Khởi tạo presenter
     *
     * @created_by KhanhNQ on 25-Jan-21
     */
    private void initPresenter() {
        try {
            MenuGroupRepository menuGroupRepository = Injector.getMenuGroupRepository(requireContext());
            MenuItemRepository menuItemRepository = Injector.getMenuItemRepository(requireContext());
            CartRepository cartRepository = Injector.getCartRepository(requireContext());
            CartItemRepository cartItemRepository = Injector.getCartItemRepository(requireContext());
            mPresenter = new AddOrderPresenter(menuGroupRepository, menuItemRepository, cartRepository, cartItemRepository);

            mPresenter.attach(this);
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    @Override
    protected void initData() {
        try {
            mPresenter.getAllGroups();
            mPresenter.getAllItems();

            if (mCart != null) {
                FLAG_CART_TYPE = FLAG_UPDATE_CART;
                mPresenter.getCartItems(mCart.getId());
                tvTotalAmount.setText(decimalFormat.format(mCart.getTotalAmount()));
                tvCartTotal.setText(decimalFormat.format(mCart.getTotalAmount()));
                switch (mCart.getType()) {
                    case ORDER:
                        ivTakeAway.setBackgroundResource(R.drawable.take_away);
                        break;
                    case TAKE_AWAY:
                        ivTakeAway.setBackgroundResource(R.drawable.take_away_fill);
                        break;
                }

                if (mCart.getTableNumber() > 0) {
                    ivEmptyTableNumber.setVisibility(View.INVISIBLE);
                    tvTableNumber.setVisibility(View.VISIBLE);
                    tvTableNumber.setText(String.valueOf(mCart.getTableNumber()));
                } else {
                    ivEmptyTableNumber.setVisibility(View.VISIBLE);
                    tvTableNumber.setVisibility(View.INVISIBLE);
                }
            } else {
                FLAG_CART_TYPE = FLAG_CREATE_NEW_CART;
                mCart = new Cart();
            }

            updateCartItemCountIndicator();

            if (isCheckout) {
                llCartQuantity.setVisibility(View.VISIBLE);
            }
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    /**
     * - Mục đích method: Cập nhật số lượng món ăn trong giỏ hàng
     *
     * @created_by KhanhNQ on 25-Jan-21
     */
    private void updateCartItemCountIndicator() {
        try {
            int itemCount = 0;
            for (CartItem item : mCartItems) {
                itemCount += item.getQuantity();
            }
            tvCartQuantity.setText(decimalFormat.format(itemCount));
            if (itemCount > 0) {
                tvItemCount.setVisibility(View.VISIBLE);
                tvItemCount.setText(decimalFormat.format(itemCount));
            } else {
                tvItemCount.setVisibility(View.INVISIBLE);
            }
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    /**
     * - Mục đích method: Lưu lại Cart
     *
     * @created_by KhanhNQ on 27-Jan-21
     */
    private void saveCart() {
        try {
            if (mCart.getTotalPrice() > 0) {
                mCart.setStatus(Cart.CartStatus.WAITING);
                switch (FLAG_CART_TYPE) {
                    case FLAG_CREATE_NEW_CART:
                        mPresenter.addNewCart(mCart);
                        break;
                    case FLAG_UPDATE_CART:
                        mPresenter.updateCart(mCart);
                        break;
                }
            }
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    /**
     * - Mục đích method: Thanh toán đơn hàng
     *
     * @created_by KhanhNQ on 27-Jan-21
     */
    private void checkoutCart() {
        try {
            if (mCart.getTotalPrice() > 0) callback.checkout(mCart);
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    /**
     * - Mục đích method: Xử lý sự kiện chọn group của người dùng
     *
     * @param item group mà người dùng chọn
     * @created_by KhanhNQ on 25-Jan-21
     */
    private void selectGroup(MenuGroupDto item) {
        try {
            groupAdapter.updateSelectedGroup(currentGroupId, item.getId());
            currentGroupId = item.getId();
            mPresenter.filterItems(currentGroupId, menuItems);
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    /**
     * - Mục đích method: Xử lý sự kiện chạm vào MenuItem của người dùng
     *
     * @param item MenuItem mà người dùng chọn
     * @created_by KhanhNQ on 25-Jan-21
     */
    private void addNewCartItem(MenuItemDto item) {
        try {
            callback.addNewItemCart(item, mCart.getId());
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    /**
     * - Mục đích method: Update thông tin danh sách MenuGroup lên view
     *
     * @param groups danh sách groups đã được load từ presenter
     * @created_by KhanhNQ on 25-Jan-21
     */
    @Override
    public void updateMenuGroup(List<MenuGroupDto> groups) {
        try {
            groupAdapter.loadItems(groups);
            currentGroupId = groups.get(0).getId();
            groupAdapter.updateSelectedGroup(currentGroupId, currentGroupId);
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    /**
     * - Mục đích method: Hiển thị danh sách MenuItemDto lên view
     *
     * @param items danh sách MenuItemDto đã được load
     * @created_by KhanhNQ on 25-Jan-21
     */
    @Override
    public void loadMenuItems(List<MenuItemDto> items) {
        try {
            this.menuItems = items;
            mPresenter.filterItems(currentGroupId, menuItems);
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    /**
     * - Mục đích method: Cập nhật danh sách MenuItemDto
     *
     * @param items danh sách MenuItemDto đã được load
     * @created_by KhanhNQ on 25-Jan-21
     */
    @Override
    public void updateMenuItem(List<MenuItemDto> items) {
        try {
            itemAdapter.loadItems(items);
            itemAdapter.updateCartItems(mCartItems);
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    /**
     * - Mục đích method: Cập nhật danh sách CartItem
     *
     * @param cartItems danh sách CartItem
     * @created_by KhanhNQ on 25-Jan-21
     */
    @Override
    public void updateCartItems(List<CartItem> cartItems) {
        try {
            mCartItems = cartItems;
            itemAdapter.updateCartItems(cartItems);
            updateView();
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    @Override
    public void updateCartItemDtoList(List<CartItemDto> itemDtoList) {
        try {
            cartItemAdapter.loadItems(itemDtoList);
            cartItemAdapter.updateCartItemInfo(menuItems);
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    /**
     * - Mục đích method: Tiếp tục thêm mới các item trong Cart
     * - Sử dụng khi: được gọi từ Presenter để thông báo với View đã thêm mới Cart thành công
     *
     * @created_by KhanhNQ on 27-Jan-21
     */
    @Override
    public void addCartSuccess() {
        try {
            mPresenter.addCartItems(mCartItems);
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    /**
     * - Mục đích method: Cập nhật giao diện sau khi thêm mới CartItems thành công vào CSDL
     *
     * @created_by KhanhNQ on 27-Jan-21
     */
    @Override
    public void addCartItemsDone() {
        try {
            getParentFragmentManager().setFragmentResult(KEY_ADD_CART_SUCCESS, new Bundle());
            getParentFragmentManager().popBackStack(TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    /**
     * - Mục đích method: Xử lý sau khi thêm mới một CartItem
     *
     * @param cartItem CartItem đã được thêm mới
     * @created_by KhanhNQ on 25-Jan-21
     */
    private void onAddNewCartItem(CartItem cartItem) {
        try {
            mCartItems.add(cartItem);

            cartItemAdapter.insertItem(cartItem, menuItems);
            updateTitle();
            mCart.setTotalPrice(mCart.getTotalPrice() + cartItem.getPrice());
            mCart.setTotalAmount(mCart.getTotalAmount() + cartItem.getAmount());
            mCart.setDiscount(mCart.getTotalPrice() - mCart.getTotalAmount());

            updateView();
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    private void updateTitle() {
        try {
            HashMap<String, Integer> hashMap = new HashMap<>();
            for (int i = 0; i < mCartItems.size(); i++) {
                for (int j = 0; j < menuItems.size(); j++) {
                    if (mCartItems.get(i).getProductId().equals(menuItems.get(j).getId())) {
                        String key = menuItems.get(j).getName();
                        int value = mCartItems.get(i).getQuantity();
                        if (hashMap.containsKey(key)) {
                            hashMap.put(key, hashMap.get(key) + value);
                        } else {
                            hashMap.put(key, value);
                        }
                    }
                }

            }
            StringBuilder stringBuilder = new StringBuilder();
            for (Map.Entry element : hashMap.entrySet()) {
                stringBuilder.append(element.getValue()).append(" ").append(element.getKey()).append(", ");
            }

            String title = stringBuilder.toString();
            mCart.setTitle(title.substring(0, title.length() - 2));
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    /**
     * - Mục đích method: Cập nhật thông tin trên View
     *
     * @created_by KhanhNQ on 25-Jan-21
     */
    private void updateView() {
        float totalAmount = 0;
        float totalPrice = 0;
        for (CartItem cartItem : mCartItems) {
            totalAmount += cartItem.getAmount();
            totalPrice += cartItem.getPrice();
        }
        mCart.setTotalPrice(totalPrice);
        mCart.setTotalAmount(totalAmount);
        mCart.setDiscount(totalPrice - totalAmount);
        updateTitle();

        tvTotalAmount.setText(decimalFormat.format(mCart.getTotalAmount()));
        tvCartTotal.setText(decimalFormat.format(mCart.getTotalAmount()));
        itemAdapter.updateCartItems(mCartItems);
        groupAdapter.updateCartItems(mCartItems, menuItems);

        updateCartItemCountIndicator();
    }

    private void popFragment() {
        try {
            getParentFragmentManager().popBackStack(TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mPresenter.detach();
    }

    public interface IAddOrderFragmentListener {
        void addNewItemCart(MenuItemDto itemDto, String cartId);

        void checkout(Cart cart);
    }
}

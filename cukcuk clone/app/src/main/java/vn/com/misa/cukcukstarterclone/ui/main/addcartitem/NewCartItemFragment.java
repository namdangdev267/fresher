package vn.com.misa.cukcukstarterclone.ui.main.addcartitem;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonToggleGroup;

import java.text.DecimalFormat;

import vn.com.misa.cukcukstarterclone.R;
import vn.com.misa.cukcukstarterclone.data.model.CartItem;
import vn.com.misa.cukcukstarterclone.data.model.MenuItem;
import vn.com.misa.cukcukstarterclone.data.repository.CartItemRepository;
import vn.com.misa.cukcukstarterclone.data.repository.MenuItemRepository;
import vn.com.misa.cukcukstarterclone.di.Injector;
import vn.com.misa.cukcukstarterclone.ui.main.addorder.dto.MenuItemDto;
import vn.com.misa.cukcukstarterclone.utils.Utils;

/**
 * @created_by KhanhNQ on 27-Jan-21
 */
public class NewCartItemFragment extends BottomSheetDialogFragment implements NewCartItemContract.View {

    public static final String TAG = "NewCartItemFragment";

    public static final String KEY_BUNDLE_NEW_CART_ITEM = "key_bundle";
    public static final String ARG_NEW_CART_ITEM = "new_cart_item";

    private static final String ARG_ITEM = "ARG_ITEM";
    private static final String ARG_CART_ID = "ARG_CART_ID";

    private TextView tvItemName;
    private TextView tvItemPrice;
    private TextView tvCartItemQuantity;
    private MaterialButtonToggleGroup toggleGroupPromotion;
    private EditText edtPromotion;
    private EditText edtNote;

    private int mQuantity = 1;

    private NewCartItemPresenter mPresenter;

    private MenuItemDto menuItemDto;
    private String cartId;

    public static NewCartItemFragment newInstance(MenuItemDto menuItemDto, String cartId) {
        NewCartItemFragment fragment = new NewCartItemFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_ITEM, menuItemDto);
        args.putString(ARG_CART_ID, cartId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.BottomSheetDialogStyle);

        if (null != getArguments()) {
            menuItemDto = getArguments().getParcelable(ARG_ITEM);
            cartId = getArguments().getString(ARG_CART_ID);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_new_cart_item, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);
        initData();
    }

    /**
     * - Mục đích method: Ánh xạ View, khởi tạo một số đối tượng
     *
     * @param view Đối tượng View để ánh xạ
     * @created_by KhanhNQ on 27-Jan-21
     */
    private void initView(View view) {
        try {
            tvItemName = view.findViewById(R.id.tvItemName);
            tvItemPrice = view.findViewById(R.id.tvItemPrice);
            tvCartItemQuantity = view.findViewById(R.id.tvCartItemQuantity);
            ImageView ivMinus = view.findViewById(R.id.ivMinus);
            ImageView ivPlus = view.findViewById(R.id.ivPlus);
            toggleGroupPromotion = view.findViewById(R.id.toggleGroupPromotion);
            edtPromotion = view.findViewById(R.id.edtPromotion);
            MaterialButton btnPromotionCash = view.findViewById(R.id.btnAtmCard);
            MaterialButton btnPromotionPercent = view.findViewById(R.id.btnCash);
            edtNote = view.findViewById(R.id.edtNote);
            TextView tvCancel = view.findViewById(R.id.tvCancel);
            TextView tvDone = view.findViewById(R.id.tvDone);

            ivMinus.setOnClickListener(v -> decreaseQuantity());
            ivPlus.setOnClickListener(v -> increaseQuantity());

            tvCancel.setOnClickListener(v -> dismiss());
            tvDone.setOnClickListener(v -> validate());

            edtPromotion.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (charSequence.length() == 0) {
                        edtPromotion.setText("0");
                        edtPromotion.requestFocus();
                        edtPromotion.selectAll();
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {
                }
            });
        } catch (Exception e) {
            Utils.handleException(e);
        }

        initPresenter();
    }

    /**
     * - Mục đích method: Khởi tạo presenter
     *
     * @created_by KhanhNQ on 27-Jan-21
     */
    private void initPresenter() {
        try {
            MenuItemRepository menuItemRepository = Injector.getMenuItemRepository(requireContext());
            CartItemRepository cartItemRepository = Injector.getCartItemRepository(requireContext());

            mPresenter = new NewCartItemPresenter(menuItemRepository, cartItemRepository);
            mPresenter.attach(this);
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    /**
     * - Mục đích method: Khởi tạo dữ liệu
     *
     * @created_by KhanhNQ on 27-Jan-21
     */
    private void initData() {
        try {
            mPresenter.getProductInfo(menuItemDto.getId());
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    /**
     * - Mục đích method: Giảm số lượng sản phẩm hiện tại đi 1
     *
     * @created_by KhanhNQ on 27-Jan-21
     */
    private void decreaseQuantity() {
        try {
            mQuantity -= mQuantity > 1 ? 1 : 0;
            tvCartItemQuantity.setText(String.valueOf(mQuantity));
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    /**
     * - Mục đích method: Tăng số lượng sản phẩm hiện tại lên 1
     *
     * @created_by KhanhNQ on 27-Jan-21
     */
    private void increaseQuantity() {
        try {
            mQuantity += 1;
            tvCartItemQuantity.setText(String.valueOf(mQuantity));
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    /**
     * - Mục đích method: Lấy ra số tiền được giảm
     *
     * @return số tiền được giảm
     * @created_by KhanhNQ on 27-Jan-21
     */
    @SuppressLint("NonConstantResourceId")
    private float getDiscount(float totalPrice) {
        float promotion = 0;
        switch (toggleGroupPromotion.getCheckedButtonId()) {
            case R.id.btnCash:
                float percent = Float.parseFloat(edtPromotion.getText().toString());
                promotion = totalPrice * percent / 100;
                break;
            case R.id.btnAtmCard:
                promotion = Float.parseFloat(edtPromotion.getText().toString());
        }
        return promotion;
    }

    /**
     * - Mục đích method: Kiểm tra tính hợp lệ của việc thêm món vào giỏ
     *
     * @created_by KhanhNQ on 27-Jan-21
     */
    private void validate() {
        try {
            float totalPrice = menuItemDto.getPrice() * mQuantity;
            float discount = getDiscount(totalPrice);
            if (discount >= totalPrice) {
                showErrorMessage(R.string.invalid_promotion);
            } else {
                CartItem cartItem = new CartItem(mQuantity, totalPrice - discount, totalPrice, edtNote.getText().toString(), menuItemDto.getId(), cartId);
                saveNewCartItem(cartItem);
            }
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    /**
     * - Mục đích method: Lưu một CartItem mới vào CSDL
     *
     * @param cartItem CartItem cần lưu vào CSDL
     * @created_by KhanhNQ on 27-Jan-21
     */
    private void saveNewCartItem(CartItem cartItem) {
        try {
            mPresenter.saveNewCartItem(cartItem);
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    /**
     * - Mục đích method: cập nhật thông tin sản phẩm
     *
     * @param item sản phẩm cần cập nhật
     * @created_by KhanhNQ on 27-Jan-21
     */
    @Override
    public void updateProductInfo(MenuItem item) {
        try {
            tvItemName.setText(item.getName());
            tvItemPrice.setText(new DecimalFormat("###,###,###").format(item.getPrice()));
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    /**
     * - Mục đích method: update UI sau khi thêm vào giỏ hàng thành công
     *
     * @param cartItem thông tin CartItem đã thêm thành công
     * @created_by KhanhNQ on 27-Jan-21
     */
    @Override
    public void onAddNewCartSuccess(CartItem cartItem) {
        try {
            Bundle result = new Bundle();
            result.putParcelable(ARG_NEW_CART_ITEM, cartItem);
            getParentFragmentManager().setFragmentResult(KEY_BUNDLE_NEW_CART_ITEM, result);
            dismiss();
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    @Override
    public void showErrorMessage(String msg) {
        try {
            if (null != getContext()) {
                Toast toast = Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP | Gravity.FILL_HORIZONTAL, 0, 0);
                View toastView = toast.getView();
                toastView.setBackgroundResource(R.drawable.bg_default_toast_error);
                TextView v = toastView.findViewById(android.R.id.message);
                v.setTextColor(Color.WHITE);
                toast.show();
            }
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    @Override
    public void showErrorMessage(int resId) {
        try {
            if (null != getContext()) {
                Toast toast = Toast.makeText(getContext(), getResources().getString(resId), Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP | Gravity.FILL_HORIZONTAL, 0, 0);
                View toastView = toast.getView();
                toastView.setBackgroundResource(R.drawable.bg_default_toast_error);
                TextView v = toastView.findViewById(android.R.id.message);
                v.setTextColor(Color.WHITE);
                toast.show();
            }
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    @Override
    public void showNormalMessage(String msg) {
        try {
            Toast toast = Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP | Gravity.FILL_HORIZONTAL, 0, 0);
            View toastView = toast.getView();
            toastView.setBackgroundResource(R.drawable.bg_default_toast);
            TextView v = toastView.findViewById(android.R.id.message);
            v.setTextColor(Color.WHITE);
            toast.show();
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    @Override
    public void showNormalMessage(int resId) {
        try {
            Toast toast = Toast.makeText(getContext(), getResources().getString(resId), Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP | Gravity.FILL_HORIZONTAL, 0, 0);
            View toastView = toast.getView();
            toastView.setBackgroundResource(R.drawable.bg_default_toast);
            TextView v = toastView.findViewById(android.R.id.message);
            v.setTextColor(Color.WHITE);
            toast.show();
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    @Override
    public void showWarningMessage(String msg) {
        try {
            Toast toast = Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP | Gravity.FILL_HORIZONTAL, 0, 0);
            View toastView = toast.getView();
            toastView.setBackgroundResource(R.drawable.bg_default_toast_warning);
            TextView v = toastView.findViewById(android.R.id.message);
            v.setTextColor(Color.WHITE);
            toast.show();
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    @Override
    public void showWarningMessage(int resId) {
        try {
            Toast toast = Toast.makeText(getContext(), getResources().getString(resId), Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP | Gravity.FILL_HORIZONTAL, 0, 0);
            View toastView = toast.getView();
            toastView.setBackgroundResource(R.drawable.bg_default_toast_warning);
            TextView v = toastView.findViewById(android.R.id.message);
            v.setTextColor(Color.WHITE);
            toast.show();
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        mPresenter.detach();
    }
}

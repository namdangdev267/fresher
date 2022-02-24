package vn.com.misa.cukcukstarterclone.ui.main.listorders;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import vn.com.misa.cukcukstarterclone.R;
import vn.com.misa.cukcukstarterclone.base.BaseFragment;
import vn.com.misa.cukcukstarterclone.data.model.Cart;
import vn.com.misa.cukcukstarterclone.data.repository.CartRepository;
import vn.com.misa.cukcukstarterclone.di.Injector;
import vn.com.misa.cukcukstarterclone.ui.main.listorders.adapter.CartAdapter;
import vn.com.misa.cukcukstarterclone.utils.Utils;

import static vn.com.misa.cukcukstarterclone.ui.main.addorder.AddOrderFragment.KEY_ADD_CART_SUCCESS;

/**
 * @created_by KhanhNQ on 25-Jan-21
 */
public class ListOrdersFragment extends BaseFragment<ListOrdersContract.View, ListOrdersPresenter>
        implements ListOrdersContract.View {

    private final CartAdapter cartAdapter = new CartAdapter();

    private ListOrdersPresenter mPresenter;

    private ConstraintLayout clNoItem;

    private IListOrdersFragmentCallback callback;

    public ListOrdersFragment() {
    }

    public void setCallback(IListOrdersFragmentCallback callback) {
        this.callback = callback;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getParentFragmentManager().setFragmentResultListener(KEY_ADD_CART_SUCCESS, this, (requestKey, bundle) -> {
            initData();
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_list_orders;
    }

    @Override
    protected void bindViews(View view) {
        try {
            RecyclerView rcvOrder = view.findViewById(R.id.rcvOrder);
            clNoItem = view.findViewById(R.id.clNoItem);
            FloatingActionButton fabAddOrder = view.findViewById(R.id.fabAddOrder);

            cartAdapter.setItemClickListener((item, position) -> callback.showCartDetails(item));
            cartAdapter.setCartActionListener(
                    (item, position) -> cancelCart(item),
                    (item, position) -> checkoutCart(item)
            );
            rcvOrder.setAdapter(cartAdapter);

            fabAddOrder.setOnClickListener(v -> startNewCart());
        } catch (Exception e) {
            Utils.handleException(e);
        }

        initPresenter();
    }

    private void initPresenter() {
        try {
            CartRepository cartRepository = Injector.getCartRepository(requireContext());

            mPresenter = new ListOrdersPresenter(cartRepository);
            mPresenter.attach(this);
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    @Override
    protected void initData() {
        try {
            mPresenter.getAllCarts();
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    @Override
    public void updateData(List<Cart> carts) {
        try {
            cartAdapter.loadItems(carts);

            if (carts.size() == 0) {
                clNoItem.setVisibility(View.VISIBLE);
            } else {
                clNoItem.setVisibility(View.GONE);
            }
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    @Override
    public void deleteCartSuccess(Cart cart) {
        try {
            cartAdapter.removeItem(cart);
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mPresenter.detach();
    }

    private void cancelCart(Cart cart) {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());

            final View customLayout = getLayoutInflater().inflate(R.layout.dialog_delete_cart, null);
            TextView tvNo = customLayout.findViewById(R.id.tvNo);
            TextView tvYes = customLayout.findViewById(R.id.tvYes);

            builder.setView(customLayout);
            AlertDialog dialog = builder.create();
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            tvNo.setOnClickListener(view -> dialog.dismiss());
            tvYes.setOnClickListener(view -> {
                deleteCart(cart);
                dialog.dismiss();
            });

            dialog.show();
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    private void deleteCart(Cart cart) {
        try {
            mPresenter.removeCart(cart);
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    private void checkoutCart(Cart cart) {
        try {
            callback.checkoutCart(cart);
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    private void startNewCart() {
        try {
            callback.startNewCart();
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    public interface IListOrdersFragmentCallback {
        void startNewCart();

        void showCartDetails(Cart cart);

        void checkoutCart(Cart cart);
    }
}

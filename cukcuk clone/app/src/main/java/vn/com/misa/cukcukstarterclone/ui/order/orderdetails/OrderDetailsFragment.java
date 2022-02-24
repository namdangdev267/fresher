package vn.com.misa.cukcukstarterclone.ui.order.orderdetails;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import vn.com.misa.cukcukstarterclone.R;
import vn.com.misa.cukcukstarterclone.base.BaseFragment;
import vn.com.misa.cukcukstarterclone.data.repository.CartItemRepository;
import vn.com.misa.cukcukstarterclone.data.repository.MenuItemRepository;
import vn.com.misa.cukcukstarterclone.di.Injector;
import vn.com.misa.cukcukstarterclone.ui.order.dto.CartItemDto;
import vn.com.misa.cukcukstarterclone.ui.order.dto.OrderDto;
import vn.com.misa.cukcukstarterclone.ui.order.orderdetails.adapter.CartItemAdapter;
import vn.com.misa.cukcukstarterclone.utils.Utils;

public class OrderDetailsFragment extends BaseFragment<OrderDetailsFragmentContract.View, OrderDetailsFragmentPresenter>
        implements OrderDetailsFragmentContract.View {

    private static final String ARG_ORDER_DTO = "argOrderDto";

    private OrderDto orderDto;

    private TextView tvBillId;
    private TextView tvBillDate;
    private TextView tvTotalQuantity;
    private TextView tvTotalAmount;
    private LinearLayout llPromotion;
    private TextView tvDiscount;
    private TextView tvAmount;
    private TextView tvCustomerPay;
    private TextView tvChanges;

    private final CartItemAdapter cartItemAdapter = new CartItemAdapter();

    private OrderDetailsFragmentPresenter mPresenter;

    private int quantity = 0;

    public OrderDetailsFragment() {
    }

    public static OrderDetailsFragment newInstance(OrderDto orderDto) {
        OrderDetailsFragment fragment = new OrderDetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_ORDER_DTO, orderDto);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            orderDto = getArguments().getParcelable(ARG_ORDER_DTO);
        }
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_order_details;
    }

    @Override
    protected void bindViews(View view) {
        try {
            Toolbar toolbar = view.findViewById(R.id.toolbar);
            toolbar.setNavigationIcon(R.drawable.ic_back);
            toolbar.setNavigationOnClickListener(v -> {
                requireActivity().getSupportFragmentManager().popBackStack();
            });

            tvBillId = view.findViewById(R.id.tvBillId);
            tvBillDate = view.findViewById(R.id.tvBillDate);
            tvTotalQuantity = view.findViewById(R.id.tvTotalQuantity);
            tvTotalAmount = view.findViewById(R.id.tvTotalAmount);
            llPromotion = view.findViewById(R.id.llPromotion);
            tvDiscount = view.findViewById(R.id.tvDiscount);
            tvAmount = view.findViewById(R.id.tvAmount);
            tvCustomerPay = view.findViewById(R.id.tvCustomerPay);
            tvChanges = view.findViewById(R.id.tvChanges);
            RecyclerView rcvCartItem = view.findViewById(R.id.rcvCartItem);

            cartItemAdapter.setItemClickListener((item, position) -> {
            });
            rcvCartItem.setAdapter(cartItemAdapter);
        } catch (Exception e) {
            Utils.handleException(e);
        }

        initPresenter();
    }

    private void initPresenter() {
        try {
            CartItemRepository cartItemRepository = Injector.getCartItemRepository(requireContext());
            MenuItemRepository menuItemRepository = Injector.getMenuItemRepository(requireContext());

            mPresenter = new OrderDetailsFragmentPresenter(cartItemRepository, menuItemRepository);
            mPresenter.attach(this);
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    @Override
    protected void initData() {
        try {
            DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
            tvBillId.setText(orderDto.getOrderId());
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
            tvBillDate.setText(sdf.format(orderDto.getCreatedAt()));
            tvTotalAmount.setText(decimalFormat.format(orderDto.getTotalPrice()));
            float discount = orderDto.getDiscount();
            if (discount > 0) {
                llPromotion.setVisibility(View.VISIBLE);
                tvDiscount.setText(decimalFormat.format(discount));
            } else {
                llPromotion.setVisibility(View.GONE);
            }
            tvAmount.setText(decimalFormat.format(orderDto.getTotalAmount()));
            tvCustomerPay.setText(decimalFormat.format(orderDto.getCustomerPayment()));
            tvChanges.setText(decimalFormat.format(orderDto.getCustomerPayment() - orderDto.getTotalAmount()));
            ;
            mPresenter.getCartItems(orderDto.getCartId());
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    @Override
    public void insertCartItem(CartItemDto newCartItem) {
        try {
            cartItemAdapter.insertItem(newCartItem);
            quantity += newCartItem.getQuantity();
            tvTotalQuantity.setText(String.valueOf(quantity));
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

package vn.com.misa.cukcukstarterclone.ui.order;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.core.util.Pair;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.datepicker.MaterialDatePicker;

import java.util.List;

import vn.com.misa.cukcukstarterclone.R;
import vn.com.misa.cukcukstarterclone.base.BaseActivity;
import vn.com.misa.cukcukstarterclone.data.repository.CartRepository;
import vn.com.misa.cukcukstarterclone.data.repository.OrderRepository;
import vn.com.misa.cukcukstarterclone.di.Injector;
import vn.com.misa.cukcukstarterclone.ui.order.adapter.BillAdapter;
import vn.com.misa.cukcukstarterclone.ui.order.dto.OrderDto;
import vn.com.misa.cukcukstarterclone.ui.order.orderdetails.OrderDetailsFragment;
import vn.com.misa.cukcukstarterclone.utils.Utils;

public class OrderActivity extends BaseActivity<OrderContract.View, OrderPresenter>
        implements OrderContract.View {

    private TextView tvNoData;
    private TextView tvDateSelection;
    private Spinner spnDateSelection;
    RecyclerView rcvBill;

    private OrderPresenter mPresenter;

    private final BillAdapter billAdapter = new BillAdapter();

    @Override
    protected int getLayout() {
        return R.layout.activity_order;
    }

    @Override
    protected void bindViews() {
        try {
            Toolbar toolbar = findViewById(R.id.toolbar);
            toolbar.setNavigationIcon(R.drawable.ic_back);
            toolbar.setNavigationOnClickListener(v -> {
                finish();
            });

            tvNoData = findViewById(R.id.tvNoData);
            tvDateSelection = findViewById(R.id.tvDateSelection);
            spnDateSelection = findViewById(R.id.spnDateSelection);
            setUpSpinner();

            rcvBill = findViewById(R.id.rcvBill);

            billAdapter.setItemClickListener((item, position) -> showBillDetails(item));
            rcvBill.setAdapter(billAdapter);
        } catch (Exception e) {
            Utils.handleException(e);
        }

        initPresenter();
    }

    private void setUpSpinner() {
        String[] reportSelection = getResources().getStringArray(R.array.report_by);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, reportSelection);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnDateSelection.setAdapter(spinnerArrayAdapter);
        spnDateSelection.setSelection(0);
        spnDateSelection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                selectDateReport(pos);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    private void selectDateReport(int pos) {
        switch (pos) {
            case 1: // Hôm qua
                mPresenter.getYesterdayOrder();
                break;
            case 2: // Tuần này
                mPresenter.getThisWeekOrder();
                break;
            case 3: // Tuần trước
                mPresenter.getLastWeekOrder();
                break;
            case 4: // Tháng này
                mPresenter.getThisMonthOder();
                break;
            case 5: // Người dùng tự chọn khoảng thời gian
                MaterialDatePicker.Builder<Pair<Long, Long>> builder = MaterialDatePicker.Builder.dateRangePicker();
                MaterialDatePicker<Pair<Long, Long>> picker = builder.build();

                picker.show(getSupportFragmentManager(), picker.toString());
                picker.addOnPositiveButtonClickListener(selection -> mPresenter.getInRange(selection.first, selection.second));
                break;
            default: // Hôm nay
                mPresenter.getCurrentDayOrder();
                break;
        }
    }

    private void showBillDetails(OrderDto orderDto) {
        OrderDetailsFragment orderDetailsFragment = OrderDetailsFragment.newInstance(orderDto);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.root, orderDetailsFragment).addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void initPresenter() {
        try {
            CartRepository cartRepository = Injector.getCartRepository(this);
            OrderRepository orderRepository = Injector.getOrderRepository(this);

            mPresenter = new OrderPresenter(cartRepository, orderRepository);
            mPresenter.attach(this);
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    @Override
    protected void initData() {
        mPresenter.getAllOrder();
    }

    @Override
    public void addOrders(OrderDto item) {
        billAdapter.insertItem(item);
    }

    @Override
    public void loadOrders(List<OrderDto> items) {
        rcvBill.setVisibility(View.VISIBLE);
        tvNoData.setVisibility(View.GONE);
        billAdapter.loadItems(items);
    }

    @Override
    public void updateDateTitle(String date) {
        tvDateSelection.setText(date);
    }

    @Override
    public void showEmptyOrder() {
        rcvBill.setVisibility(View.GONE);
        tvNoData.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mPresenter.detach();
    }
}

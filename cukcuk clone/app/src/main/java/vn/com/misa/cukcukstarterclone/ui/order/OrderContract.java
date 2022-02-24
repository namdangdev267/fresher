package vn.com.misa.cukcukstarterclone.ui.order;

import java.util.List;

import vn.com.misa.cukcukstarterclone.base.BaseContract;
import vn.com.misa.cukcukstarterclone.ui.order.dto.OrderDto;

/**
 * @created_by KhanhNQ on 29-Jan-2021.
 */
public class OrderContract {
    interface View extends BaseContract.View {
        void addOrders(OrderDto item);

        void loadOrders(List<OrderDto> items);

        void updateDateTitle(String date);

        void showEmptyOrder();
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void getAllOrder();

        void getCurrentDayOrder();

        void getYesterdayOrder();

        void getThisWeekOrder();

        void getLastWeekOrder();

        void getThisMonthOder();

        void getInRange(long from, long to);
    }
}

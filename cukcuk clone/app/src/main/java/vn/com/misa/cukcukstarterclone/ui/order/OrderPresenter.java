package vn.com.misa.cukcukstarterclone.ui.order;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import vn.com.misa.cukcukstarterclone.data.IOnLoadedCallback;
import vn.com.misa.cukcukstarterclone.data.model.Cart;
import vn.com.misa.cukcukstarterclone.data.model.Order;
import vn.com.misa.cukcukstarterclone.data.repository.CartRepository;
import vn.com.misa.cukcukstarterclone.data.repository.OrderRepository;
import vn.com.misa.cukcukstarterclone.ui.order.dto.OrderDto;
import vn.com.misa.cukcukstarterclone.utils.Utils;

import static vn.com.misa.cukcukstarterclone.utils.Constants.DATE_DAY_FORMAT;
import static vn.com.misa.cukcukstarterclone.utils.Constants.FULL_DATE_FORMAT;
import static vn.com.misa.cukcukstarterclone.utils.Constants.SIMPLE_DATE_FORMAT;

/**
 * @created_by KhanhNQ on 29-Jan-2021.
 */
public class OrderPresenter implements OrderContract.Presenter {
    private OrderContract.View view;

    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;

    public OrderPresenter(CartRepository cartRepository, OrderRepository orderRepository) {
        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public void attach(OrderContract.View view) {
        this.view = view;
    }

    @Override
    public void detach() {
        this.view = null;
    }

    @Override
    public void getAllOrder() {
        orderRepository.getAllOrders(new IOnLoadedCallback<List<Order>>() {
            @Override
            public void onSuccess(List<Order> data) {
                loadCartItems(data);
            }

            @Override
            public void onFailure(Exception e) {
                view.showErrorMessage(e.getLocalizedMessage());
            }
        });
    }

    @Override
    public void getCurrentDayOrder() {
        String fullDate = Utils.dateFormat(FULL_DATE_FORMAT, new Date());
        view.updateDateTitle(fullDate);

        String date = Utils.dateFormat(DATE_DAY_FORMAT, new Date());

        getOneDayInfo(date);
    }

    @Override
    public void getYesterdayOrder() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        Date yesterday = cal.getTime();

        String fullDate = Utils.dateFormat(FULL_DATE_FORMAT, yesterday);
        view.updateDateTitle(fullDate);

        String date = Utils.dateFormat(DATE_DAY_FORMAT, yesterday);

        getOneDayInfo(date);
    }

    @Override
    public void getThisWeekOrder() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
        String firstDayOfTheWeek = Utils.dateFormat(SIMPLE_DATE_FORMAT, cal.getTime());
        String firstDay = Utils.dateFormat(DATE_DAY_FORMAT, cal.getTime());

        cal.add(Calendar.WEEK_OF_YEAR, 1);
        cal.add(Calendar.DATE, -1);
        String lastDayOfTheWeek = Utils.dateFormat(SIMPLE_DATE_FORMAT, cal.getTime());
        String lastDay = Utils.dateFormat(DATE_DAY_FORMAT, cal.getTime());

        view.updateDateTitle(firstDayOfTheWeek + " - " + lastDayOfTheWeek);

        getInfo(firstDay, lastDay);
    }

    @Override
    public void getLastWeekOrder() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.WEEK_OF_YEAR, -1);
        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
        String firstDayOfTheWeek = Utils.dateFormat(SIMPLE_DATE_FORMAT, cal.getTime());
        String firstDay = Utils.dateFormat(DATE_DAY_FORMAT, cal.getTime());

        cal.add(Calendar.WEEK_OF_YEAR, 1);
        cal.add(Calendar.DATE, -1);
        String lastDayOfTheWeek = Utils.dateFormat(SIMPLE_DATE_FORMAT, cal.getTime());
        String lastDay = Utils.dateFormat(DATE_DAY_FORMAT, cal.getTime());

        view.updateDateTitle(firstDayOfTheWeek + " - " + lastDayOfTheWeek);

        getInfo(firstDay, lastDay);
    }

    @Override
    public void getThisMonthOder() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        String firstDayOfTheMonth = Utils.dateFormat(SIMPLE_DATE_FORMAT, cal.getTime());
        String firstDay = Utils.dateFormat(DATE_DAY_FORMAT, cal.getTime());

        cal.add(Calendar.MONTH, 1);
        cal.add(Calendar.DATE, -1);
        String lastDayOfTheMonth = Utils.dateFormat(SIMPLE_DATE_FORMAT, cal.getTime());
        String lastDay = Utils.dateFormat(DATE_DAY_FORMAT, cal.getTime());

        view.updateDateTitle(firstDayOfTheMonth + " - " + lastDayOfTheMonth);

        getInfo(firstDay, lastDay);
    }

    @Override
    public void getInRange(long from, long to) {
        Date startDay = new Date(from);
        Date endDay = new Date(to);

        String firstDayInRange = Utils.dateFormat(SIMPLE_DATE_FORMAT, startDay);
        String lastDayInRange = Utils.dateFormat(SIMPLE_DATE_FORMAT, endDay);
        view.updateDateTitle(firstDayInRange + " - " + lastDayInRange);

        String firstDay = Utils.dateFormat(DATE_DAY_FORMAT, startDay);
        String lastDay = Utils.dateFormat(DATE_DAY_FORMAT, endDay);

        getInfo(firstDay, lastDay);
    }

    private void getOneDayInfo(String date) {
        orderRepository.getOrdersByDate(date + "to" + date, new IOnLoadedCallback<List<Order>>() {
            @Override
            public void onSuccess(List<Order> data) {
                if (data.size() != 0) {
                    loadCartItems(data);
                } else {
                    view.showEmptyOrder();
                }
            }

            @Override
            public void onFailure(Exception e) {
                view.showErrorMessage(e.getMessage());
            }
        });
    }

    private void getInfo(String from, String end) {
        orderRepository.getOrdersByDate(from + "to" + end, new IOnLoadedCallback<List<Order>>() {
            @Override
            public void onSuccess(List<Order> data) {
                if (data.size() != 0) {
                    loadCartItems(data);
                } else {
                    view.showEmptyOrder();
                }
            }

            @Override
            public void onFailure(Exception e) {
                view.showErrorMessage(e.getMessage());
            }
        });
    }

    private void loadCartItems(List<Order> orders) {
        List<OrderDto> orderDtos = new ArrayList<>();
        boolean isEnd = false;
        for (int i = 0; i < orders.size(); i++) {
            if (i != orders.size() - 1) {
                isEnd = true;
            }
            Order order = orders.get(i);
            boolean finalIsEnd = isEnd;
            cartRepository.getCartById(order.getCartId(), new IOnLoadedCallback<Cart>() {
                @Override
                public void onSuccess(Cart data) {
                    orderDtos.add(new OrderDto(data, order));
                    if (finalIsEnd) {
                        view.loadOrders(orderDtos);
                    }
                }

                @Override
                public void onFailure(Exception e) {
                    view.showErrorMessage(e.getMessage());
                    if (finalIsEnd) {
                        view.loadOrders(orderDtos);
                    }
                }
            });
        }
    }
}

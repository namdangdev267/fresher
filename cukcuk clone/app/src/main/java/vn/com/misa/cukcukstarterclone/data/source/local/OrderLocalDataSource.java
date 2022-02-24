package vn.com.misa.cukcukstarterclone.data.source.local;

import java.util.List;

import vn.com.misa.cukcukstarterclone.data.IOnLoadedCallback;
import vn.com.misa.cukcukstarterclone.data.LoadingAsyncTask;
import vn.com.misa.cukcukstarterclone.data.model.Order;
import vn.com.misa.cukcukstarterclone.data.source.IOrderDataSource;
import vn.com.misa.cukcukstarterclone.data.source.Unit;
import vn.com.misa.cukcukstarterclone.data.source.local.dao.IOrderDao;

/**
 * @created_by KhanhNQ on 28-Jan-2021.
 */
public class OrderLocalDataSource implements IOrderDataSource.Local {
    public static OrderLocalDataSource instance = null;

    private final IOrderDao orderDao;

    private OrderLocalDataSource(IOrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public static OrderLocalDataSource getInstance(IOrderDao orderDao) {
        if (null == instance) {
            instance = new OrderLocalDataSource(orderDao);
        }
        return instance;
    }

    @Override
    public void getAllOrders(IOnLoadedCallback<List<Order>> callback) {
        new LoadingAsyncTask<Unit, List<Order>>(callback, param -> orderDao.getAllOrders()).execute(new Unit());
    }

    @Override
    public void getOrdersByDate(String date, IOnLoadedCallback<List<Order>> callback) {
        new LoadingAsyncTask<String, List<Order>>(callback, param -> orderDao.getOrdersByDate(date)).execute(date);
    }

    @Override
    public void getOrderById(String id, IOnLoadedCallback<Order> callback) {
        new LoadingAsyncTask<String, Order>(callback, param -> orderDao.getOrderById(id)).execute(id);
    }

    @Override
    public void addNewOrder(Order newOrder, IOnLoadedCallback<Boolean> callback) {
        new LoadingAsyncTask<Order, Boolean>(callback, param -> orderDao.addNewOrder(newOrder)).execute(newOrder);
    }
}

package vn.com.misa.cukcukstarterclone.data.repository;

import java.util.List;

import vn.com.misa.cukcukstarterclone.data.IOnLoadedCallback;
import vn.com.misa.cukcukstarterclone.data.model.Order;
import vn.com.misa.cukcukstarterclone.data.source.IOrderDataSource;

/**
 * @created_by KhanhNQ on 28-Jan-2021.
 */
public class OrderRepository implements IOrderRepository {

    private final IOrderDataSource.Local localDataSource;

    public OrderRepository(IOrderDataSource.Local localDataSource) {
        this.localDataSource = localDataSource;
    }

    private static OrderRepository instance = null;

    public static OrderRepository getInstance(IOrderDataSource.Local localDataSource) {
        if (null == instance) {
            instance = new OrderRepository(localDataSource);
        }
        return instance;
    }

    @Override
    public void getAllOrders(IOnLoadedCallback<List<Order>> callback) {
        localDataSource.getAllOrders(callback);
    }

    @Override
    public void getOrdersByDate(String date, IOnLoadedCallback<List<Order>> callback) {
        localDataSource.getOrdersByDate(date, callback);
    }

    @Override
    public void getOrderById(String id, IOnLoadedCallback<Order> callback) {
        localDataSource.getOrderById(id, callback);
    }

    @Override
    public void addNewOrder(Order newOrder, IOnLoadedCallback<Boolean> callback) {
        localDataSource.addNewOrder(newOrder, callback);
    }
}

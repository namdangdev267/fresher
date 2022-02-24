package vn.com.misa.cukcukstarterclone.data.repository;

import java.util.List;

import vn.com.misa.cukcukstarterclone.data.IOnLoadedCallback;
import vn.com.misa.cukcukstarterclone.data.model.Order;

/**
 * - Mục đích Class: Interface cho Repository chứa các phương thức tương tác với {@link Order}
 * - Sử dụng khi: tạo Class để implement lại Interface này
 *
 * @created_by KhanhNQ on 28-Jan-2021.
 */
public interface IOrderRepository {
    void getAllOrders(IOnLoadedCallback<List<Order>> callback);

    void getOrdersByDate(String date, IOnLoadedCallback<List<Order>> callback);

    void getOrderById(String id, IOnLoadedCallback<Order> callback);

    void addNewOrder(Order newOrder, IOnLoadedCallback<Boolean> callback);
}

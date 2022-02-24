package vn.com.misa.cukcukstarterclone.data.source;

import java.util.List;

import vn.com.misa.cukcukstarterclone.data.IOnLoadedCallback;
import vn.com.misa.cukcukstarterclone.data.model.Order;

/**
 * - Mục đích Class: Interface chứa các phương thức xử lý data liên quan đến {@link Order}
 *
 * @created_by KhanhNQ on 09-Jan-21.
 */
public interface IOrderDataSource {
    interface Local {
        void getAllOrders(IOnLoadedCallback<List<Order>> callback);

        void getOrdersByDate(String date, IOnLoadedCallback<List<Order>> callback);

        void getOrderById(String id, IOnLoadedCallback<Order> callback);

        void addNewOrder(Order newOrder, IOnLoadedCallback<Boolean> callback);
    }

    interface Remote {

    }
}

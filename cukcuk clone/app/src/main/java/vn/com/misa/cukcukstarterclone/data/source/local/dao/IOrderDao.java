package vn.com.misa.cukcukstarterclone.data.source.local.dao;

import java.util.List;

import vn.com.misa.cukcukstarterclone.data.model.Order;

/**
 * @created_by KhanhNQ on 28-Jan-2021.
 */
public interface IOrderDao {

    List<Order> getAllOrders();

    List<Order> getOrdersByDate(String date);

    Order getOrderById(String id);

    boolean addNewOrder(Order newOrder);
}

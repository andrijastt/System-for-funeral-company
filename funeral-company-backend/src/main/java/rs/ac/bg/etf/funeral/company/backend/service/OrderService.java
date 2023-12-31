package rs.ac.bg.etf.funeral.company.backend.service;

import rs.ac.bg.etf.funeral.company.backend.entity.Item;
import rs.ac.bg.etf.funeral.company.backend.entity.Orders;

import java.util.List;

public interface OrderService {

    List<Orders> getAllOrders();
    Orders saveOrder(Orders orders);
    List<Orders> getAllContractOrders(Long contractID);
    Orders updateOrder(Orders orders);
    Orders updateItems(List<Item> itemList, Long orderID);
    Orders arrivedOrder(Long orderID);
    String removeOrder(Long orderID);
}

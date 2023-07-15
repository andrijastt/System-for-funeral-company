package rs.ac.bg.etf.funeral.company.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.bg.etf.funeral.company.backend.entity.Item;
import rs.ac.bg.etf.funeral.company.backend.entity.Orders;
import rs.ac.bg.etf.funeral.company.backend.entity.Product;
import rs.ac.bg.etf.funeral.company.backend.repository.ItemRepository;
import rs.ac.bg.etf.funeral.company.backend.repository.OrderRepository;
import rs.ac.bg.etf.funeral.company.backend.repository.ProductRepository;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImplementation implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Orders> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Orders saveOrder(Orders orders) {

        List<Item> itemList = orders.getItemList();

        boolean flag = true;
        for(Item item: itemList){
            Product product = productRepository.findById(item.getItemPK().getProductID()).get();
            if(product.getCount() < item.getAmount()){
                flag = false;
                break;
            }
        }

        if(!flag) return null;

        orders.setItemList(null);
        orders.setDateSend(new Date());
        orderRepository.saveAndFlush(orders);

        for(Item item: itemList){

            Product product = productRepository.findById(item.getItemPK().getProductID()).get();
            product.setCount(product.getCount() - item.getAmount());
            productRepository.save(product);

            item.getItemPK().setOrderID(orders.getOrderID());
            itemRepository.save(item);
        }

        orders.setItemList(itemList);
        return orderRepository.save(orders);
    }

    @Override
    public List<Orders> getAllContractOrders(Long contractID) {
        return orderRepository.findByContract_ContractID(contractID);
    }

    @Override
    public Orders updateOrder(Orders orders) {

        Orders ordersDB = orderRepository.findById(orders.getOrderID()).get();

        ordersDB.setContract(orders.getContract());
        ordersDB.setDateSend(orders.getDateSend());

        return orderRepository.save(ordersDB);
    }

    @Override
    public Orders updateItems(List<Item> itemList, Long orderID) {

        Orders orderDB = orderRepository.findById(orderID).get();

        for(Item item: orderDB.getItemList()){
            Product product = productRepository.findById(item.getItemPK().getProductID()).get();
            product.setCount(product.getCount() + item.getAmount());
            productRepository.save(product);
        }

        boolean flag = true;
        for(Item item: itemList){
            Product product = productRepository.findById(item.getItemPK().getProductID()).get();
            if(product.getCount() < item.getAmount()){
                flag = false;
                break;
            }
        }

        if(!flag){

            for(Item item: orderDB.getItemList()){
                Product product = productRepository.findById(item.getItemPK().getProductID()).get();
                product.setCount(product.getCount() - item.getAmount());
                productRepository.save(product);
            }
            return null;
        }

        orderDB.setItemList(null);
        orderRepository.saveAndFlush(orderDB);

        for(Item item: itemList){

            Product product = productRepository.findById(item.getItemPK().getProductID()).get();
            product.setCount(product.getCount() - item.getAmount());
            productRepository.save(product);

            item.getItemPK().setOrderID(orderDB.getOrderID());
            itemRepository.save(item);
        }

        orderDB.setItemList(itemList);
        return orderRepository.save(orderDB);
    }

    @Override
    public Orders arrivedOrder(Long orderID) {
        Orders orders = orderRepository.findById(orderID).get();
        orders.setDateArrived(new Date());
        return orderRepository.save(orders);
    }

    @Override
    public String removeOrder(Long orderID) {

        Orders order = orderRepository.findById(orderID).get();

        List<Item> itemsDB = order.getItemList();
        order.setItemList(null);

        for(Item item: itemsDB){
            Product product = productRepository.findById(item.getItemPK().getProductID()).get();
            product.setCount(product.getCount() + item.getAmount());
            productRepository.save(product);
            itemRepository.delete(item);
        }
        orderRepository.delete(order);

        return "Successfully deleted Order";
    }
}

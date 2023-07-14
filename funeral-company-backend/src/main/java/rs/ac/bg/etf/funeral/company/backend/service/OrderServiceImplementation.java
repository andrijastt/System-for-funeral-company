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
}

package rs.ac.bg.etf.funeral.company.backend.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rs.ac.bg.etf.funeral.company.backend.entity.Orders;
import rs.ac.bg.etf.funeral.company.backend.service.OrderService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping(path ="/orders")
    public List<Orders> getAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping(path ="/orders/{contractID}")
    public List<Orders> getAllContractOrders(@Valid @PathVariable("contractID") Long contractID){
        return orderService.getAllContractOrders(contractID);
    }

    @PostMapping(path = "/order")
    public Orders saveOrder(@Valid @RequestBody Orders order){
        return orderService.saveOrder(order);
    }

    @PostMapping(path = "/order/update")
    public Orders updateOrder(@Valid @RequestBody Orders order){
        return orderService.updateOrder(order);
    }

    @PostMapping(path = "/order/product/update")
    public Orders updateItems(@Valid @RequestBody Orders order){
        return orderService.updateItems(order.getItemList(), order.getOrderID());
    }

    @PostMapping(path ="/order/arrived/{contractID}")
    public Orders arrivedOrder(@Valid @PathVariable("contractID") Long contractID){
        return orderService.arrivedOrder(contractID);
    }

    @DeleteMapping(path ="/order/delete/{orderID}")
    public String removeOrder(@Valid @PathVariable("orderID") Long orderID){
        return orderService.removeOrder(orderID);
    }
}

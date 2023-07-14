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
    private List<Orders> getAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping(path ="/orders/{contractID}")
    private List<Orders> getAllContractOrders(@Valid @PathVariable("contractID") Long contractID){
        return orderService.getAllContractOrders(contractID);
    }

    @PostMapping(path = "/order")
    private Orders saveOrder(@Valid @RequestBody Orders order){
        return orderService.saveOrder(order);
    }

    @PostMapping(path = "/order/update")
    private Orders updateOrder(@Valid @RequestBody Orders order){
        return orderService.updateOrder(order);
    }
}

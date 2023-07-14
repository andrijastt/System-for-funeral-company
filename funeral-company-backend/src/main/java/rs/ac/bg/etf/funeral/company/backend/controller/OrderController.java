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

    @PostMapping(path = "/order")
    private Orders saveOrder(@Valid @RequestBody Orders order){
        return orderService.saveOrder(order);
    }
}

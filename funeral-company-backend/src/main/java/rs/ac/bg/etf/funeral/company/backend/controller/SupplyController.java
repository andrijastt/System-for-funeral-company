package rs.ac.bg.etf.funeral.company.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.bg.etf.funeral.company.backend.entity.Supply;
import rs.ac.bg.etf.funeral.company.backend.service.SupplyService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class SupplyController {

    @Autowired
    private SupplyService supplyService;

    @GetMapping(path = "/supplies")
    public List<Supply> getAllSupplies(){
        return supplyService.getAllServices();
    }
}

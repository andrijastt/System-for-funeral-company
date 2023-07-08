package rs.ac.bg.etf.funeral.company.backend.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
        return supplyService.getAllSupplies();
    }

    @PostMapping(path = "/supply")
    public Supply saveSupply(@Valid @RequestBody Supply supply){
        System.out.println("supply = " + supply);
        return supply;
    }
}

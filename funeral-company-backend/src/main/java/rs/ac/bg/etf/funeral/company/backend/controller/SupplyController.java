package rs.ac.bg.etf.funeral.company.backend.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rs.ac.bg.etf.funeral.company.backend.entity.MaterialSupply;
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

    @GetMapping(path = "/supplies/search/{name}")
    public List<Supply> searchSupply(@Valid @PathVariable("name") String name){
        return supplyService.searchSupply(name);
    }

    @PostMapping(path = "/supply")
    public Supply saveSupply(@Valid @RequestBody Supply supply){
        return supplyService.saveSupply(supply);
    }

    @PostMapping(path = "/supply/update")
    public Supply updateSupply(@Valid @RequestBody Supply supply){
        return supplyService.updateSupply(supply);
    }

    @PostMapping(path = "/supply/material/update")
    public Supply updateMaterialSupply(@Valid @RequestBody Supply materialSupply){
        return supplyService.updateMaterialSupply(materialSupply.getMaterialSupplyList(), materialSupply.getSupplyID());
    }

    @PostMapping(path = "/supply/arrived/{supplyID}")
    public Supply supplyArrived(@Valid @PathVariable("supplyID") Long supplyID){
        return supplyService.supplyArrived(supplyID);
    }

    @DeleteMapping(path = "/supply/delete/{supplyID}")
    public String removeSupply(@Valid @PathVariable("supplyID") Long supplyID){
        return supplyService.removeSupply(supplyID);
    }
}

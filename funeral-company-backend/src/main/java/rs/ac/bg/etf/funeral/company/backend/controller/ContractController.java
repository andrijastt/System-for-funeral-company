package rs.ac.bg.etf.funeral.company.backend.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rs.ac.bg.etf.funeral.company.backend.entity.Contract;
import rs.ac.bg.etf.funeral.company.backend.service.ContractService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class ContractController {

    @Autowired
    private ContractService contractService;

    @GetMapping(path = "/contracts")
    public List<Contract> getAllContracts(){
        return  contractService.getAllContracts();
    }

    @GetMapping(path = "/contracts/valid")
    public List<Contract> getAllValidContracts(){
        return contractService.getAllValidContracts();
    }

    @PostMapping(path = "/contract")
    public Contract saveContract(@Valid @RequestBody Contract contract){
        return contractService.saveContract(contract);
    }

    @PostMapping(path = "/contract/valid/{contractID}")
    public Contract updateValidContract(@Valid @PathVariable("contractID") Long contractID){
        return contractService.updateValidContract(contractID);
    }
}

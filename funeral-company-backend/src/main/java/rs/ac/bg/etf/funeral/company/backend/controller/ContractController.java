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

    @GetMapping(path = "/contracts/client/{clientID}")
    public List<Contract> getAllContractsByClient(@Valid @PathVariable("clientID") Long clientID){
        return contractService.getAllContractsByClient(clientID);
    }

    @GetMapping(path = "/contracts/client/sum/{clientID}")
    public List<Object[]> sumMoneyOfClient(@Valid @PathVariable("clientID") Long clientID){
        return contractService.sumMoneyOfClient(clientID);
    }

    @GetMapping(path = "/contract/client/newest/{clientID}")
    public List<Contract> findByClientNewest(@Valid @PathVariable("clientID") Long clientID){
        return contractService.findByClientNewest(clientID);
    }

    @PostMapping(path = "/contract")
    public Contract saveContract(@Valid @RequestBody Contract contract){
        return contractService.saveContract(contract);
    }

    @PostMapping(path = "/contract/valid/{contractID}")
    public Contract updateValidContract(@Valid @PathVariable("contractID") Long contractID){
        return contractService.updateValidContract(contractID);
    }

    @PostMapping(path = "/contract/update")
    public Contract updateValidContract(@Valid @RequestBody Contract contract){
        return contractService.updateContract(contract);
    }

    @DeleteMapping(path = "/contract/{contractID}")
    public String removeContract(@Valid @PathVariable("contractID") Long contractID){
        return contractService.removeContract(contractID);
    }
}

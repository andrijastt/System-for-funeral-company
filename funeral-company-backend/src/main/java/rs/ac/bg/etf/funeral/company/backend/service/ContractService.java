package rs.ac.bg.etf.funeral.company.backend.service;

import rs.ac.bg.etf.funeral.company.backend.entity.Client;
import rs.ac.bg.etf.funeral.company.backend.entity.Contract;

import java.util.List;

public interface ContractService {

    List<Contract> getAllContracts();
    List<Contract> getAllValidContracts();
    Contract saveContract(Contract contract);
    Contract updateValidContract(Long contractID);
    List<Contract> getAllContractsByClient(Long clientID);
    List<Object[]> sumMoneyOfClient(Long clientID);
}

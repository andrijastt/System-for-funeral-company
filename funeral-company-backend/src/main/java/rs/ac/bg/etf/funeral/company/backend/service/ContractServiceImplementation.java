package rs.ac.bg.etf.funeral.company.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.bg.etf.funeral.company.backend.entity.Client;
import rs.ac.bg.etf.funeral.company.backend.entity.Contract;
import rs.ac.bg.etf.funeral.company.backend.repository.ClientRepository;
import rs.ac.bg.etf.funeral.company.backend.repository.ContractRepository;

import java.util.List;

@Service
public class ContractServiceImplementation implements ContractService{

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public List<Contract> getAllContracts() {
        return contractRepository.findAll();
    }

    @Override
    public List<Contract> getAllValidContracts() {
        return contractRepository.findByValid(true);
    }

    @Override
    public Contract saveContract(Contract contract) {
        Client clientDB = clientRepository.findById(contract.getClient().getClientID()).get();
        clientDB.addContract(contract);
        contract.setClient(clientDB);
        clientRepository.save(clientDB);
        return contractRepository.save(contract);
    }


}

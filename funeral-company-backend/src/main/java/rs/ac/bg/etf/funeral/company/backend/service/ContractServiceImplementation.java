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

    @Override
    public Contract updateValidContract(Long contractID) {
        Contract contract = contractRepository.findById(contractID).get();
        contract.setValid(!contract.getValid());
        return contractRepository.save(contract);
    }

    @Override
    public List<Contract> getAllContractsByClient(Long clientID) {
        Client client = clientRepository.findById(clientID).get();
        return contractRepository.findByClient(client);
    }

    @Override
    public List<Object[]> sumMoneyOfClient(Long clientID) {
        Client client = clientRepository.findById(clientID).get();
        return contractRepository.sumMoneyOfClient(client);
    }

//    @Override
//    public List<String> currencyOfClient(Long clientID) {
//        Client client = clientRepository.findById(clientID).get();
//        return contractRepository.currencyOfClient(client);
//    }
}

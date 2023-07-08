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

    @Override
    public String removeContract(Long contractID) {
        Contract contract = contractRepository.findById(contractID).get();
        contract.getClient().removeContract(contract);
        contract.setClient(null);
        contractRepository.delete(contract);
        return "Successfully deleted contract!";
    }

    @Override
    public Contract updateContract(Contract contract) {
        Contract contractDB = contractRepository.findById(contract.getContractID()).get();

        if(!contract.getCurrency().equals(""))
            contractDB.setCurrency(contract.getCurrency());
        if(contract.getMoney() != null)
            contractDB.setMoney(contract.getMoney());
        contractDB.setDateSigned(contract.getDateSigned());
        contractDB.setPaymentDate(contract.getPaymentDate());
        contractDB.setValidUntil(contract.getValidUntil());
        Client client = clientRepository.findById(contractDB.getClient().getClientID()).get();
        contractDB.setClient(client);
        return contractRepository.save(contractDB);
    }

    @Override
    public List<Contract> findByClientNewest(Long clientID) {
        Client client = clientRepository.findById(clientID).get();
        return contractRepository.findByClientNewest(clientID);
    }
}

package rs.ac.bg.etf.funeral.company.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.bg.etf.funeral.company.backend.entity.Client;
import rs.ac.bg.etf.funeral.company.backend.repository.ClientRepository;

import java.util.List;

@Service
public class ClientServiceImplementation implements ClientService{

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client updateClient(Client client) {
        Client clientDB = clientRepository.findById(client.getClientID()).get();
        if(!client.getName().equals("")){
            clientDB.setName(client.getName());
        }
        if(!client.getCity().equals("")){
            clientDB.setCity(client.getCity());
        }
        return clientRepository.save(clientDB);
    }

    @Override
    public String deleteClientByID(Long clientID) {
        clientRepository.deleteById(clientID);
        return "Successfully deleted client!";
    }

    @Override
    public List<Client> getAllClientsByNameContainingAndCityContaining(String name, String city) {
        return clientRepository.findByNameContainingAndCityContaining(name, city);
    }
}

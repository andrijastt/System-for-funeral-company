package rs.ac.bg.etf.funeral.company.backend.service;

import rs.ac.bg.etf.funeral.company.backend.entity.Client;

import java.util.List;

public interface ClientService {

    List<Client> getAllClients();
    Client saveClient(Client client);
    Client updateClient(Client client);

    String deleteClientByID(Long clientID);

    List<Client> getAllClientsByNameContainingAndCityContaining(String name, String city);
}

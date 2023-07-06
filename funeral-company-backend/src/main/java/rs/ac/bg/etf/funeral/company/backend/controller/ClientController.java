package rs.ac.bg.etf.funeral.company.backend.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rs.ac.bg.etf.funeral.company.backend.entity.Client;
import rs.ac.bg.etf.funeral.company.backend.service.ClientService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping(path = "/clients")
    public List<Client> getAllModels(){
        return clientService.getAllClients();
    }

    @PostMapping(path = "/client")
    public Client saveModel(@Valid @RequestBody Client client){
        return clientService.saveClient(client);
    }

    @PostMapping(path = "/client/update")
    public Client updateModel(@Valid @RequestBody Client client){
        return clientService.updateClient(client);
    }

    @GetMapping(path = "/client/search")
    public List<Client> getAllModelsByNameContaining(@Valid @RequestParam("name") String name, @Valid @RequestParam("city") String city){
        return clientService.getAllClientsByNameContainingAndCityContaining(name, city);
    }

    @DeleteMapping(path = "/client/delete/{clientID}")
    public String deleteModel(@Valid @PathVariable("clientID") Long clientID){
        return clientService.deleteClientByID(clientID);
    }
}

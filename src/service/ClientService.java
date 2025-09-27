package service;

import model.Client;
import repository.ClientRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class ClientService {
    private final ClientRepository clientRepo;

    public ClientService(ClientRepository clientRepo) {
        this.clientRepo = clientRepo;
    }

    public Client createClient(String nom, String prenom, String email, String motDePasse, String id) {
        Client c = new Client(id, nom, prenom, email, motDePasse);
        clientRepo.save(c);
        return c;
    }

    public Client updateClient(Client client) {
        clientRepo.save(client);
        return client;
    }

    public void deleteClient(String id) {
        clientRepo.deleteById(id);
    }

    public Client findByIdOrThrow(String id) {
        return clientRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Client introuvable: " + id));
    }

    public Optional<Client> findById(String id) {
        return clientRepo.findById(id);
    }

    public List<Client> findAll() {
        return clientRepo.findAll();
    }
}

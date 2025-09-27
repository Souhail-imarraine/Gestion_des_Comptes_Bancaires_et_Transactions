package service;

import model.Client;
import model.Compte;
import model.enums.TypeCompte;
import repository.CompteRepository;
import repository.ClientRepository;
import util.IdGenerator;

import java.util.List;
import java.util.NoSuchElementException;

public class CompteService {
    private final CompteRepository compteRepo;
    private final ClientRepository clientRepo;

    public CompteService(CompteRepository compteRepo, ClientRepository clientRepo) {
        this.compteRepo = compteRepo;
        this.clientRepo = clientRepo;
    }

    public Compte createCompte(String clientId, TypeCompte type) {
        Client client = clientRepo.findById(clientId).orElseThrow(() -> new NoSuchElementException("Client introuvable"));
        String id = IdGenerator.nextId("CP");
        Compte c = new Compte(id, type, client);
        client.addCompte(c);
        compteRepo.save(c);
        clientRepo.save(client);
        return c;
    }

    public Compte findByIdOrThrow(String id) {
        return compteRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Compte introuvable: " + id));
    }

    public List<Compte> findByClientId(String clientId) {
        return compteRepo.findByClientId(clientId);
    }

    public void deleteCompte(String id) {
        // remove association with client if exists
        compteRepo.findById(id).ifPresent(c -> {
            Client client = c.getClient();
            if (client != null) client.removeCompte(c);
            compteRepo.deleteById(id);
        });
    }
}

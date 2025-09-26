package repository;
import repository.implementation.ClientRepositoryInterface;
import model.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class ClientRepository implements ClientRepositoryInterface {
    private final List<Client> clients = new ArrayList<>();

    public void save(Client client) {
        // overwrite if exist
        findById(client.getIdClient()).ifPresent(c -> clients.remove(c));
        clients.add(client);
    }

    public Optional<Client> findById(String id) {
        return clients.stream().filter(c -> c.getIdClient().equals(id)).findFirst();
    }

    public List<Client> findAll() {
        return new ArrayList<>(clients);
    }

    public void deleteById(String id) {
        findById(id).ifPresent(clients::remove);
    }
}

package repository.implementation;
import model.Client;

import java.util.List;
import java.util.Optional;

public interface ClientRepositoryInterface {
    public void save(Client client);
    public Optional<Client> findById(String id);
    public List<Client> findAll();
    public void deleteById(String id);
}

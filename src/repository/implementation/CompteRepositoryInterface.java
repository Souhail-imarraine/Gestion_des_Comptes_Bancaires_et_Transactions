package repository.implementation;

import model.Compte;

import java.util.List;
import java.util.Optional;

public interface CompteRepositoryInterface {
    public void save(Compte compte);
    public Optional<Compte> findById(String id);
    public List<Compte> findAll();
    public List<Compte> findByClientId(String clientId);
    public void deleteById(String id);
}

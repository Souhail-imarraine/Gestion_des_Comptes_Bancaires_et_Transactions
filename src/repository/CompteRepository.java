package repository;
import repository.implementation.CompteRepositoryInterface;
import model.Compte;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CompteRepository implements CompteRepositoryInterface {
    private final List<Compte> comptes = new ArrayList<>();
    @Override
    public void save(Compte compte) {
        findById(compte.getIdCompte()).ifPresent(c -> comptes.remove(c));
        comptes.add(compte);
    }
    @Override
    public Optional<Compte> findById(String id) {
        return comptes.stream().filter(c -> c.getIdCompte().equals(id)).findFirst();
    }
    @Override
    public List<Compte> findAll() {
        return new ArrayList<>(comptes);
    }
    @Override
    public List<Compte> findByClientId(String clientId) {
        return comptes.stream()
                .filter(c -> c.getClient() != null && clientId.equals(c.getClient().getIdClient()))
                .collect(Collectors.toList());
    }
    @Override
    public void deleteById(String id) {
        findById(id).ifPresent(comptes::remove);
    }
}

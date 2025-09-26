package model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Client extends Personne {
    private final String idClient;
    private final List<Compte> comptes = new ArrayList<>();

    public Client(String nom, String prenom, String email, String motDePasse) {
        super(nom, prenom, email, motDePasse);
        this.idClient = UUID.randomUUID().toString();
    }

    public String getIdClient() {
        return idClient;
    }

    public List<Compte> getComptes() {
        return comptes;
    }

    public void addCompte(Compte compte) {
        if (compte != null) {
            comptes.add(compte);
        }
    }

    public void removeCompte(Compte compte) {
        comptes.remove(compte);
    }

    @Override
    public String toString() {
        return "Client{" + "id='" + idClient + '\'' + ", name=" + prenom + " " + nom + '}';
    }
}

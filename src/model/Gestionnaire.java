package model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Gestionnaire extends Personne {
    private final String idGestionnaire;
    private final String departement;
    private final List<Client> listeClients = new ArrayList<>();

    public Gestionnaire(String idGestionnaire, String nom, String prenom, String email, String motDePasse, String departement) {
        super(nom, prenom, email, motDePasse);
        this.idGestionnaire = idGestionnaire;
        this.departement = departement;
    }

    public String getIdGestionnaire() {
        return idGestionnaire;
    }

    public String getDepartement() {
        return departement;
    }

    public List<Client> getListeClients() {
        return listeClients;
    }

    public void addClient(Client client) {
        if (client != null) listeClients.add(client);
    }

    public void removeClient(Client client) {
        listeClients.remove(client);
    }

    @Override
    public String toString() {
        return "Gestionnaire{" + idGestionnaire + ", dept=" + departement + "}";
    }
}

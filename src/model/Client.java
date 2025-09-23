package model;

import java.util.UUID;
import java.util.ArrayList;
import model.Compte;

public class Client extends Personne {
    private UUID idClients;
    private ArrayList<Compte> comptes;

    public Client(String nom, String prenom, String email, String motDePasse, UUID idClients){
        super(nom, prenom, email, motDePasse);
        this.idClients = idClients;
    }

    public UUID getIdClients() {
        return idClients;
    }

    public void setIdClients(UUID idClients) {
        this.idClients = idClients;
    }

    public ArrayList<Compte> getComptes() {
        return comptes;
    }

    public void setComptes(ArrayList<Compte> comptes) {
        this.comptes = comptes;
    }


}

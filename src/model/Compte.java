package model;

import model.enums.TypeCompte;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Compte {
    private final String idCompte;
    private final TypeCompte typeCompte;
    private BigDecimal solde = BigDecimal.ZERO;
    private final List<Transaction> transactions = new ArrayList<>();
    private Client client; // association

    public Compte(TypeCompte typeCompte, Client client) {
        this.idCompte = UUID.randomUUID().toString();
        this.typeCompte = typeCompte;
        this.client = client;
    }

    public String getIdCompte() {
        return idCompte;
    }

    public TypeCompte getTypeCompte() {
        return typeCompte;
    }

    public BigDecimal getSolde() {
        return solde;
    }

    public void setSolde(BigDecimal solde) {
        this.solde = solde;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client c) {

        this.client = c;
    }

    public void credit(BigDecimal montant) {

        solde = solde.add(montant);
    }

    public void debit(BigDecimal montant) {

        solde = solde.subtract(montant);
    }

    @Override
    public String toString() {

        return "Compte{" + idCompte + ", type=" + typeCompte + ", solde=" + solde + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Compte)) return false;
        Compte compte = (Compte) o;
        return Objects.equals(idCompte, compte.idCompte);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idCompte);
    }
}

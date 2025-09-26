package model;

import model.enums.TypeTransaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Transaction {
    private final String idTransaction;
    private final TypeTransaction type;
    private final BigDecimal montant;
    private final LocalDateTime date;
    private final String motif;
    private final Compte compteSource;
    private final Compte compteDestination; // nullable for DEPOT/RETRAIT

    public Transaction(String idTransaction, TypeTransaction type, BigDecimal montant,
                       LocalDateTime date, String motif, Compte compteSource, Compte compteDestination) {
        this.idTransaction = UUID.randomUUID().toString();
        this.type = type;
        this.montant = montant;
        this.date = date;
        this.motif = motif;
        this.compteSource = compteSource;
        this.compteDestination = compteDestination;
    }

    public String getIdTransaction() {
        return idTransaction;
    }

    public TypeTransaction getType() {
        return type;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getMotif() {
        return motif;
    }

    public Compte getCompteSource() {
        return compteSource;
    }

    public Compte getCompteDestination() {
        return compteDestination;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "idTransaction='" + idTransaction + '\'' +
                ", type=" + type +
                ", montant=" + montant +
                ", date=" + date +
                ", motif='" + motif + '\'' +
                ", compteSource=" + compteSource +
                ", compteDestination=" + compteDestination +
                '}';
    }
}

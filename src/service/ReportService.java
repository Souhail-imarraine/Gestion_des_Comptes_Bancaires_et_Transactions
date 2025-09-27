package service;

import model.Compte;
import model.Transaction;
import model.enums.TypeTransaction;
import repository.CompteRepository;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class ReportService {
    private final CompteRepository compteRepo;

    public ReportService(CompteRepository compteRepo) {
        this.compteRepo = compteRepo;
    }

    public BigDecimal totalSoldeParClient(String clientId) {
        List<Compte> comptes = compteRepo.findByClientId(clientId);
        return comptes.stream().map(Compte::getSolde).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Map<TypeTransaction, BigDecimal> totalParTypePourCompte(String compteId) {
        Compte c = compteRepo.findById(compteId).orElseThrow(() -> new NoSuchElementException("Compte introuvable: " + compteId));
        return c.getTransactions().stream()
                .collect(Collectors.groupingBy(Transaction::getType,
                        Collectors.mapping(Transaction::getMontant,
                                Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))));
    }

    public List<Transaction> transactionsLesPlusGrandes(int topN) {
        return compteRepo.findAll().stream()
                .flatMap(cp -> cp.getTransactions().stream())
                .sorted(Comparator.comparing(Transaction::getMontant).reversed())
                .limit(topN)
                .collect(Collectors.toList());
    }

    // detection simple: transactions au dessus d'un seuil
    public List<Transaction> detecterTransactionsSuspectes(BigDecimal seuil) {
        return compteRepo.findAll().stream()
                .flatMap(cp -> cp.getTransactions().stream())
                .filter(tx -> tx.getMontant().compareTo(seuil) > 0)
                .collect(Collectors.toList());
    }
}

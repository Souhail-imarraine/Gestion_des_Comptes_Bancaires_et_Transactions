package service;

import model.Compte;
import model.Transaction;
import model.enums.TypeTransaction;
import repository.CompteRepository;
import repository.TransactionRepository;
import util.IdGenerator;
import util.Validators;
import util.exceptions.InsufficientFundsException;
import util.exceptions.InvalidTransactionException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TransactionService {
    private final CompteRepository compteRepo;
    private final TransactionRepository transactionRepo;

    public TransactionService(CompteRepository compteRepo, TransactionRepository transactionRepo) {
        this.compteRepo = compteRepo;
        this.transactionRepo = transactionRepo;
    }

    public Transaction effectuerDepot(String compteId, BigDecimal montant, String motif) {
        Validators.requirePositiveAmount(montant);
        Compte c = compteRepo.findById(compteId).orElseThrow(() -> new NoSuchElementException("Compte introuvable"));
        Transaction t = new Transaction(IdGenerator.nextId("TX"), TypeTransaction.DEPOT, montant, LocalDateTime.now(), motif, c, null);
        c.credit(montant);
        c.getTransactions().add(t);
        transactionRepo.save(t);
        compteRepo.save(c);
        return t;
    }

    public Transaction effectuerRetrait(String compteId, BigDecimal montant, String motif) {
        Validators.requirePositiveAmount(montant);
        Compte c = compteRepo.findById(compteId).orElseThrow(() -> new NoSuchElementException("Compte introuvable"));
        if (c.getSolde().compareTo(montant) < 0) {
            throw new InsufficientFundsException("Solde insuffisant pour retrait");
        }
        Transaction t = new Transaction(IdGenerator.nextId("TX"), TypeTransaction.RETRAIT, montant, LocalDateTime.now(), motif, c, null);
        c.debit(montant);
        c.getTransactions().add(t);
        transactionRepo.save(t);
        compteRepo.save(c);
        return t;
    }

    public Transaction effectuerVirement(String srcId, String destId, BigDecimal montant, String motif) {
        Validators.requirePositiveAmount(montant);
        if (srcId.equals(destId)) throw new InvalidTransactionException("Source et destination identiques");
        Compte src = compteRepo.findById(srcId).orElseThrow(() -> new NoSuchElementException("Compte source introuvable"));
        Compte dest = compteRepo.findById(destId).orElseThrow(() -> new NoSuchElementException("Compte destination introuvable"));
        if (src.getSolde().compareTo(montant) < 0)
            throw new InsufficientFundsException("Solde insuffisant pour virement");
        // create transaction and update balances
        Transaction t = new Transaction(IdGenerator.nextId("TX"), TypeTransaction.VIREMENT, montant, LocalDateTime.now(), motif, src, dest);
        src.debit(montant);
        dest.credit(montant);
        src.getTransactions().add(t);
        dest.getTransactions().add(t);
        transactionRepo.save(t);
        compteRepo.save(src);
        compteRepo.save(dest);
        return t;
    }

    public List<Transaction> findTransactionsForCompte(String compteId, Predicate<Transaction> filter) {
        Compte c = compteRepo.findById(compteId).orElseThrow(() -> new NoSuchElementException("Compte introuvable"));
        return c.getTransactions().stream().filter(filter).collect(Collectors.toList());
    }
}

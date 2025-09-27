package repository;
import repository.implementation.TransactionRepositoryInterface;
import model.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TransactionRepository implements TransactionRepositoryInterface {
    private final List<Transaction> transactions = new ArrayList<>();
    @Override
    public void save(Transaction t) {
        transactions.add(t);
    }
    @Override
    public Optional<Transaction> findById(String id) {
        return transactions.stream().filter(tx -> tx.getIdTransaction().equals(id)).findFirst();
    }
    @Override
    public List<Transaction> findAll() {
        return new ArrayList<>(transactions);
    }
    @Override
    public void deleteById(String id) {
        findById(id).ifPresent(transactions::remove);
    }
}

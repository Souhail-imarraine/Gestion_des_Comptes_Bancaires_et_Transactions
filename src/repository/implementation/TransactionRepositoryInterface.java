package repository.implementation;

import model.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionRepositoryInterface {
    public void save(Transaction t);
    public Optional<Transaction> findById(String id);
    public List<Transaction> findAll();
    public void deleteById(String id);
}

package ma.skypay.repository;

import java.util.Collection;
import ma.skypay.entities.Transaction;

public interface TransactionRepository {

    public void addTransaction(Transaction transaction);

    public Collection<Transaction> findAllTransactions();
}

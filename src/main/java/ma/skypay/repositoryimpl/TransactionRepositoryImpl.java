package ma.skypay.repositoryimpl;

import java.util.ArrayList;
import java.util.Collection;
import ma.skypay.entities.Transaction;
import ma.skypay.repository.TransactionRepository;

public class TransactionRepositoryImpl implements TransactionRepository {

    private Collection<Transaction> transactions = new ArrayList<>();

    @Override
    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
    }

    @Override
    public Collection<Transaction> findAllTransactions() {
        return this.transactions;
    }
    
}

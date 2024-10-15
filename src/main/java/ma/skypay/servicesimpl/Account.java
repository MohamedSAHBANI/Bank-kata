package ma.skypay.servicesimpl;

import ma.skypay.DateProvider;
import ma.skypay.entities.Transaction;
import ma.skypay.repository.TransactionRepository;
import ma.skypay.services.AccountService;

public class Account implements AccountService {

    private TransactionRepository transactionRepository ;
    private OutputBuilder outputBuilder ;
    private DateProvider dateProvider ;

    public Account(TransactionRepository transactionRepository, OutputBuilder outputBuilder, DateProvider dateProvider) {
        this.transactionRepository = transactionRepository;
        this.outputBuilder = outputBuilder;
        this.dateProvider = dateProvider;
    }

    @Override
    public void deposit(int amount) {
        Transaction transaction = new Transaction(amount, dateProvider.getDate()) ;
        transactionRepository.addTransaction(transaction);
    }

    @Override
    public void withdraw(int amount) {
        Transaction transaction = new Transaction(amount*-1, dateProvider.getDate()) ;
        transactionRepository.addTransaction(transaction);
    }

    @Override
    public void printStatement() {
        outputBuilder.buildStatement( transactionRepository.findAllTransactions() );
    }
}

package ma.skypay;

import ma.skypay.repository.TransactionRepository;
import ma.skypay.repositoryimpl.TransactionRepositoryImpl;
import ma.skypay.servicesimpl.Account;
import ma.skypay.servicesimpl.OutputBuilder;
import ma.skypay.servicesimpl.OutputPrinter;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        TransactionRepository transactionRepository = new TransactionRepositoryImpl();
        OutputBuilder outputBuilder = new OutputBuilder( new OutputPrinter() {
            @Override
            public void print(String message) {
                System.out.print( message );
            }
        } );
        DateProvider dateProvider = new DateProvider();
        
        Account account = new Account( transactionRepository, outputBuilder, dateProvider );

        account.deposit(1000);
        account.deposit(1000);
        account.withdraw(500);
        account.printStatement();
    }
}

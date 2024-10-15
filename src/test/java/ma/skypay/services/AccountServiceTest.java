package ma.skypay.services;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ma.skypay.DateProvider;
import ma.skypay.repository.TransactionRepository;
import ma.skypay.repositoryimpl.TransactionRepositoryImpl;
import ma.skypay.servicesimpl.Account;
import ma.skypay.servicesimpl.OutputBuilder;
import ma.skypay.servicesimpl.OutputPrinter;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {
    AccountService accountService ;
    
    @Mock
    OutputPrinter outputPrinter;
    @Mock
    DateProvider dateProvider ;
        
    @Test
    public void printStatement() {
        TransactionRepository transactionRepository = new TransactionRepositoryImpl();
        OutputBuilder outputBuilder = new OutputBuilder( outputPrinter );
        this.accountService = new Account(transactionRepository, outputBuilder, dateProvider);


        when(dateProvider.getDate()).thenReturn(LocalDate.of(2012, 1, 10))
                                    .thenReturn(LocalDate.of(2012, 1, 13))
                                    .thenReturn(LocalDate.of(2012, 1, 14));


        accountService.deposit( 1000 );
        accountService.deposit( 2000 );
        accountService.withdraw( 500 );

        accountService.printStatement();

        String header = "Date       || Amount || Balance\n" ;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(header).append( "14/01/2012 || -500   || 2500\n" )
                                    .append( "13/01/2012 || 2000   || 3000\n" )
                                    .append( "10/01/2012 || 1000   || 1000" ) ;
                                    
        verify(outputPrinter).print( stringBuilder.toString() );
    }
}

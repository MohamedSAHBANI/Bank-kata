package ma.skypay.servicesimpl;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import ma.skypay.entities.Transaction;

public class OutputBuilder {
    String HEADER = "Date       || Amount || Balance";

    OutputPrinter printer ;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public OutputBuilder(OutputPrinter printer) {
        this.printer = printer;
    }

    public void buildStatement(Collection<Transaction> transactions){

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(HEADER);
        int balance = 0; 
        ArrayList<String> statementLines = new ArrayList<>();
        
        for (Transaction transaction : transactions) {
            String date = transaction.getDate().format(formatter);
            int amount = transaction.getAmount();
            balance += amount;
    
            String line = String.format("%s || %d   || %d", date, amount, balance ); 
            statementLines.add(line);
        }

        statementLines.sort( Comparator.reverseOrder() );
        
        for (String line : statementLines) {
            stringBuilder.append("\n");
            stringBuilder.append(line);
        }

        printer.print( stringBuilder.toString() ) ;
    }
}

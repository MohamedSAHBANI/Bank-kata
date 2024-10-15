package ma.skypay;

import java.time.LocalDate;

public class DateProvider {
    LocalDate date = LocalDate.now();
    public LocalDate getDate() {
        return this.date;   
    }
    public void setDate( LocalDate date ) {
        this.date = date;
    }
}

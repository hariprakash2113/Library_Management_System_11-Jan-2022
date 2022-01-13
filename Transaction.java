import java.time.LocalDate;
public class Transaction {
    Book book;
    String transaction_type;
    LocalDate date;

    public Transaction(Book book, String transaction_type, LocalDate date) {
        this.book = book;
        this.transaction_type = transaction_type;
        this.date = date;
    }
}

public class Book {
    String bookName;
    String authorName;
    Integer ISBNno;
    Integer quantity;
    Integer availableQuantity;
    Integer borrowCount;
    String addedBy;

    public Book(String bookName, String authorName, Integer iSBNno, Integer quantity, Integer availableQuantity,
            Integer borrowCount, String addedBy) {
        this.bookName = bookName;
        this.authorName = authorName;
        ISBNno = iSBNno;
        this.quantity = quantity;
        this.availableQuantity = availableQuantity;
        this.borrowCount = borrowCount;
        this.addedBy = addedBy;
    }

}

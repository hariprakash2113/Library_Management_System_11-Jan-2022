public class Book {
    String bookName;
    String authorName;
    Integer ISBNno;
    Integer quantity;
    Integer availableQuantity;
    Integer borrowCount;
    String addedBy;

    public Book(String bookName, String authorName, Integer iSBNno, Integer quantity, Integer availableQuantity,
            String addedBy) {
        this.bookName = bookName;
        this.authorName = authorName;
        ISBNno = iSBNno;
        this.quantity = quantity;
        this.availableQuantity = availableQuantity;
        this.addedBy = addedBy;
    }

    static void addBook(int ind) {
        System.out.print("Enter ISB number : ");
        Integer iSBNno = Integer.parseInt(Main.sc.nextLine());
        System.out.print("Enter Book Name : ");
        String bookName = Main.sc.nextLine();
        System.out.print("Enter Author Number : ");
        String authorName = Main.sc.nextLine();
        System.out.print("Enter Quantity : ");
        Integer quantity = Integer.parseInt(Main.sc.nextLine());
        Main.books.add(new Book(bookName, authorName, iSBNno, quantity, quantity, Main.admins.get(ind).name));
        System.out.printf("Book %s has been Successfully Added\n", bookName);
        System.out.println("Enter 1 to add one another book else any other key for returing to to Admin page");
        int n = Integer.parseInt(Main.sc.nextLine());
        if (n == 1) {
            addBook(ind);
        } else {
            Admin.adminPage(ind);
        }
    }
}

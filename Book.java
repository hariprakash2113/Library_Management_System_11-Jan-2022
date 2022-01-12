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

    public static void modify(int ind) {
        System.out.print("Enter ISB number of the Book to Modify or 0 to exit :");
        Integer isbn = Integer.parseInt(Main.sc.nextLine());
        if (isbn==0)
            Admin.adminPage(ind);
        int pos = -1;
        for (int i = 0; i < Main.books.size(); i++) {
            if (Main.books.get(i).ISBNno==isbn) {
                pos = i;break;
            }
        }
        if (pos == -1) {
            System.out.println("Book not found\nEnter correct number");
            modify(ind);
        }
        else{
            System.out.print("Enter Number of books to be added : ");
            int num = Integer.parseInt(Main.sc.nextLine());
            Main.books.get(pos).quantity+=num;
            Main.books.get(pos).availableQuantity+=num;
            System.out.printf("%d number of %s book has been added to Library\n",num,Main.books.get(pos).bookName);
            System.out.println("Press any key to return to Admin page");
            Main.sc.nextLine();
            Admin.adminPage(ind);
        }
    }

    public static void deleteBook(int ind) {
        System.out.print("Enter ISB number of the Book to Delete or 0 to exit :");
        Integer isbn = Integer.parseInt(Main.sc.nextLine());
        if (isbn==0)
            Admin.adminPage(ind);
        int pos = -1;
        for (int i = 0; i < Main.books.size(); i++) {
            if (Main.books.get(i).ISBNno==isbn) {
                pos = i;break;
            }
        }
        if (pos == -1) {
            System.out.println("Book not found\nEnter correct number");
            deleteBook(ind);
        }
        else{
            System.out.printf("Are you Sure ? You Want to delete \"%s\" book from Library ?\n",Main.books.get(pos).bookName);
            System.out.println("Press \"y\" to confirm else any other key to abort ");
            char opt = Main.sc.nextLine().charAt(0);
            if(opt=='y'){
                Main.books.remove(pos);
                System.out.printf("Book \"%s\" has been removed from library\n",Main.books.get(pos).bookName);
                System.out.println("Press any key to redirect to admin page");
                Main.sc.nextLine();
                Admin.adminPage(ind);
            }
            else{
                System.out.printf("Book \"%s\" not deleted\n",Main.books.get(pos).bookName);
                System.out.println("Press any key to redirect to admin page");
                Main.sc.nextLine();
                Admin.adminPage(ind);  
            }

        }
    }
}

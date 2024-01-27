import java.util.ArrayList;
import java.util.Scanner;

class Book {
    int bookId;
    String title;
    String author;
    boolean isAvailable;

    public Book(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }

    public void displayBookInfo() {
        System.out.println("Book ID: " + bookId);
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Availability: " + (isAvailable ? "Available" : "Not Available"));
    }
}

class user {
    String username;
    ArrayList<Book> borrowedBooks;

    public user(String username) {
        this.username = username;
        this.borrowedBooks = new ArrayList<>();
    }

    public void displayUserInfo() {
        System.out.println("user: " + username);
        System.out.println("Borrowed Books:");
        for (Book book : borrowedBooks) {
            book.displayBookInfo();
            System.out.println("------------------------------");
        }
    }
}

class Library {
    ArrayList<Book> books;
    ArrayList<user> users;

    public Library() {
        this.books = new ArrayList<>();
        this.users = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void addUser(user user) {
        users.add(user);
    }

    public void displayAllBooks() {
        System.out.println("Library Catalog:");
        for (Book book : books) {
            book.displayBookInfo();
            System.out.println("------------------------------");
        }
    }

    public Book findBookById(int bookId) {
        for (Book book : books) {
            if (book.bookId == bookId) {
                return book;
            }
        }
        return null;
    }

    public void borrowBook(user user, int bookId) {
        Book book = findBookById(bookId);
        if (book != null && book.isAvailable) {
            book.isAvailable = false;
            user.borrowedBooks.add(book);
            System.out.println("Book successfully borrowed.");
        } else {
            System.out.println("Book not available for borrowing.");
        }
    }

    public void returnBook(user user, int bookId) {
        Book book = findBookById(bookId);
        if (book != null && !book.isAvailable && user.borrowedBooks.contains(book)) {
            book.isAvailable = true;
            user.borrowedBooks.remove(book);
            System.out.println("Book successfully returned.");
        } else {
            System.out.println("Invalid book or book not borrowed by the user.");
        }
    }

    public ArrayList<Book> searchBooks(String keyword) {
        ArrayList<Book> searchResults = new ArrayList<>();
        for (Book book : books) {
            if (book.title.toLowerCase().contains(keyword.toLowerCase()) || book.author.toLowerCase().contains(keyword.toLowerCase())) {
                searchResults.add(book);
            }
        }
        return searchResults;
    }
}

public class OnlineLibrary {
    public static void main(String[] args) {
        Library library = new Library();

        // Adding sample books
        library.addBook(new Book(1, "Java Programming", "John Doe"));
        library.addBook(new Book(2, "Data Structures and Algorithms", "Jane Smith"));
        library.addBook(new Book(3, "Machine Learning Basics", "Alice Johnson"));

        // Adding sample users
        library.addUser(new user("user1"));
        library.addUser(new user("user2"));

        // Display available books
        library.displayAllBooks();

        // Login
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        String enteredUsername = scanner.nextLine();

        user loggedInUser = null;
        for (user user : library.users) {
            if (user.username.equals(enteredUsername)) {
                loggedInUser = user;
                break;
            }
        }

        if (loggedInUser != null) {
            System.out.println("Login successful.");

            // Borrow a book
            System.out.print("Enter the book ID to borrow: ");
            int bookToBorrowId = scanner.nextInt();
            library.borrowBook(loggedInUser, bookToBorrowId);
            loggedInUser.displayUserInfo();

            // Return a book
            System.out.print("Enter the book ID to return: ");
            int bookToReturnId = scanner.nextInt();
            library.returnBook(loggedInUser, bookToReturnId);
            loggedInUser.displayUserInfo();

            // Search for books
            System.out.print("Enter a keyword to search for books: ");
            scanner.nextLine(); // Consume the newline character
            String keyword = scanner.nextLine();
            ArrayList<Book> searchResults = library.searchBooks(keyword);

            if (!searchResults.isEmpty()) {
                System.out.println("Search Results:");
                for (Book result : searchResults) {
                    result.displayBookInfo();
                    System.out.println("------------------------------");
                }
            } else {
                System.out.println("No books found matching the keyword.");
            }
        } else {
            System.out.println("user not found. Exiting the program.");
        }
    }
}


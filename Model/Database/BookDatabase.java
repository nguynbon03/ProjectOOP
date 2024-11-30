package ProjectOOP.Model.Database;

import ProjectOOP.Model.Book;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BookDatabase {

    private final String FILE_PATH = "books.txt";

    public BookDatabase() {
        File file = new File(FILE_PATH);
        try {
            if (!file.exists()) {
                file.createNewFile(); // Tạo file nếu chưa tồn tại
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Lấy tất cả các sách từ file
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 4) { // Chỉnh sửa để xử lý 4 thuộc tính
                    String name = parts[0]; // Thêm thuộc tính name
                    String title = parts[1];
                    String author = parts[2];
                    String content = parts[3];
                    books.add(new Book(name, title, author, content)); // Cập nhật khi tạo Book mới
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return books;
    }

    // Thêm sách vào file
    public boolean addBook(Book book) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(book.getName() + ";" + book.getTitle() + ";" + book.getAuthor() + ";" + book.getContent());
            writer.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Xóa sách khỏi file
    public boolean deleteBook(Book book) {
        List<Book> books = getAllBooks();
        boolean isRemoved = books.removeIf(b -> b.getName().equals(book.getName()) && b.getTitle().equals(book.getTitle()) && b.getAuthor().equals(book.getAuthor()));

        if (isRemoved) {
            // Ghi lại toàn bộ danh sách sách sau khi xóa
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
                for (Book b : books) {
                    writer.write(b.getName() + ";" + b.getTitle() + ";" + b.getAuthor() + ";" + b.getContent());
                    writer.newLine();
                }
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    // Cập nhật thông tin sách trong file
    public boolean updateBook(Book oldBook, Book newBook) {
        List<Book> books = getAllBooks();
        boolean isUpdated = false;

        // Cập nhật thông tin sách nếu tìm thấy
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            if (book.getName().equals(oldBook.getName()) && book.getTitle().equals(oldBook.getTitle())) {
                books.set(i, newBook);
                isUpdated = true;
                break;
            }
        }

        if (isUpdated) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
                for (Book book : books) {
                    writer.write(book.getName() + ";" + book.getTitle() + ";" + book.getAuthor() + ";" + book.getContent());
                    writer.newLine();
                }
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}

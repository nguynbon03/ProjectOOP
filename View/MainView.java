package ProjectOOP.View;

import ProjectOOP.BookListCellRenderer;
import ProjectOOP.Model.Book;
import ProjectOOP.Model.Database.BookDatabase;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainView {
    private JFrame frame;
    private JList<Book> bookList;
    private JTextPane contentArea;
    private JButton addBookButton;
    private JButton deleteBookButton;
    private JButton readBookButton;
    private JButton editBookButton; // Nút Edit Book mới
    private BookDatabase bookDatabase;

    public MainView() {
        // Khởi tạo bookDatabase
        bookDatabase = new BookDatabase();
        frame = new JFrame("Book App");
        frame.setSize(1000, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Danh sách sách (Sidebar)
        bookList = new JList<>();
        bookList.setCellRenderer(new BookListCellRenderer()); // Hiển thị chỉ tên truyện
        updateBookList();
        bookList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Nội dung sách (Main Content)
        contentArea = new JTextPane();
        contentArea.setEditable(false); // Không cho phép chỉnh sửa
        JScrollPane contentScrollPane = new JScrollPane(contentArea);

        // Nút thêm, xóa, đọc sách và chỉnh sửa sách
        addBookButton = new JButton("Add Book");
        deleteBookButton = new JButton("Delete Book");
        readBookButton = new JButton("Read Book");
        editBookButton = new JButton("Edit Book"); // Nút "Edit Book"

        addBookButton.addActionListener(e -> openAddBookDialog());
        deleteBookButton.addActionListener(e -> deleteSelectedBook());
        readBookButton.addActionListener(e -> readSelectedBook());
        editBookButton.addActionListener(e -> openEditBookDialog()); // Xử lý khi bấm nút "Edit Book"

        // Sidebar panel
        JPanel sidebarPanel = new JPanel(new BorderLayout());
        sidebarPanel.setPreferredSize(new Dimension(300, 0));
        sidebarPanel.setBorder(BorderFactory.createTitledBorder("Book List"));
        sidebarPanel.add(new JScrollPane(bookList), BorderLayout.CENTER);

        // Button Panel
        JPanel buttonPanel = new JPanel(new GridLayout(1, 4)); // Cập nhật GridLayout để thêm nút Edit Book
        buttonPanel.add(addBookButton);
        buttonPanel.add(deleteBookButton);
        buttonPanel.add(readBookButton);
        buttonPanel.add(editBookButton); // Thêm nút "Edit Book" vào giao diện

        // Main Layout
        frame.setLayout(new BorderLayout());
        frame.add(sidebarPanel, BorderLayout.WEST);
        frame.add(contentScrollPane, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    // Cập nhật danh sách sách
    private void updateBookList() {
        List<Book> books = bookDatabase.getAllBooks();
        bookList.setListData(books.toArray(new Book[0]));
    }

    // Đọc sách khi bấm nút "Read Book"
    private void readSelectedBook() {
        Book selectedBook = bookList.getSelectedValue();
        if (selectedBook != null) {
            contentArea.setText("Title: " + selectedBook.getTitle() + "\n"
                    + "Author: " + selectedBook.getAuthor() + "\n\n"
                    + selectedBook.getContent());
        } else {
            JOptionPane.showMessageDialog(frame, "Please select a book to read!");
        }
    }

    // Thêm sách mới
    private void openAddBookDialog() {
        JTextField titleField = new JTextField();
        JTextField authorField = new JTextField();
        JTextField nameField = new JTextField();
        JTextArea contentField = new JTextArea(10, 30);

        int result = JOptionPane.showConfirmDialog(frame,
                new Object[]{
                        "Book Name:", nameField,
                        "Title:", titleField,
                        "Author:", authorField,
                        "Content:", new JScrollPane(contentField)
                },
                "Add Book", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            String name = nameField.getText();
            String title = titleField.getText();
            String author = authorField.getText();
            String content = contentField.getText();

            if (!name.isEmpty() && !title.isEmpty() && !author.isEmpty() && !content.isEmpty()) {
                Book newBook = new Book(name, title, author, content);
                if (bookDatabase.addBook(newBook)) {
                    updateBookList();
                    JOptionPane.showMessageDialog(frame, "Book added successfully!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Failed to add book!");
                }
            } else {
                JOptionPane.showMessageDialog(frame, "All fields are required!");
            }
        }
    }

    // Xóa sách
    private void deleteSelectedBook() {
        Book selectedBook = bookList.getSelectedValue();
        if (selectedBook != null) {
            int confirm = JOptionPane.showConfirmDialog(frame,
                    "Are you sure you want to delete \"" + selectedBook.getTitle() + "\"?",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                if (bookDatabase.deleteBook(selectedBook)) {
                    JOptionPane.showMessageDialog(frame, "Book deleted successfully!");
                    updateBookList();
                    contentArea.setText(""); // Xóa nội dung sách đang hiển thị
                } else {
                    JOptionPane.showMessageDialog(frame, "Failed to delete the book!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Please select a book to delete!");
        }
    }

    // Mở dialog để chỉnh sửa sách
    private void openEditBookDialog() {
        Book selectedBook = bookList.getSelectedValue();
        if (selectedBook != null) {
            JTextField titleField = new JTextField(selectedBook.getTitle());
            JTextField authorField = new JTextField(selectedBook.getAuthor());
            JTextArea contentField = new JTextArea(selectedBook.getContent(), 10, 30);

            int result = JOptionPane.showConfirmDialog(frame,
                    new Object[]{
                            "Title:", titleField,
                            "Author:", authorField,
                            "Content:", new JScrollPane(contentField)
                    },
                    "Edit Book", JOptionPane.OK_CANCEL_OPTION);

            if (result == JOptionPane.OK_OPTION) {
                String title = titleField.getText();
                String author = authorField.getText();
                String content = contentField.getText();

                if (!title.isEmpty() && !author.isEmpty() && !content.isEmpty()) {
                    Book updatedBook = new Book(selectedBook.getName(), title, author, content);
                    if (bookDatabase.updateBook(selectedBook, updatedBook)) {
                        updateBookList();
                        JOptionPane.showMessageDialog(frame, "Book updated successfully!");
                    } else {
                        JOptionPane.showMessageDialog(frame, "Failed to update book!");
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "All fields are required!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Please select a book to edit!");
        }
    }
}

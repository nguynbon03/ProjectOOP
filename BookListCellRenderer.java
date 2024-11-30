package ProjectOOP;

import ProjectOOP.Model.Book;

import javax.swing.*;
import java.awt.*;

public class BookListCellRenderer extends DefaultListCellRenderer {
    @Override
    public Component getListCellRendererComponent(
            JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (value instanceof Book) {
            Book book = (Book) value;
            setText(book.getTitle()); // Chỉ hiển thị tên truyện
        }
        return this;
    }
}

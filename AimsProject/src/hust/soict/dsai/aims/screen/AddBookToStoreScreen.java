package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.media.Book;
import hust.soict.dsai.aims.store.Store;

import javax.swing.*;
import java.util.Arrays;

public class AddBookToStoreScreen extends AddItemToStoreScreen {

    public AddBookToStoreScreen(Store store) {
        super(store);
        setTitle("Add Book to Store");
    }

    // Triển khai phương thức addItem() để thêm sách vào cửa hàng
    @Override
    public void addItem() {
        // Lấy thông tin từ các trường nhập liệu
        String title = titleField.getText();
        String category = categoryField.getText();
        float cost = Float.parseFloat(costField.getText());

        // Yêu cầu người dùng nhập tên tác giả
        String author = JOptionPane.showInputDialog(this, "Enter author:");

        // Tạo đối tượng Book và thêm vào cửa hàng
        Book book = new Book(title, category, cost, Arrays.asList(author));
        store.addMedia(book);

        // Hiển thị thông báo khi thêm thành công
        JOptionPane.showMessageDialog(this, "Book added to store!");

        // Quay lại màn hình StoreScreen để hiển thị cửa hàng
        this.setVisible(false);
        StoreScreen storeScreen = new StoreScreen(store);
        storeScreen.setVisible(true);
    }
}

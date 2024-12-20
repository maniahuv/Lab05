package hust.soict.dsai.aims.screen;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.Book;
import hust.soict.dsai.aims.store.Store;

public class CartScreen extends JFrame {
    private Cart cart;

    public CartScreen(Cart cart) {
        super();
        this.cart = cart;

        // Khởi tạo FX Panel để sử dụng JavaFX trong JFrame
        JFXPanel fxPanel = new JFXPanel();
        this.add(fxPanel);

        // Set title and visibility for the JFrame
        this.setTitle("Cart");
        this.setSize(1024, 768);  // Set kích thước cửa sổ
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Đảm bảo JFrame thoát đúng cách

        // Sử dụng JavaFX để tạo giao diện người dùng
        initializeJavaFX(fxPanel);
    }

    private void initializeJavaFX(JFXPanel fxPanel) {
        Platform.runLater(() -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/hust/soict/dsai/aims/screen/cart.fxml"));
                CartScreenController controller = new CartScreenController(cart);
                loader.setController(controller);
                Parent root = loader.load();
                fxPanel.setScene(new Scene(root));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static void main(String[] args) {
        // Khởi tạo giỏ hàng và thêm sản phẩm
        Store store = new Store();
        Cart cart = new Cart(store);

        List<String> authors = new ArrayList<>();
        authors.add("John Doe");
        authors.add("Jane Smith");

        Book book1 = new Book("DVD1's Title", "category 1", 7.87f, authors);
        Book book2 = new Book("CD2's Title", "category 2", 19.21f, authors);
        Book book3 = new Book("Book3's Title", "category 3", 14.61f, authors);

        cart.addMedia(book1);
        cart.addMedia(book2);
        cart.addMedia(book3);

        // Tạo và hiển thị CartScreen trong một luồng sự kiện Swing
        SwingUtilities.invokeLater(() -> {
            CartScreen cartScreen = new CartScreen(cart);  // Tạo đối tượng CartScreen
            cartScreen.setVisible(true); // Hiển thị CartScreen
        });
    }
}

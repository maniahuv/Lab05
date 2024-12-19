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

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.Book;

public class CartScreen extends JFrame {
    private Cart cart;
    public CartScreen(Cart cart){
        super();
        this.cart=cart;

        JFXPanel fxPanel=new JFXPanel();
        this.add(fxPanel);

        this.setTitle("Cart");
        this.setVisible(true);
        Platform.runLater(new Runnable() {
            @Override
            public void run(){
                try{
                    FXMLLoader loader=new FXMLLoader(getClass().getResource("/hust/soict/dsai/aims/screen/cart.fxml"));
                    CartScreenController controller=new CartScreenController(cart);
                    loader.setController(controller);
                    Parent root=loader.load();
                    fxPanel.setScene(new Scene(root));
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
            

        });
    }
    public static void main(String[] args) {
        // Khởi tạo giỏ hàng và thêm sản phẩm
        Cart cart = new Cart();
        List<String> authors = new ArrayList<>();
        authors.add("John Doe");
        authors.add("Jane Smith");
        
        Book book1 = new Book("DVD1's Title", "category 1", 7.87f, authors);
        Book book2 = new Book("CD2's Title ", "category 2", 19.21f, authors);
        Book book3 = new Book("Book3's Title", "category 3", 14.61f, authors);

        cart.addMedia(book1);
        cart.addMedia(book2);
        cart.addMedia(book3);

        // Tạo và hiển thị CartScreen
        CartScreen cartScreen = new CartScreen(cart);
    }
}

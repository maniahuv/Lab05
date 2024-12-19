package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.store.Store;
import hust.soict.dsai.aims.media.Book;
import hust.soict.dsai.aims.media.Media;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class StoreScreen extends JFrame {
    private Store store;
    private Cart cart;  

    public StoreScreen(Cart cart, Store store) {
        this.store = store;
        this.cart = cart;
        
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        cp.add(createNorth(), BorderLayout.NORTH);
        cp.add(createCenter(), BorderLayout.CENTER);

        setVisible(true);
        setTitle("Store");
        setSize(1024, 768);
    }

    JPanel createNorth() {
        JPanel north = new JPanel();
        north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
        north.add(createMenuBar());
        north.add(createHeader());
        return north;
    }

    JMenuBar createMenuBar() {
        JMenu menu = new JMenu("Options");
        JMenu smUpdateStore = new JMenu("Update Store");
        smUpdateStore.add(new JMenuItem("Add Book"));
        smUpdateStore.add(new JMenuItem("Add CD"));
        smUpdateStore.add(new JMenuItem("Add DVD"));

        menu.add(smUpdateStore);
        menu.add(new JMenuItem("View store"));
        JMenuItem viewCartItem = new JMenuItem("View cart");
        viewCartItem.addActionListener(e -> switchToCartScreen());
        menu.add(viewCartItem);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        menuBar.add(menu);

        return menuBar;
    }

    JPanel createHeader() {
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));

        JLabel title = new JLabel("AIMS");
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 50));
        title.setForeground(Color.CYAN);

        JButton cartButton = new JButton("View Cart");
        cartButton.setPreferredSize(new Dimension(100, 50));
        cartButton.setMaximumSize(new Dimension(100, 50));
        cartButton.addActionListener(e -> switchToCartScreen());

        header.add(Box.createRigidArea(new Dimension(10, 10)));
        header.add(title);
        header.add(Box.createHorizontalGlue());
        header.add(cartButton);
        header.add(Box.createRigidArea(new Dimension(10, 10)));

        return header;
    }

    JPanel createCenter() {
        JPanel center = new JPanel();
        center.setLayout(new GridLayout(3, 3, 2, 2));
        ArrayList<Media> mediaInStore = store.getItemInStore();
        int size = Math.min(mediaInStore.size(), 9);
        for (int i = 0; i < size; i++) {
            MediaStore cell = new MediaStore(mediaInStore.get(i));
            center.add(cell);
        }
        return center;
    }

    private void switchToCartScreen() {
        this.setVisible(false);
        CartScreen cartScreen = new CartScreen(cart);
        cartScreen.setVisible(true);
    }

    public static void main(String[] args) {
        Store store = new Store();
        store.addMedia(new Book("DVD1's Title", "category 1", 7.87f, Arrays.asList("John Doe", "Jane Smith")));
        store.addMedia(new Book("C++ Programming", "Programming", 40.0f, Arrays.asList("Alice Brown")));
        
        Cart cart = new Cart();  // Tạo đối tượng Cart

        // Khởi tạo StoreScreen và hiển thị cửa hàng
        StoreScreen storeScreen = new StoreScreen(cart, store);
        storeScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Đảm bảo ứng dụng đóng đúng cách khi thoát
    }
}

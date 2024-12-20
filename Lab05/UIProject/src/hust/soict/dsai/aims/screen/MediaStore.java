package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.Media;

import javax.swing.*;
import java.awt.*;

public class MediaStore extends JPanel {
    private Media media;
    private Cart cart;

    public MediaStore(Media media, Cart cart) {
        this.media = media;
        this.cart = cart;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel title = new JLabel(media.getTitle());
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btnAddToCart = new JButton("Add to Cart");
        btnAddToCart.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnAddToCart.addActionListener(e -> addToCart());

        add(title);
        add(Box.createRigidArea(new Dimension(0, 10))); // Khoảng cách giữa các thành phần
        add(btnAddToCart);
    }

    private void addToCart() {
        cart.addMedia(media);
        JOptionPane.showMessageDialog(this, media.getTitle() + " has been added to the cart.");
    }
}

package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.store.Store;

import javax.swing.*;
import java.awt.*;

public abstract class AddItemToStoreScreen extends JFrame {
    protected Store store;
    protected JTextField titleField;
    protected JTextField categoryField;
    protected JTextField costField;

    public AddItemToStoreScreen(Store store) {
        this.store = store;
        setLayout(new FlowLayout());

        JLabel titleLabel = new JLabel("Title:");
        titleField = new JTextField(20);
        JLabel categoryLabel = new JLabel("Category:");
        categoryField = new JTextField(20);
        JLabel costLabel = new JLabel("Cost:");
        costField = new JTextField(10);

        // Add common components
        add(titleLabel);
        add(titleField);
        add(categoryLabel);
        add(categoryField);
        add(costLabel);
        add(costField);

        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> addItem());
        add(addButton);

        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public abstract void addItem();
}

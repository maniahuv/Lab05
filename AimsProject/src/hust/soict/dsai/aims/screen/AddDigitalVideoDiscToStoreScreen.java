package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.media.DigitalVideoDisc;
import hust.soict.dsai.aims.store.Store;

import javax.swing.*;
import java.util.Arrays;

public class AddDigitalVideoDiscToStoreScreen extends AddItemToStoreScreen {
    public AddDigitalVideoDiscToStoreScreen(Store store) {
        super(store);
        setTitle("Add DVD to Store");
    }

    @Override
    public void addItem() {
        String title = titleField.getText();
        String category = categoryField.getText();
        float cost = Float.parseFloat(costField.getText());

        // DVD-specific fields
        String director = JOptionPane.showInputDialog(this, "Enter director:");

        DigitalVideoDisc dvd = new DigitalVideoDisc(title, category, cost, director);
        store.addMedia(dvd);

        JOptionPane.showMessageDialog(this, "DVD added to store!");
        this.setVisible(false);
        // Go back to the Store screen
        StoreScreen storeScreen = new StoreScreen(store);
        storeScreen.setVisible(true);
    }
}

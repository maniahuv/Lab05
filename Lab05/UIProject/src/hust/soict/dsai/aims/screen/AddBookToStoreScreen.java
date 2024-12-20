package hust.soict.dsai.aims.screen;

import java.util.Arrays;

import hust.soict.dsai.aims.media.Book;
import hust.soict.dsai.aims.store.Store;

import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.control.Alert;

public class AddBookToStoreScreen extends AddItemToStoreScreen {
    private TextField tfAuthors;

    public AddBookToStoreScreen(Store store) {
        super(store);

        tfAuthors = new TextField();
        tfAuthors.setPromptText("Enter authors (comma separated)");
        ((VBox) this.getScene().getRoot()).getChildren().add(3, tfAuthors); // Thêm trường nhập sau `tfCost`

        this.setTitle("Add Book to Store");
    }

    @Override
    protected void addItem() {
        try {
            String title = tfTitle.getText();
            String category = tfCategory.getText();
            float cost = Float.parseFloat(tfCost.getText());
            String authorsText = tfAuthors.getText();
            String[] authors = authorsText.split(",");

            Book newBook = new Book(title, category, cost, Arrays.asList(authors));
            store.addMedia(newBook);

            showSuccessAlert("Book added successfully!");
            clearFields();
        } catch (NumberFormatException e) {
            showErrorAlert("Invalid cost! Please enter a numeric value.");
        }
    }

    private void showSuccessAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearFields() {
        tfTitle.clear();
        tfCategory.clear();
        tfCost.clear();
        tfAuthors.clear();
    }
    
}

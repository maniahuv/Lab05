package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.media.DigitalVideoDisc;
import hust.soict.dsai.aims.store.Store;

import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.control.Alert;

public class AddDigitalVideoDiscToStoreScreen extends AddItemToStoreScreen {
    private TextField tfDirector;

    public AddDigitalVideoDiscToStoreScreen(Store store) {
        super(store);

        tfDirector = new TextField();
        tfDirector.setPromptText("Enter director");
        ((VBox) this.getScene().getRoot()).getChildren().add(3, tfDirector); // Thêm trường nhập sau `tfCost`

        this.setTitle("Add Digital Video Disc to Store");
    }

    @Override
    protected void addItem() {
        try {
            String title = tfTitle.getText();
            String category = tfCategory.getText();
            float cost = Float.parseFloat(tfCost.getText());
            DigitalVideoDisc newDVD = new DigitalVideoDisc(title, category, cost);
            store.addMedia(newDVD);

            showSuccessAlert("Digital Video Disc added successfully!");
            clearFields();
        } catch (NumberFormatException e) {
            showErrorAlert("Invalid cost! Please enter a numeric value.");
        }
    }

    private void clearFields() {
        tfTitle.clear();
        tfCategory.clear();
        tfCost.clear();
        tfDirector.clear();
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
}

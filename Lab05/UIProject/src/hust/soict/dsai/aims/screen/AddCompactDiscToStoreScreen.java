package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.media.CompactDisc;
import hust.soict.dsai.aims.store.Store;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.control.Alert;
import java.util.ArrayList;

public class AddCompactDiscToStoreScreen extends AddItemToStoreScreen {
    private TextField tfArtist;

    public AddCompactDiscToStoreScreen(Store store) {
        super(store);

        tfArtist = new TextField();
        tfArtist.setPromptText("Enter artist");
        ((VBox) this.getScene().getRoot()).getChildren().add(3, tfArtist); // Thêm trường nhập sau `tfCost`

        this.setTitle("Add Compact Disc to Store");
    }

    @Override
    protected void addItem() {
        try {
            String title = tfTitle.getText();
            String category = tfCategory.getText();
            float cost = Float.parseFloat(tfCost.getText());
            String artist = tfArtist.getText();

            // Khởi tạo CompactDisc với 4 tham số: title, category, cost và artist
            CompactDisc newCD = new CompactDisc(title, category, cost, artist, new ArrayList<>());  // Thêm mảng track rỗng nếu không có track nào

            store.addMedia(newCD);

            showSuccessAlert("Compact Disc added successfully!");
            clearFields();
        } catch (NumberFormatException e) {
            showErrorAlert("Invalid cost! Please enter a numeric value.");
        }
    }

    private void clearFields() {
        tfTitle.clear();
        tfCategory.clear();
        tfCost.clear();
        tfArtist.clear();
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

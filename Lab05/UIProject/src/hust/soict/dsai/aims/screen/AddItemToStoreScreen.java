package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.store.Store;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public abstract class AddItemToStoreScreen extends Stage {
    protected TextField tfTitle;
    protected TextField tfCategory;
    protected TextField tfCost;
    protected Store store;

    public AddItemToStoreScreen(Store store) {
        this.store = store;

        tfTitle = new TextField();
        tfTitle.setPromptText("Enter title");

        tfCategory = new TextField();
        tfCategory.setPromptText("Enter category");

        tfCost = new TextField();
        tfCost.setPromptText("Enter cost");

        Button btnAdd = new Button("Add Item");
        btnAdd.setOnAction(e -> addItem());

        VBox layout = new VBox(10, tfTitle, tfCategory, tfCost, btnAdd);
        layout.setPrefWidth(300);

        Scene scene = new Scene(layout);
        this.setScene(scene);
    }

    protected abstract void addItem();
}

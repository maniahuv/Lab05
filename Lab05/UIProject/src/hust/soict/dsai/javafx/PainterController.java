package hust.soict.dsai.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;


public class PainterController{
    @FXML
    private Pane drawingAreaPane;

    @FXML
    private RadioButton penRadioButton;  // Pen RadioButton

    @FXML
    private RadioButton eraseRadioButton;  // Erase RadioButton
    @FXML
    private ToggleGroup penEraseGroup;  // ToggleGroup cho Pen và Erase

    private boolean isPenSelected = true; // Mặc định là Pen

    @FXML
    void penSelected(ActionEvent event) {
        isPenSelected = true; // Chọn Pen
    }

    @FXML
    void eraseSelected(ActionEvent event) {
        isPenSelected = false; // Chọn Erase
    }
    @FXML
    void clearButtonPressed(ActionEvent event){
        drawingAreaPane.getChildren().removeIf(node -> node instanceof Circle);
    }

    @FXML
     void drawingAreaMouseDragged(MouseEvent event) {
        if (isPenSelected) {
            // Vẽ bằng Pen (Circle)
            Circle newCircle = new Circle(event.getX(), event.getY(), 4, Color.BLACK);
            drawingAreaPane.getChildren().add(newCircle);
        } else {
            // Xóa bằng Erase (Rectangle màu trắng)
            Rectangle eraseRect = new Rectangle(event.getX() - 10, event.getY() - 10, 20, 20);
            eraseRect.setFill(Color.WHITE); // Xóa bằng cách vẽ hình trắng lên
            drawingAreaPane.getChildren().add(eraseRect);
        }
    }
}
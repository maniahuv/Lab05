package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.exception.PlayerException;
import hust.soict.dsai.aims.media.Disc;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.media.Playable;
import hust.soict.dsai.aims.store.Store;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.beans.value.ChangeListener;
import javafx.scene.Node;


public class CartScreenController {
    private Cart cart;
    @FXML
    private MenuItem viewStoreButton;
    @FXML
    private Label totalLabel;
    @FXML
    private ToggleGroup filterCategory;
    @FXML
    private Button btnPlaceOrder;
    @FXML
    private Button btnPlay;
    @FXML
    private Button btnRemove;
    @FXML
    private TableView<Media> tblMedia;
    @FXML
    private TableColumn<Media, String> colMediaTitle;
    @FXML
    private TableColumn<Media, String> colMediacategory;
    @FXML
    private TableColumn<Media, Float> colMediaCost;

    public CartScreenController(Cart cart){
        super();
        this.cart=cart;
    }
    @FXML
    private void initialize() {
        colMediaTitle.setCellValueFactory(new PropertyValueFactory<Media, String>("title"));
        colMediacategory.setCellValueFactory(new PropertyValueFactory<Media, String>("category"));
        colMediaCost.setCellValueFactory(new PropertyValueFactory<Media, Float>("cost"));
        
        ObservableList<Media> mediaList = FXCollections.observableArrayList(this.cart.getItemsOrdered());
        tblMedia.setItems(mediaList);

        btnPlay.setVisible(false);
        btnRemove.setVisible(false);
        updateTotalLabel();
        tblMedia.getSelectionModel().selectedItemProperty().addListener(
            new ChangeListener<Media>() {
                @Override
                public void changed(ObservableValue<? extends Media> observable, Media oldValue, Media newValue) {
                    if (newValue != null) {
                        updateButtonBar(newValue);
                    }
                }
            }
        );
        cart.getItemsOrdered().addListener((ListChangeListener<? super Media>) change -> updateTotalLabel());

    }
    


    @FXML
void btnPlaceOrderPressed() {
    // Lưu đơn hàng
    // Bạn có thể thực hiện việc lưu vào cơ sở dữ liệu hoặc tạo một đối tượng đơn hàng
    placeOrder();

    // Thông báo cho người dùng về việc đặt hàng thành công
    showConfirmationDialog();

    // Làm trống giỏ hàng sau khi đặt hàng thành công (nếu cần)
    cart.clear();
    
    // Cập nhật lại bảng giỏ hàng và tổng chi phí
    tblMedia.setItems(FXCollections.observableArrayList(cart.getItemsOrdered()));
    updateTotalLabel();
}

private void placeOrder() {
    // Tạo một đơn hàng mới từ các sản phẩm trong giỏ hàng
    // Ví dụ: Lưu đơn hàng vào cơ sở dữ liệu hoặc chỉ đơn giản là lưu vào một danh sách đơn hàng
    System.out.println("Đơn hàng đã được đặt thành công với các sản phẩm: ");
    for (Media media : cart.getItemsOrdered()) {
        System.out.println(media.getTitle() + " - $" + media.getCost());
    }
}

private void showConfirmationDialog() {
    // Hiển thị một hộp thoại xác nhận đơn hàng
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Đặt hàng thành công");
    alert.setHeaderText("Đơn hàng của bạn đã được đặt thành công!");
    alert.setContentText("Cảm ơn bạn đã mua sắm tại cửa hàng của chúng tôi.");
    alert.showAndWait();
}


    void updateButtonBar(Media media){
        btnRemove.setVisible(true);
        if(media instanceof Playable){
            btnPlay.setVisible(true);
        }else {
            btnPlay.setVisible(false);
        }
    }
    void updateTotalLabel() {
        float total = 0;
        for (Media media : cart.getItemsOrdered()) {
            total += media.getCost();
        }
        totalLabel.setText("Tổng: $" + total);
    }
    @FXML
    void btnRemovePressed(ActionEvent event){
        Media media=tblMedia.getSelectionModel().getSelectedItem();
       if (media != null) {
            // Xóa media khỏi cart
            cart.removeMedia(media);
            updateTotalLabel();
            // Cập nhật lại ObservableList
            ObservableList<Media> mediaList = FXCollections.observableArrayList(this.cart.getItemsOrdered());
            tblMedia.setItems(mediaList); // Làm mới TableView để phản ánh thay đổi
        }
    }
    @FXML
    void btnPlayPressed() throws PlayerException {
        // Lấy đối tượng media được chọn từ bảng
        Media media = tblMedia.getSelectionModel().getSelectedItem();
        
        // Kiểm tra nếu media là Playable
        if (media instanceof Playable) {
            // Gọi phương thức play() của đối tượng Playable
            ((Playable) media).play();
            
            // Cập nhật giao diện sau khi phát (nếu cần)
            // Ví dụ: Ẩn nút Play sau khi phát xong
            btnPlay.setVisible(false);
        } else {
            System.out.println("Selected media is not playable.");
        }
    }
    @FXML
    void handleViewStore(ActionEvent event) {
        // Lấy Stage hiện tại từ sự kiện
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow(); // Lấy Stage từ Scene của node
    
        // Đóng cửa sổ hiện tại (CartScreen)
        stage.close();
    
        // Mở cửa sổ StoreScreen
        Store store = cart.getStore();
        StoreScreen storeScreen = new StoreScreen(cart, store);
        storeScreen.setVisible(true);
    }
    public void handleAddBook(ActionEvent event) {
        // Logic xử lý khi bấm nút "Add Book"
    }
    public void handleAddCD() {
        // Thêm hành động khi nhấn nút
        System.out.println("Add CD clicked!");
    }
    public void handleAddDVD() {
    // Giả sử bạn đã có thông tin của Disc trong một số biến
    String title = "DVD1's Title"; // Thay thế bằng cách lấy giá trị từ TextField nếu cần
    String category = "category 1"; // Thay thế bằng cách lấy giá trị từ TextField nếu cần
    float cost = 15.99f;  // Thay thế bằng cách lấy giá trị từ TextField nếu cần
    int length = 120; // Ví dụ chiều dài của Disc
    String director = "John Doe"; // Ví dụ tên đạo diễn

    // Tạo Disc mới
    Disc disc = new Disc(title, category, cost, length, director);

    // Thêm vào store
    Store store = cart.getStore();  // Lấy store từ cart
    store.addMedia(disc);

    // Cập nhật giao diện hoặc làm điều gì đó sau khi thêm
    System.out.println("Added Disc to Store: " + title);
}
}

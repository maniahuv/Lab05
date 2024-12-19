package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.media.CompactDisc;
import hust.soict.dsai.aims.store.Store;

import javax.swing.*;
import java.util.Arrays;

public class AddCompactDiscToStoreScreen extends AddItemToStoreScreen {

    public AddCompactDiscToStoreScreen(Store store) {
        super(store);
        setTitle("Add Compact Disc to Store");
    }

    @Override
    public void addItem() {
        // Lấy thông tin từ các trường nhập liệu
        String title = titleField.getText();
        String category = categoryField.getText();
        float cost = Float.parseFloat(costField.getText());

        // Yêu cầu người dùng nhập tên nghệ sĩ
        String artist = JOptionPane.showInputDialog(this, "Enter artist:");

        // Tạo đối tượng CompactDisc và thêm vào cửa hàng
        CompactDisc cd = new CompactDisc(title, category, cost, Arrays.asList(artist));
        store.addMedia(cd);

        // Hiển thị thông báo khi thêm thành công
        JOptionPane.showMessageDialog(this, "Compact Disc added to store!");

        // Quay lại màn hình StoreScreen để hiển thị cửa hàng
        this.setVisible(false);
        StoreScreen storeScreen = new StoreScreen(store);
        storeScreen.setVisible(true);
    }
}

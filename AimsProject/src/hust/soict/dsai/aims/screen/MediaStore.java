package hust.soict.dsai.aims.screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.media.Playable;

public class MediaStore extends JPanel {
    private Media media;

    public MediaStore(Media media) {
        this.media = media;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel title = new JLabel(media.getTitle());
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 20));
        title.setAlignmentX(CENTER_ALIGNMENT);

        JLabel cost = new JLabel("" + media.getCost() + " $");
        cost.setAlignmentX(CENTER_ALIGNMENT);

        JPanel container = new JPanel();
        container.setLayout(new FlowLayout(FlowLayout.CENTER));

        // "Add to cart" button
        JButton addToCartButton = new JButton("Add to cart");
        addToCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Simulate adding to cart (for now, print to console)
                System.out.println(media.getTitle() + " added to the cart.");
            }
        });

        container.add(addToCartButton);

        // "Play" button for playable media
        if (media instanceof Playable) {
            JButton playButton = new JButton("Play");
            playButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Show a dialog window simulating the play action
                    JDialog playDialog = new JDialog();
                    playDialog.setTitle("Playing " + media.getTitle());
                    playDialog.setSize(300, 100);
                    JLabel playLabel = new JLabel("Playing media: " + media.getTitle(), SwingConstants.CENTER);
                    playDialog.add(playLabel);
                    playDialog.setModal(true);  // Make the dialog modal so the user can close it
                    playDialog.setVisible(true);
                }
            });
            container.add(playButton);
        }

        // Add components to the MediaStore panel
        this.add(Box.createVerticalGlue());
        this.add(title);
        this.add(cost);
        this.add(Box.createVerticalGlue());
        this.add(container);

        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }
}

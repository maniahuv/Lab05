package hust.soict.dsai.aims.store;
import java.util.ArrayList;
import hust.soict.dsai.aims.media.Media;

public class Store {
    public static int maxItemsInStore = 100;
    private ArrayList<Media> itemsInStore = new ArrayList<Media>();
    private static int qtyAvailable = 0;
    public Store() {
        new ArrayList<>();
    }
    public void addMedia(Media media) {
        if (qtyAvailable < maxItemsInStore) {
            itemsInStore.add(media);
            qtyAvailable++;
            System.out.println("Added to store: " + media.getTitle());
        } else {
            System.out.println("The store is full. Cannot add more items.");
        }
    }

    public void removeMedia(Media media) {
        if (itemsInStore.remove(media)) {
            qtyAvailable--;
            System.out.println("Removed from store: " + media.getTitle());
        } else {
            System.out.println("Media not found in store.");
        }
    }

    public Media searchByTitle(String title) {
        for (Media media : itemsInStore) {
            if (title.equalsIgnoreCase(media.getTitle())) {
                return media;
            }
        }
        System.out.println("No media found with title: " + title);
        return null;
    }
    

    public ArrayList<Media> getItemInStore() {
        return itemsInStore;
    }
}

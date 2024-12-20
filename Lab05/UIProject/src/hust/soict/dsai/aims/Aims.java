package hust.soict.dsai.aims;
import java.util.Scanner;

import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.media.Playable;
import hust.soict.dsai.aims.media.DigitalVideoDisc;
import hust.soict.dsai.aims.store.Store;
import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.exception.PlayerException;


public class Aims {
    private static Store store = new Store();
    private static Cart cart = new Cart(store);

    public static void main(String[] args) throws PlayerException {
        store.addMedia(new DigitalVideoDisc("The Matrix", "Sci-Fi", "Wachowski", 136, 19.99f));
        store.addMedia(new DigitalVideoDisc("Inception", "Sci-Fi", "Christopher Nolan", 148, 14.99f));
        
        // Menu
        Scanner scanner = new Scanner(System.in);
        while (true) {
            showMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    viewStoreMenu(scanner);
                    break;
                case 2:
                    updateStoreMenu(scanner);
                    break;
                case 3:
                    cartMenu(scanner);
                    break;
                case 0:
                    System.out.println("\nExiting system...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    // Main Menu
    public static void showMenu() {
        System.out.println("\nAIMS: ");
        System.out.println("--------------------------------");
        System.out.println("1. View store");
        System.out.println("2. Update store");
        System.out.println("3. See current cart");
        System.out.println("0. Exit");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-2-3");
    }

    // View Store Menu
    public static void viewStoreMenu(Scanner scanner) throws PlayerException {
        while (true) {
            storeMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();  

            switch (choice) {
                case 1:
                    seeMediaDetails(scanner);
                    break;
                case 2:
                    addMediaToCart(scanner);
                    break;
                case 3:
                    playMedia(scanner);
                    break;
                case 4:
                    cart.print();
                    break;
                case 0:
                    return;  // Trở lại main menu
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    // Store Menu
    public static void storeMenu() {
        System.out.println("\nOptions: ");
        System.out.println("--------------------------------");
        System.out.println("1. See a media's details");
        System.out.println("2. Add a media to cart");
        System.out.println("3. Play a media");
        System.out.println("4. See current cart");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-2-3-4");
    }

    // See Media Details
    public static void seeMediaDetails(Scanner scanner) throws PlayerException {
        System.out.print("\nEnter the title of the media: ");
        String title = scanner.nextLine();
        Media media = store.searchByTitle(title);
        if (media != null) {
            System.out.println(media.toString());
            mediaDetailsMenu(scanner, media);
        } else {
            System.out.println("Media not found.");
        }
    }

    // Media Details Menu
    public static void mediaDetailsMenu(Scanner scanner, Media media) throws PlayerException {
        while (true) {
            System.out.println("\nOptions: ");
            System.out.println("--------------------------------");
            System.out.println("1. Add to cart");
            System.out.println("2. Play");
            System.out.println("0. Back");
            System.out.println("--------------------------------");
            System.out.print("Please choose a number: 0-1-2: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  

            switch (choice) {
                case 1:
                    cart.addMedia(media);
                    System.out.println("Added to cart.");
                    return;
                case 2:
                    if (media instanceof Playable) {
                        ((Playable) media).play();
                    } else {
                        System.out.println("This media cannot be played.");
                    }
                    return;
                case 0:
                    return; 
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    // Add Media to Cart
    public static void addMediaToCart(Scanner scanner) {
        System.out.print("\nEnter the title of the media to add to cart: ");
        String title = scanner.nextLine();
        Media media = store.searchByTitle(title);
        if (media != null) {
            cart.addMedia(media);
            System.out.println("Media added to cart. Current number of DVDs: " + cart.getItemsOrdered().size());
        } else {
            System.out.println("Media not found in the store.");
        }
    }

    // Play Media
    public static void playMedia(Scanner scanner) {
        System.out.print("\nEnter the title of the media to play: ");
        String title = scanner.nextLine();
        Media media = store.searchByTitle(title);
        if (media != null && media instanceof Playable) {
            try {
                ((Playable) media).play();  // Call play() method
            } catch (PlayerException e) {
                // Xử lý ngoại lệ
                System.err.println("Error occurred while playing media: " + e.getMessage());
                e.printStackTrace();  // Print the stack trace for debugging
                // Hiển thị thông báo lỗi cho người dùng
                javax.swing.JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Play Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        } else {
            System.out.println("Media not found or cannot be played.");
        }
    }

    // Update Store Menu
    public static void updateStoreMenu(Scanner scanner) {
        while (true) {
            System.out.println("\nOptions: ");
            System.out.println("--------------------------------");
            System.out.println("1. Add a media to store");
            System.out.println("2. Remove a media from store");
            System.out.println("0. Back");
            System.out.println("--------------------------------");
            System.out.print("Please choose a number: 0-1-2: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    addMediaToStore(scanner);
                    break;
                case 2:
                    removeMediaFromStore(scanner);
                    break;
                case 0:
                    return;  // Back to main menu
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    // Add Media to Store
    public static void addMediaToStore(Scanner scanner) {
        System.out.print("\nEnter the title of the media to add: ");
        String title = scanner.nextLine();
        System.out.print("Enter the category: ");
        String category = scanner.nextLine();
        System.out.print("Enter the cost: ");
        float cost = scanner.nextFloat();
        scanner.nextLine(); // Consume newline
        Media media = new DigitalVideoDisc(title, category, cost);
        store.addMedia(media);
        System.out.println("Media added to store.");
    }

    // Remove Media from Store
    public static void removeMediaFromStore(Scanner scanner) {
        System.out.print("\nEnter the title of the media to remove: ");
        String title = scanner.nextLine();
        Media media = store.searchByTitle(title);
        if (media != null) {
            store.removeMedia(media);
            System.out.println("Media removed from store.");
        } else {
            System.out.println("Media not found.");
        }
    }

    // Cart Menu
    public static void cartMenu(Scanner scanner) throws PlayerException {
        while (true) {
            System.out.println("\nOptions: ");
            System.out.println("--------------------------------");
            System.out.println("1. Filter medias in cart");
            System.out.println("2. Sort medias in cart");
            System.out.println("3. Remove media from cart");
            System.out.println("4. Play a media");
            System.out.println("5. Place order");
            System.out.println("0. Back");
            System.out.println("--------------------------------");
            System.out.print("Please choose a number: 0-1-2-3-4-5: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    filterMediasInCart(scanner);
                    break;
                case 2:
                    sortMediasInCart(scanner);
                    break;
                case 3:
                    removeMediaFromCart(scanner);
                    break;
                case 4:
                    playMedia(scanner);
                    break;
                case 5:
                    placeOrder();
                    return;
                case 0:
                    return;  // Back to main menu
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    // Filter Medias in Cart
    public static void filterMediasInCart(Scanner scanner) {
        System.out.println("\nFilter by: ");
        System.out.println("1. ID");
        System.out.println("2. Title");
        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        switch (choice) {
            case 1:
                // Implement ID-based filtering
                break;
            case 2:
                // Implement title-based filtering
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    // Sort Medias in Cart
    public static void sortMediasInCart(Scanner scanner) {
        System.out.println("\nSort by: ");
        System.out.println("1. Title");
        System.out.println("2. Cost");
        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        switch (choice) {
            case 1:
                cart.sortByTitle();
                break;
            case 2:
                cart.sortByCost();
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    // Remove Media from Cart
    public static void removeMediaFromCart(Scanner scanner) {
        System.out.print("\nEnter the title of the media to remove: ");
        String title = scanner.nextLine();
        Media media = cart.searchByTitle(title);
        if (media != null) {
            cart.removeMedia(media);
            System.out.println("Media removed from cart.");
        } else {
            System.out.println("Media not found in cart.");
        }
    }

    // Place Order
    public static void placeOrder() {
        System.out.println("\nOrder created. Your cart is now empty.");
        cart.clear();
    }
    
}
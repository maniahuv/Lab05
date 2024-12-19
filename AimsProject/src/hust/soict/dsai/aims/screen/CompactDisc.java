package hust.soict.dsai.aims.screen;

import java.util.List;

import hust.soict.dsai.aims.media.Media;

public class CompactDisc extends Media {
    private List<String> artists;

    // Constructor phù hợp với các tham số: title, category, cost, artists
    public CompactDisc(String title, String category, float cost, List<String> artists) {
        super(title, category, cost);
        this.artists = artists;
    }

    public List<String> getArtists() {
        return artists;
    }

    public void setArtists(List<String> artists) {
        this.artists = artists;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toString'");
    }
    
    // Các phương thức khác nếu cần
}

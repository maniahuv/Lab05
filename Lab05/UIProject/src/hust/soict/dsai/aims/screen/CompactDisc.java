package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.exception.PlayerException;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.media.Track;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class CompactDisc extends Media {
    private String artist;
    private List<Track> tracks; // Danh sách các track

    // Constructor với 3 tham số (title, category, cost)
    public CompactDisc(String title, String category, float cost) {
        super(title, category, cost);
        this.tracks = new ArrayList<>(); // Khởi tạo danh sách track
    }

    // Constructor với 4 tham số (title, category, cost, artist)
    public CompactDisc(String title, String category, float cost, String artist) {
        super(title, category, cost);
        this.artist = artist;
        this.tracks = new ArrayList<>(); // Khởi tạo danh sách track
    }

    public String getArtist() {
        return artist;
    }

    // Phương thức để thêm track vào CD
    public void addTrack(Track track) {
        this.tracks.add(track);
    }

    @Override
    public String toString() {
        // Trả về thông tin về CompactDisc
        return "CompactDisc [Title=" + getTitle() + ", Artist=" + artist + ", Category=" + getCategory() + ", Cost=" + getCost() + "]";
    }

    public void play() throws PlayerException {
        // Kiểm tra nếu CD có độ dài hợp lệ
        if (this.getLength() > 0) {
            // Phát từng track trong danh sách tracks
            Iterator<Track> iter = tracks.iterator();
            while (iter.hasNext()) {
                Track nextTrack = iter.next();
                nextTrack.play(); // Phát track
            }
        } else {
            throw new PlayerException("Error: CD length is non-positive!");
        }
    }

    // Phương thức tính tổng độ dài của tất cả các track
    private int getLength() {
        int totalLength = 0;
        for (Track track : tracks) {
            totalLength += track.getLength(); // Cộng độ dài của từng track
        }
        return totalLength;
    }
}

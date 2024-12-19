package hust.soict.dsai.aims.media;

import java.util.ArrayList;

public class CompactDisc extends Media implements Playable {
    private String artist;
    private ArrayList<Track> tracks;
    
    public String getArtist() {
        return artist;
    }
    
    public CompactDisc(String title, String category, float cost, String artist, ArrayList<Track> tracks) {
        super(title, category, cost);
        this.artist = artist;
        this.tracks = tracks;
    }

    public CompactDisc(String title, String artist, ArrayList<Track> tracks) {
        super(title);
        this.artist = artist;
        this.tracks = tracks;
    }

    public void addTrack(Track trackName) {
        boolean a=false;
        for (Track track : tracks){
            if (track.equals(trackName)){
                a=true;
                System.out.println("Error, cannot add authorname!");
            }
        }
        if (a==false) tracks.add(trackName);
    }
    public void removeTrack(Track trackName) {
        boolean a=false;
        for (Track track : tracks){
            if (track.equals(trackName)){
                a=true;
                tracks.remove(trackName);
                System.out.println("Remove success!");
            }
        }
        if (a==false) tracks.add(trackName);
    }
    public int getLength() {
        int leng = 0 ;
        for (Track track : tracks) {
            leng += track.getLength();
        }
        return leng;
    }

    public void play(){
        for(Track track : tracks){
            track.play();
        }
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toString'");
    }

}

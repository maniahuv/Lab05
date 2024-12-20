package hust.soict.dsai.aims.media;

import java.util.Comparator;

public class MediaComparatorByCostTitle implements Comparator<Media> {
    public int compare(Media o1, Media o2) {
        int cos = Double.compare(o1.getCost(),o2.getCost());
        if (cos != 0 ) return cos;
        return (o1.getTitle()).compareTo(o2.getTitle());
    } 
}
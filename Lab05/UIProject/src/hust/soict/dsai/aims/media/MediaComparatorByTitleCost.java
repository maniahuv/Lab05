package hust.soict.dsai.aims.media;

import java.util.Comparator;


public class MediaComparatorByTitleCost implements Comparator<Media> {
    public int compare(Media o1, Media o2) {
        int cos = (o1.getTitle()).compareTo(o2.getTitle());
        if (cos != 0 ) return cos;
        return Double.compare(o1.getCost(),o2.getCost());
    } 
}
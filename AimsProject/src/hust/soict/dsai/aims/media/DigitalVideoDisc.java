package hust.soict.dsai.aims.media;

public class DigitalVideoDisc extends Disc implements Playable{
    private static int nbDigitalVideoDiscs = 0;

    public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
        super(title, category, cost, length, director);
        nbDigitalVideoDiscs++;
        setId(nbDigitalVideoDiscs);
    }
    public DigitalVideoDisc(String title, String category, float cost) {
        super(title, category, cost);
        nbDigitalVideoDiscs++;
        setId(nbDigitalVideoDiscs);
    }
    public DigitalVideoDisc(String title) {
        super(title);
        nbDigitalVideoDiscs++;
        setId(nbDigitalVideoDiscs);
    }
    public String toString() {
        return "DVD - " + getTitle() + " - " + getCategory() + " - " + getDirector() + " - " + getLength() + ": " + getCost() + " $";
    }
    public boolean isMatch(String title) {
        return title.equals(getTitle());
    }
    @Override
    public void play() {
        System.out.println("Playing DVD: " + this.getTitle());
        System.out.println("DVD length: " + this.getLength());
    }

    //Ghi đè compareTo trong Media
    public int compareTo(Media other) {
        if (other instanceof DigitalVideoDisc) {
            DigitalVideoDisc otherDVD = (DigitalVideoDisc) other;
            int titleComparison = this.getTitle().compareTo(otherDVD.getTitle());            
            if (titleComparison != 0) 
                return titleComparison;  
            int lengthComparison = Integer.compare(this.getLength(), otherDVD.getLength());
            if (lengthComparison != 0)
                return lengthComparison;
            return Float.compare(this.getCost(), otherDVD.getCost()); 
        }
        return super.compareTo(other);   
    }
}
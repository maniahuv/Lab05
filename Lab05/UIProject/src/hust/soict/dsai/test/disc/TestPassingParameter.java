package hust.soict.dsai.test.disc;
import hust.soict.dsai.aims.media.DigitalVideoDisc;

public class TestPassingParameter {
    public static void main(String[] args){
        DigitalVideoDisc jungleDVD = new DigitalVideoDisc("jungle");
        DigitalVideoDisc cinderellaDVD = new DigitalVideoDisc("Cinderella");

        swap(jungleDVD, cinderellaDVD);
        System.out.println("jungle dvd title: " + jungleDVD.getTitle());
        System.out.println("Cinderella dvd title: " + cinderellaDVD.getTitle());
        
        changeTitle(jungleDVD, cinderellaDVD.getTitle());
        System.out.println("jungle dvd title: " + jungleDVD.getTitle());
    }

    public static void swap(Object o1, Object o2) {  
        DigitalVideoDisc dvd1 = (DigitalVideoDisc) o1;
        DigitalVideoDisc dvd2 = (DigitalVideoDisc) o2;
        String tmp = dvd1.getTitle();
        dvd1.setTitle(dvd2.getTitle());
        dvd2.setTitle(tmp);
    }

    public static void changeTitle(DigitalVideoDisc dvd, String title){ //truyền giá trị dvd và thay đổi title, còn truyền tham chiếu không làm thay đổi giá trị
        String oldTitle=dvd.getTitle(); //jungle
        dvd.setTitle(title);    //jungle.title="cinderella"
        dvd = new DigitalVideoDisc(oldTitle); // jungle.title="jungle"
    }
}
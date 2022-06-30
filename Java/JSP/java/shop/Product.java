package shop;

public class Product {
    public String PID;
    public String artist;
    public String title;
    public String description;
    public int price;
    public String thumbnail;
    public String fullimage;


    public Product(
            String PID, String artist, String title,
            String description, int price, String thumbnail, String fullimage) {
        this.PID = PID;
        this.artist = artist;
        this.title = title;
        this.description = description;
        this.price = price;
        this.thumbnail = thumbnail;
        this.fullimage = fullimage;
    }

    public String toString() {
        return title + "\t " + price;
    }


    public String formatPrice(){
        return formatPrice(this.price);
    }


    public static String formatPrice(int price) {
        String convertedP = Integer.toString(price);
        if(convertedP.length() == 1){
            convertedP="0.0"+ convertedP;
        }
        else if (convertedP.length() == 2) convertedP = "0." + convertedP;
        else convertedP = convertedP.substring(0, convertedP.length() - 2) + "." + convertedP.substring(convertedP.length() - 2);

        return " £" + convertedP;
    }


}

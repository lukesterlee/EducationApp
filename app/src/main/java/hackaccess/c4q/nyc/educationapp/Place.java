package hackaccess.c4q.nyc.educationapp;

/**
 * Created by Willee on 8/1/15.
 */
public class Place {

    private String name;
    private String address;
    private String zipcode;
    private String pictureUrl;

    public Place() {

    }

    public Place(String address, String name, String pictureUrl, String zipcode) {
        this.address = address;
        this.name = name;
        this.pictureUrl = pictureUrl;
        this.zipcode = zipcode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}

package hackaccess.c4q.nyc.educationapp.firebase;

/**
 * Created by c4q-anthonyf on 8/1/15.
 */
public class UserInfo {

    private String name;
    private int zip;

    public UserInfo(String name, int zip) {
        this.name = name;
        this.zip = zip;
    }

    public UserInfo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }
}

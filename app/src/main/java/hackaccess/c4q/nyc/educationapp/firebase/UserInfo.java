package hackaccess.c4q.nyc.educationapp.firebase;

import java.util.HashMap;

/**
 * Created by c4q-anthonyf on 8/1/15.
 */
public class UserInfo {

    private String name;
    private String zip;
    private HashMap<String,String> userLikes;

    public UserInfo(String name, String zip) {
        this.name = name;
        this.zip = zip;
        userLikes = new HashMap<>();
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

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public HashMap<String, String> getUserLikes() {
        return userLikes;
    }

    public void setUserLikes(HashMap<String, String> userLikes) {
        this.userLikes = userLikes;
    }
}
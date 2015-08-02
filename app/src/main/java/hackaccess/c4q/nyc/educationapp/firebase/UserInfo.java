package hackaccess.c4q.nyc.educationapp.firebase;

import java.util.HashMap;

/**
 * Created by c4q-anthonyf on 8/1/15.
 */
public class UserInfo {

    private String firstName;
    private String lastName;
    private String zip;
    private HashMap<String,String> userLikes;


    public UserInfo(String firstName, String lastName, String zip) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.zip = zip;
        userLikes = new HashMap<String, String>();
    }

    public UserInfo(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
         return firstName ;
    }

    public String getLastName() {
        return lastName ;
    }


    public void setName(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
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
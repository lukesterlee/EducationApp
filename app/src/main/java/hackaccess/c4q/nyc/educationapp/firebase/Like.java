package hackaccess.c4q.nyc.educationapp.firebase;

import java.util.HashMap;

public class Like {

    HashMap<String,String> like;

    public Like(String programID, String zipcode) {
        like = new HashMap<>();
        like.put("programID", programID);
        like.put("zipcode", zipcode);
    }

    public HashMap<String, String> getHashMap() {
        return like;
    }
    public String getZipcode(){
        return like.get("zipcode");
    }

    public String getProgramID(){
        return like.get("programID");
    }

}
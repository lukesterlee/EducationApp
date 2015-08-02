package hackaccess.c4q.nyc.educationapp.firebase;

import java.util.HashMap;

/**
 * Created by c4q-anthonyf on 8/1/15.
 */
public class Like {

    HashMap<String,Long> like;

    public Like(long officeID, long userID) {
        like = new HashMap<>();
        like.put("officeid", officeID);
        like.put("userid", userID);
    }

    public HashMap<String, Long> getLike() {
        return like;
    }

}
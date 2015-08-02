package hackaccess.c4q.nyc.educationapp;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;

import java.util.ArrayList;
import java.util.HashMap;

import hackaccess.c4q.nyc.educationapp.firebase.Like;
import hackaccess.c4q.nyc.educationapp.firebase.Account;

/**
 * Created by c4q-anthonyf on 8/1/15.
 */
public class FbHelper {


    public static Firebase saveAccount(Account account, Firebase ref){

        Query accountExists = ref.orderByChild("email");

           ref.addChildEventListener(new ChildEventListener() {
               @Override
               public void onChildAdded(DataSnapshot dataSnapshot, String s) {

               }

               @Override
               public void onChildChanged(DataSnapshot dataSnapshot, String s) {

               }

               @Override
               public void onChildRemoved(DataSnapshot dataSnapshot) {

               }

               @Override
               public void onChildMoved(DataSnapshot dataSnapshot, String s) {

               }

               @Override
               public void onCancelled(FirebaseError firebaseError) {

               }
           });


            HashMap<String, String> accountHash = account.getAccountHash();
            Firebase accountRef = ref.child("users").push();
            accountRef.setValue(accountHash);

            return accountRef;

    }

    public void addLike(long officeId, long userId){

    }

    public ArrayList<Like> getOfficeLikes(long officeID){
        return null;
    }

    public ArrayList<Like> getUserLikes(Firebase account){
        return null;
    }

}

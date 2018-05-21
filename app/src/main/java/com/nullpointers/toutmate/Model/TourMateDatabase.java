package com.nullpointers.toutmate.Model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TourMateDatabase {

    private Context context;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference rootRef;
    private DatabaseReference userRef;
    private DatabaseReference userInfoRef;
    private DatabaseReference eventRef;
    private DatabaseReference expenseRef;
    private DatabaseReference momentRef;
    FirebaseUser user;

    public TourMateDatabase(Context context ){

        this.context = context;
        firebaseAuth = FirebaseAuth.getInstance();
        rootRef = FirebaseDatabase.getInstance().getReference();
        /*user = firebaseAuth.getCurrentUser();
        userRef = rootRef.child(user.getUid());
        userInfoRef = userRef.child("User");
        eventRef = userRef.child("Event");
        expenseRef = eventRef.child("Expense");
        momentRef=eventRef.child("Moment");*/

    }

}

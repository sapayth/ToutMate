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
    private DatabaseReference eventRef;
    private DatabaseReference expenseRef;
    private DatabaseReference momentRef;
    private FirebaseUser user;

    private String eventKey;
    private String expenseKey;
    private String momentKey;

    public TourMateDatabase(Context context, FirebaseUser user){

        this.context = context;
        this.user = user;
        firebaseAuth = FirebaseAuth.getInstance();
        rootRef = FirebaseDatabase.getInstance().getReference("Tour Mate");
        userRef = rootRef.child(user.getUid());
        eventRef = userRef.child("Event");
        eventKey = eventRef.push().getKey();
    }

    public void addEvent(Event event){
        eventRef.child(eventKey).setValue(event);
    }

    public void addExpense(String eventKey, String expenseKey, Expense expense){
        expenseRef = eventRef.child(eventKey).child("Expense");
        expenseRef.child(expenseKey).setValue(expense);
    }

    public void addMoment(String eventKey, String momentKey, Moment moment){
        momentRef = eventRef.child(eventKey).child("Moment");
        momentRef.child(momentKey).setValue(moment);
    }

    public String getNewEventKey(){
        return eventKey;
    }

    public String getNewExpenseKey(String eventKey){
        return  eventRef.child(eventKey).child("Expense").push().getKey();
    }

    public String getNewMomentKey(String eventKey){
        return  eventRef.child(eventKey).child("Moment").push().getKey();
    }
}

package com.nullpointers.toutmate.fragment;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nullpointers.toutmate.LoginActivity;
import com.nullpointers.toutmate.Model.DateConverter;
import com.nullpointers.toutmate.Model.Event;
import com.nullpointers.toutmate.Model.Expense;
import com.nullpointers.toutmate.Model.TourMateDatabase;
import com.nullpointers.toutmate.R;
import com.nullpointers.toutmate.adapter.ExpandableListViewAdapter;
import com.nullpointers.toutmate.adapter.ExpenseListAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventDetailsFragment extends Fragment implements DatePickerDialog.OnDateSetListener {
    private ExpandableListView eventExListView;
    private List<String> eventActionHeading = new ArrayList<>();
    private HashMap<String, List<String>> eventActionChild = new HashMap<>();

    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;

    private DatabaseReference rootRef;
    private DatabaseReference userRef;
    private DatabaseReference eventRef;
    private DatabaseReference expenseRef;

    private Event event;
    private TextView eventNameTextView;
    private TextView budgetStatusTextView;
    private TextView expensePercentTextView;
    private TextView totalBudgetPercentTextView;
    private TextView expenseDateET;
    private ProgressBar budgetStatusProgressBar;

    private List<Expense>expenseList = new ArrayList<>();
    private String expensePercent = "";
    private String totalBudgetPercent = "100%";
    private String expenseKey;
    private TourMateDatabase database;

    private String eventName;
    private String eventKey;
    private String startingLocation;
    private String destinationLocation;
    private long createdDate;
    private long departureDate;
    private double budget;

    private DateConverter converter = new DateConverter();

    Calendar calendar = Calendar.getInstance();
    int year = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH);
    int day = calendar.get(Calendar.DAY_OF_MONTH);
    long expenseDate;


    private LayoutInflater dialogInflater;

    public EventDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_event_details, container, false);

        eventExListView = view.findViewById(R.id.eventExListView);
        eventNameTextView = view.findViewById(R.id.eventNameTextView);
        budgetStatusTextView = view.findViewById(R.id.budgetStatusTextView);
        expensePercentTextView = view.findViewById(R.id.expensePercent);
        totalBudgetPercentTextView = view.findViewById(R.id.budgetStatusTextView);
        budgetStatusProgressBar = view.findViewById(R.id.budgetStatusProgressBar);
        firebaseAuth = FirebaseAuth.getInstance();
        user  = firebaseAuth.getCurrentUser();

        if (user!=null){
            database = new TourMateDatabase(getContext(),user);
        }else {
            getActivity().startActivity(new Intent(getContext(), LoginActivity.class));
        }
        rootRef = FirebaseDatabase.getInstance().getReference().child("Tour Mate");
        userRef = rootRef.child(user.getUid());
        eventRef = userRef.child("Event");


        dialogInflater = LayoutInflater.from(getContext());

        initializeData();
        ExpandableListViewAdapter expandableListViewAdapter = new ExpandableListViewAdapter(EventDetailsFragment.this.getActivity(), eventActionHeading, eventActionChild);
        eventExListView.setAdapter(expandableListViewAdapter);

        Bundle bundle = getArguments();
        if (bundle!=null){
           event  = (Event) bundle.getSerializable("Event");
           eventName = event.getEventName();
           eventKey = event.getKey();
           startingLocation = event.getStartingLocation();
           destinationLocation = event.getDestinationLocation();
           createdDate = event.getCreatedDate();
           departureDate = event.getDepartureDate();
           budget = event.getBudget();
           String totalBudget = String.valueOf(event.getBudget());
           String totalExpense = "100";
           String budgetStatus = String.format("Budget Status (%s/%s)",totalExpense,totalBudget);
           eventNameTextView.setText(eventName);
           budgetStatusTextView.setText(budgetStatus);
           expensePercentTextView.setText(expensePercent);
           totalBudgetPercentTextView.setText(totalBudget);
           expensePercentTextView.setText(expensePercent);
           totalBudgetPercentTextView.setText(totalBudgetPercent);

        }


       eventExListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
           @Override
           public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

               String childClick = eventActionChild.get(eventActionHeading.get(groupPosition)).get(childPosition);

               if (childClick.equals("Add New Expense")){
                   newExpenseDialog();
               }else if (childClick.equals("View All Expense")){
                   viewAllExpenseDialog();
               }else if (childClick.equals("Add More Budget")){
                   addBudgetDialog();
               }else if (childClick.equals("Take a Photo")){
                   takePhoto();
               }else if (childClick.equals("View Gallery")){
                   viewGallery();
               }else if (childClick.equals("View All Moments")){
                   viewMoments();
               }else if (childClick.equals("Edit Event")){
                   editEvent();
               }else if (childClick.equals("Delete Event")){
                   deleteEvent();
               }

               return false;
           }
       });
        return  view;
    }

    private void deleteEvent() {

    }

    private void editEvent() {

    }

    private void viewMoments() {

    }

    private void viewGallery() {

    }

    private void takePhoto() {

    }

    private void addBudgetDialog() {
        View view = getLayoutInflater().inflate(R.layout.add_budget_dialog,null);
        AlertDialog.Builder addBudgetDialog = new AlertDialog.Builder(getContext());
        addBudgetDialog.setView(view);
        addBudgetDialog.setCancelable(true);
        final EditText budgetAmount = view.findViewById(R.id.addBudgetAmount);
        Button saveBtn = view.findViewById(R.id.addBudgetSaveBtn);
        Button cancelBtn = view.findViewById(R.id.addBudgetCancelBtn);
        final AlertDialog dialog = addBudgetDialog.create();
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (budgetAmount.getText().toString().isEmpty()){
                    budgetAmount.setError("Budget Amount Required");
                    budgetAmount.requestFocus();
                    return;
                }
                double amount = Double.parseDouble(budgetAmount.getText().toString());
                budget = budget+amount;
                Event event = new Event(eventKey,eventName,startingLocation,destinationLocation,createdDate,departureDate,budget);
                Task<Void> task = database.updateEvent(eventKey,event);
                task.addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(getContext(), "Budget Added", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    }
                });
            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void viewAllExpenseDialog() {
        expenseRef = eventRef.child(eventKey).child("Expense");
        expenseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                expenseList.clear();
                for (DataSnapshot postData: dataSnapshot.getChildren()){
                    Expense expense = postData.getValue(Expense.class);
                    expenseList.add(expense);
                }
                AlertDialog.Builder expenseDialog = new AlertDialog.Builder(getContext());
                expenseDialog.setCancelable(false);
                View view = getLayoutInflater().inflate(R.layout.expense_list,null);
                ListView expenseListView = view.findViewById(R.id.expenseListView);
                TextView emptyExpense = view.findViewById(R.id.emptyExpense);
                ImageButton crossBtn = view.findViewById(R.id.crossButton);
                expenseDialog.setView(view);
                final AlertDialog dialog = expenseDialog.create();
                crossBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                if (expenseList.size()>0){
                    ExpenseListAdapter adapter = new ExpenseListAdapter(expenseList,getContext());
                    expenseListView.setAdapter(adapter);

                }else {
                    emptyExpense.setVisibility(View.VISIBLE);
                }
                dialog.show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void newExpenseDialog() {
        View view = dialogInflater.inflate(R.layout.add_new_expense,null);
        AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
        dialog.setView(view);
        dialog.setCancelable(false);
        final EditText expenseNameET = view.findViewById(R.id.expenseNameET);
        final EditText expenseAmountET = view.findViewById(R.id.expenseAmountET);
        final EditText expenseCommentET = view.findViewById(R.id.expenseCommentET);
        expenseDateET = view.findViewById(R.id.expenseDateTV);
        Button saveBtn = view.findViewById(R.id.saveBtn);
        Button cancelBtn = view.findViewById(R.id.cancelBtn);
        final AlertDialog alertDialog = dialog.create();
        expenseDateET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseDate();
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (expenseNameET.getText().toString().isEmpty()){
                    expenseNameET.setError("Expense Name Required");
                    expenseNameET.requestFocus();
                    return;
                }if (expenseAmountET.getText().toString().isEmpty()){
                    expenseAmountET.setError("Amount Required");
                    expenseAmountET.requestFocus();
                    return;
                }if (expenseDateET.getText().toString().isEmpty()){
                    expenseDateET.setError("Date Required");
                    expenseDateET.requestFocus();
                    return;
                }

                String expenseName = expenseNameET.getText().toString().trim();
                double expenseAmount = Double.parseDouble(expenseAmountET.getText().toString().trim());
                String comment = expenseCommentET.getText().toString().trim();
                expenseKey = database.getNewExpenseKey(eventKey);
                Expense expense = new Expense(expenseKey,expenseName,expenseAmount,comment,expenseDate);
                Task<Void> task = database.addExpense(eventKey,expenseKey,expense);
                task.addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(getContext(), "Expense Added", Toast.LENGTH_SHORT).show();
                            alertDialog.dismiss();
                        }else {
                            Toast.makeText(getContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        alertDialog.show();
    }

    private void chooseDate() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), this, year, month, day);
        datePickerDialog.show();
    }

    private void initializeData(){
        List<String> Expenditure = new ArrayList<>();
        List<String> Moments = new ArrayList<>();
        List<String> MoreOnEvent = new ArrayList<>();

        eventActionHeading = new ArrayList<>();
        eventActionHeading.add("Expenditure");
        eventActionHeading.add("Moments");
        eventActionHeading.add("More on Event");

        Expenditure.add("Add New Expense");
        Expenditure.add("View All Expense");
        Expenditure.add("Add More Budget");

        Moments.add("Take a Photo");
        Moments.add("View Gallery");
        Moments.add("View All Moments");

        MoreOnEvent.add("Edit Event");
        MoreOnEvent.add("Delete Event");

        eventActionChild.put(eventActionHeading.get(0), Expenditure);
        eventActionChild.put(eventActionHeading.get(1), Moments);
        eventActionChild.put(eventActionHeading.get(2), MoreOnEvent);
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        expenseDate = converter.getDateInUnix(dayOfMonth+"/"+(month+1)+"/"+year);
        expenseDateET.setText(converter.getDateInString(expenseDate));
    }
}
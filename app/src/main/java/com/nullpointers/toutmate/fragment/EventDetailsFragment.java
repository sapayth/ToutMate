package com.nullpointers.toutmate.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.nullpointers.toutmate.Model.Event;
import com.nullpointers.toutmate.R;
import com.nullpointers.toutmate.adapter.ExpandableListViewAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventDetailsFragment extends Fragment {
    private ExpandableListView eventExListView;
    private List<String> eventActionHeading = new ArrayList<>();
    private HashMap<String, List<String>> eventActionChild = new HashMap<>();
    private Event event;
    private TextView eventNameTextView;
    private TextView budgetStatusTextView;
    private TextView expensePercentTextView;
    private TextView totalBudgetPercentTextView;
    private ProgressBar budgetStatusProgressBar;

    private String eventName = "";
    private String expensePercent = "";
    private String totalBudgetPercent = "100%";

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

        initializeData();
        ExpandableListViewAdapter expandableListViewAdapter = new ExpandableListViewAdapter(EventDetailsFragment.this.getActivity(), eventActionHeading, eventActionChild);
        eventExListView.setAdapter(expandableListViewAdapter);

        Bundle bundle = getArguments();
        if (bundle!=null){
           event  = (Event) bundle.getSerializable("Event");
           eventName = event.getEventName();
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

    }

    private void viewAllExpenseDialog() {

    }

    private void newExpenseDialog() {

    }

    private void initializeData()
    {

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






}
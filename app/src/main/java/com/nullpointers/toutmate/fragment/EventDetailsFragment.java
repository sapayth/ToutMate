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
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventDetailsFragment extends Fragment {
    private ExpandableListView eventExListView;
    private List<String> events;
    private HashMap<String, List<String>> expense;
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
        ExpandableListViewAdapter expandableListViewAdapter = new ExpandableListViewAdapter(EventDetailsFragment.this.getActivity(), events, expense);
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


               if (groupPosition == 0 && childPosition==0)
               {
                   View mView= getLayoutInflater().inflate(R.layout.add_new_expense,null);
                   AlertDialog.Builder mBuilder= new AlertDialog.Builder(getContext());
                           //AlertDialog.Builder(getActivity()).setTitle("expenses").setView(mView).create();

                   mBuilder.setView(mView);
                   AlertDialog dialog =mBuilder.create();
                   dialog.show();
                   Toast.makeText(getContext(), "Dailog add new expanse", Toast.LENGTH_SHORT).show();


               }
               else if (groupPosition==0 && childPosition==1)
               {
                   View mView= getLayoutInflater().inflate(R.layout.view_expense,null);
                   AlertDialog.Builder mBuilder= new AlertDialog.Builder(getContext());
                   //AlertDialog.Builder(getActivity()).setTitle("expenses").setView(mView).create();

                   mBuilder.setView(mView);
                   AlertDialog dialog =mBuilder.create();
                   dialog.show();
                   Toast.makeText(getContext(), "Dailog add new expanse", Toast.LENGTH_SHORT).show();               }
               else if(groupPosition==0 && childPosition==2)
               {

               }



               return false;
           }
       });
        return  view;
    }
    private void initializeData()
    {
        events = new ArrayList<>();
        events.add("Expenditure");
        events.add("Moments");
        events.add("More on Event");

        List<String> Expenditure = new ArrayList<>();
        List<String> Moments = new ArrayList<>();
        List<String> MoreOnEvent = new ArrayList<>();

        Expenditure.add("Add New Expense");
        Expenditure.add("View All Expense");
        Expenditure.add("Add More Budget");

        Moments.add("Take a Photo");
        Moments.add("View Gallery");
        Moments.add("View All Moments");

        MoreOnEvent.add("Edit Event");
        MoreOnEvent.add("Delete Event");

        expense = new HashMap<>();
        expense.put(events.get(0), Expenditure);
        expense.put(events.get(1), Moments);
        expense.put(events.get(2), MoreOnEvent);

    }






}
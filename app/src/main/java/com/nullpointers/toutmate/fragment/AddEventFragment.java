package com.nullpointers.toutmate.fragment;


import android.app.DatePickerDialog;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.nullpointers.toutmate.Model.Event;
import com.nullpointers.toutmate.Model.Expense;
import com.nullpointers.toutmate.Model.Moment;
import com.nullpointers.toutmate.Model.TourMateDatabase;
import com.nullpointers.toutmate.R;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddEventFragment extends Fragment implements View.OnClickListener {

    private EditText eventNameEditText;
    private EditText startLocationEditText;
    private EditText destLocationEditText;
    private EditText budgetEditText;
    private TextView startDateTextView;
    private ImageButton startDateButton;
    private Button createEventButton;

    private long createdDate;
    private long departureDate;

    public AddEventFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_event, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        eventNameEditText = view.findViewById(R.id.eventNameEditText);
        startLocationEditText = view.findViewById(R.id.startLocationEditText);
        destLocationEditText = view.findViewById(R.id.destLocationEditText);
        budgetEditText = view.findViewById(R.id.budgetEditText);
        startDateTextView = view.findViewById(R.id.startDateTextView);
        startDateButton = view.findViewById(R.id.startDateButton);
        createEventButton = view.findViewById(R.id.createEventButton);

        startDateButton.setOnClickListener(this);
        startDateTextView.setOnClickListener(this);
        createEventButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.startDateButton:
            case R.id.startDateTextView:
                chooseStartDate();
                break;
            case R.id.createEventButton:
                createEvent();
                break;
        }
    }

    private void createEvent() {
        TourMateDatabase database = new TourMateDatabase(getContext(), FirebaseAuth.getInstance().getCurrentUser());
        String eventName = eventNameEditText.getText().toString().trim();
        String startLocation = startLocationEditText.getText().toString().trim();
        String destLocation = destLocationEditText.getText().toString().trim();
        double budget = Double.parseDouble(budgetEditText.getText().toString());
        String eventKey = database.getNewEventKey();
        Event event = new Event(eventKey,eventName,startLocation,destLocation,8764287,237648726,budget);
        database.addEvent(event);
    }

    private void chooseStartDate() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);


    }
}

package com.nullpointers.toutmate.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nullpointers.toutmate.Model.Event;
import com.nullpointers.toutmate.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventListFragment extends Fragment {
    private TextView eventHeadingTextView;
    private RecyclerView eventListRecyclerView;
    private FloatingActionButton addButton;
    private TextView emptyEventTextView;
    private List<Event> events = new ArrayList<>();



    public EventListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_event_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        eventHeadingTextView = view.findViewById(R.id.eventHeadingTextView);
        eventListRecyclerView = view.findViewById(R.id.eventListRecyclerView);
        addButton = view.findViewById(R.id.addEventButton);
        emptyEventTextView = view.findViewById(R.id.showEmptyEvent);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.mainLayout,new AddEventFragment());
                ft.commit();
            }
        });
    }
}

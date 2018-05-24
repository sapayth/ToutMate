package com.nullpointers.toutmate.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nullpointers.toutmate.Model.DateConverter;
import com.nullpointers.toutmate.Model.Event;
import com.nullpointers.toutmate.R;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.EventHolder> {
    private DateConverter converter;
    private Context context;
    private List<Event> eventList;
    private Date date;

    public EventListAdapter(Context context, List<Event> eventList){
        this.context = context;
        this.eventList = eventList;
        this.date = Calendar.getInstance().getTime();
    }

    @NonNull
    @Override
    public EventHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.event_list_item,parent,false);
        return new EventHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventHolder holder, int position) {
        Event event = eventList.get(position);
        String name = event.getEventName();
        long createDate = event.getCreatedDate();
        long startDate = event.getDepartureDate();
        long currentDate = date.getTime();

        int dayLeft = (int) ((startDate - currentDate)/86400);

        holder.eventNameTextView.setText(name);
//        holder.createdDateTextView.setText(converter.getDateInString(createDate));
//        holder.startDateTextView.setText(converter.getDateInString(startDate));
        holder.dayLeftTextView.setText(dayLeft+"");

    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public class EventHolder extends RecyclerView.ViewHolder{
        private TextView eventNameTextView;
        private TextView createdDateTextView;
        private TextView startDateTextView;
        private TextView dayLeftTextView;

        public EventHolder(View itemView) {
            super(itemView);
            eventNameTextView = itemView.findViewById(R.id.eventName);
            createdDateTextView = itemView.findViewById(R.id.createdDate);
            startDateTextView = itemView.findViewById(R.id.startDate);
            dayLeftTextView = itemView.findViewById(R.id.dayLeft);
        }
    }
}

package com.nullpointers.toutmate.Model;

public class Event {
    private String eventName;
    private String startingLocation;
    private String destinationLocation;
    private long createdDate;
    private long departureDate;
    private String budget;

    public Event() {
        //required for firebase
    }

    public Event(String eventName, String startingLocation, String destinationLocation,long createdDate, long departureDate, String budget) {
        this.eventName = eventName;
        this.startingLocation = startingLocation;
        this.destinationLocation = destinationLocation;
        this.createdDate = createdDate;
        this.departureDate = departureDate;
        this.budget = budget;
    }

    public String getEventName() {
        return eventName;
    }

    public String getStartingLocation() {
        return startingLocation;
    }

    public String getDestinationLocation() {
        return destinationLocation;
    }

    public long getCreatedDate() {
        return createdDate;
    }

    public long getDepartureDate() {
        return departureDate;
    }

    public String getBudget() {
        return budget;
    }
}

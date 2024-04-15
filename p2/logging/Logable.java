package p2.logging;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import p2.events.Event;

public abstract class Logable {

    protected ArrayList<Event> events = new ArrayList<Event>();

    /**
     * addToLog is a method that allows for an event to be added to the log.
     * @param event The event object to be added
     * @return NONE
     */
    public void addToLog(Event event) {
        events.add(event);
    }

    /**
     * logSize is a method that returns the number of events that exist within the log.
     * @param NONE
     * @return The number of events in the log.
     */
    public int logSize() {
        return events.size();
    }

    /**
     * contains is a method that checks if the log contains a list of events.
     * @param events Events that are being checked in the log
     * @return A boolean value of true if the specified events are found, false if not.
     */
    public boolean contains(ArrayList<String> evnts) {
        Set<String> currentEvents = new HashSet<>();
        for (Event e : events) {
            currentEvents.add(e.toString());
        }

        for (String evnt : evnts) {
            if (!currentEvents.contains(evnt)) {
                return false;
            }
        }
        return true;
    }

    /**
     * containsInSequence is a method that checks to see if the list of events exist in a particular sequence.
     * @param evnts The representation of the list of events in a sequence that is to be checked.
     * @return A boolean value of true if the events are found in the specific sequence, false if not.
     */
    public boolean containsInSequence(ArrayList<String> evnts) {
        ArrayList<String> currentEvents = getEvents();
        int startIdx = currentEvents.indexOf(evnts.get(0));
        if (startIdx == -1 || evnts.size() + startIdx > currentEvents.size()) {
            return false;
        }

        for (int i = 0; i < evnts.size(); i++) {
            if (!currentEvents.get(startIdx + i).equals(evnts.get(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * validate is a method that checks the log to ensure the proper timing of the events as well as its uniqueness.
     * @param NONE
     * @return A boolean value of true if the validations are correct, that is the events are unique and properly times, false if not.
     */
    public boolean validate() {
        Set<String> seenDescriptions = new HashSet<>();
        Set<String> objectTimePairs = new HashSet<>();

        for (Event event : events) {
            String description = event.toString();
            String objectTimeKey = event.getObject() + "@" + event.getTime();

            if (!seenDescriptions.add(description) || !objectTimePairs.add(objectTimeKey)) {
                System.out.println("Validation failed on event: " + description);
                return false;
            }
        }

        System.out.println("Validation passed: All events are unique and properly timed.");
        return true;
    }

    /**
     * getEvents is a method that retrieves and returns the event descriptions of events stored within the log.
     * @param NONE
     * @return An array of the events stored in the log alongside their descriptions.
     */
    public ArrayList<String> getEvents() {
        ArrayList<String> eventDescriptions = new ArrayList<>();
        for (Event e : events) {
            eventDescriptions.add(e.toString());
        }
        return eventDescriptions;
    }

    /**
     * getEvents is a method that retrieves and returns event descriptions stored in the log that occur at a specific time.
     * @param time The time at which events occur.
     * @return An array of the descriptions of events stored in the log that would have occured at the specified times.
     */
    public ArrayList<String> getEvents(int time) {
        ArrayList<String> eventDescriptions = new ArrayList<>();
        for (Event e : events) {
            if (e.getTime() == time) {
                eventDescriptions.add(e.toString());
            }
        }
        return eventDescriptions;
    }

    /**
     * getEvents is a method that retrieves and returns description of events about a specific object within the log.
     * @param object The object related to the event.A
     * @return An array containing the events related to a specific object stored in the log, alongside its descriptions.
     */ 
    public ArrayList<String> getEvents(String object) {
        ArrayList<String> eventDescriptions = new ArrayList<>();
        for (Event e : events) {
            if (e.getObject().equals(object)) {
                eventDescriptions.add(e.toString());
            }
        }
        return eventDescriptions;
    }

    /**
     * getObjects is a method that retrieves the different objects involved in an event that are stored within the log.
     * @param NONE
     * @return An array of objects related to the specified event.
     */
    public ArrayList<String> getObjects() {
        HashSet<String> uniqueObjects = new HashSet<>();
        for (Event e : events) {
            uniqueObjects.add(e.getObject());
        }
        return new ArrayList<>(uniqueObjects);
    }

    /**
     * distinctObjects is a method that returns the number of distinct objects involved in the events stored in the log.
     * @param NONE
     * @return The number of distinct/unique objects related to the events stored within the log.
     */
    public int distinctObjects() {
        return getObjects().size();
    }

    /**
     * toString is a method that displays the string representation of the Events log.
     * @param NONE
     * @return String representation of the EventsLog and its events.
     */
    @Override
    public String toString() {
        StringBuilder evnts = new StringBuilder("Events Log[");
        if (events.isEmpty()) {
            evnts.append("no events]");
        } else {
            for (Event e : events) {
                evnts.append("\n\t").append(e).append(events.indexOf(e) != events.size() - 1 ? "," : "");
            }
            evnts.append("\n]");
        }
        return evnts.toString();
    }
}

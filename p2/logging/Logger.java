package p2.logging;

import p2.events.*;

public class Logger extends Logable {

   
    public void log(String message) {
        // Implementation for logging string messages
        System.out.println("Log: " + message);
    }


    public void logEvent(Event event) {
        // Implementation for logging events, assuming Event has a meaningful toString method
        System.out.println("Event Logged: " + event.toString());
    }
}

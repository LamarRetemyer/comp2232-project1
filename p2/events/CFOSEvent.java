package p2.events;
import p2.enums.Action;

public class CFOSEvent extends Event {
    private Action action;

    /** 
     * CFOSEvent is a method that creates an event with the given parameters.
     * @param object The object represented in the event.
     * @param time The time that the event occurs.
     * @param action The specific action that occurs in the event.
     * @return NONE 
     */
    public CFOSEvent(String object, long time, Action action) {
        super(object, time);
        this.action = action;
    }

    /**
     * getAction is a method that retrieves the action related to the CFOSEvent
     * @param NONE
     * @return The action related to the event. 
     */
    public Action getAction() {
        return action;
    }
    
    /**
     * toString is a method that represents the CFOSEvent as a string.
     * @param NONE
     * @return A string representation of the event in detail.
     */
    @Override
    public String toString() {
        return super.toString() + ", Action=" + action;
    }
}

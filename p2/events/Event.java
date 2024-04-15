package p2.events;

abstract public class Event{
	
	private String object; 
	private long time; 

	/** Event is a method that constructs the event with the provided parameters.
	 * @param object The object related to the event
	 * @param time2 The time that the event occurs.
	 * @return NONE
	 */
	public Event(String object, long time2) {
		this.object = object;
		this.time = time2;
	}

	/** getObject is a method that retrieves and returns the object related to the event.
	 * @param NONE
	 * @return The object related to the event.
	 */
	public String getObject() {
		return object;
	}

	/** getTime is a method that retrieves and returns the time the event occurs.
	 * @param NONE
	 * @return The time object related to the event.
	 * 
	 */
	public long getTime() {
		return time;
	}

	/** equals is a method that determines whether the object is the same as this one.
	 * @param evnt The object used to compare whether there is equality.
	 * @return True if the object is equal to the event, false if not.
	 */
	@Override
	public boolean equals(Object evnt) {
		if (evnt instanceof Event)
			return object.equals(((Event) evnt).object) && time == (((Event) evnt).time);
		return false;
	}
	
	/** toString is a method that represents the event as a string.
	 * @param NONE
	 * @return The string representation of the event in detail.
	 */
	@Override
	public String toString() {
		return "Object=" + object + ", Time()=" + time;
	}
}

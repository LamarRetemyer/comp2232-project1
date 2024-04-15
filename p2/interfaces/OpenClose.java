package p2.interfaces;

import p2.events.*;

/**
 * OpenClose is an interface that outlines the operations necessary for opening and closing elements in the system.
 */
public interface OpenClose {

    /**
     * open is a method that opens the component and returns an event describing the outcome.
	 * @param: NONE
     * @return Event indicating the result of the open operation.
     */
    Event open();

    /**
     * close is a method that closes the component and returns an event describing the outcome.
	 * @param: NONE
     * @return Event indicating the result of the close operation.
     */
    Event close();

    /**
     * isOpen is a method that checks if the component is currently open.
	 * @param: NONE
     * @return true if the component is open, false otherwise.
     */
    boolean isOpen();
}

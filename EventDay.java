/**
 * Filename: EventDay.java
 *
 * Author: Aayushi Mehta (SCU ID: W1549753)
 *
 * Description: Implementation of EventDay class
 */

import java.awt.*;

/**
 * Implementation of EventDay
 */
public class EventDay {
    int eventDay;
    Color eventColor;
    String eventName;

    /**
     * Parameterized constructor of EventDay class
     * @param eventDay      date of the event
     * @param eventName     name of the event
     * @param eventColor    color to be displayed for a particular event
     */
    public EventDay(int eventDay, String eventName, Color eventColor) {
        this.eventDay = eventDay;
        this.eventColor = eventColor;
        this.eventName = eventName;
    }
}

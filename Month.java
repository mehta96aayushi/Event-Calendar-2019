/**
 * Filename: Month.java
 *
 * Author: Aayushi Mehta (SCU ID: W1549753)
 *
 * Description: Implementation of Month class
 */

import java.util.ArrayList;

/**
 * Implementation of Month
 */
public class Month {
    private String monthName;
    private int numberOfDays;

    //Create an array list of EventDay class to store all the events for a particular month
    ArrayList<EventDay> eventDaysArrayList = new ArrayList<>();

    /**
     * Parameterized constructor of Month class
     * @param month        month name
     * @param numberOfDays number of days of a particular month
     */
    public Month(String month, int numberOfDays) {
        this.monthName = month;
        this.numberOfDays = numberOfDays;
    }

    /**
     * Get method for month name
     * @return name of the month
     */
    public String getMonthName() {
        return this.monthName;
    }

    /**
     * Get method for total number of days in a particular month
     * @return number of days
     */
    public int getNumberOfDays() {
        return this.numberOfDays;
    }
}

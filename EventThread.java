/**
 * Filename: EventThread.java
 *
 * Author: Aayushi Mehta (SCU ID: W1549753)
 *
 * Description: Implementation of EventThread class
 */

import java.awt.*;

/**
 * Implementation of EventThread
 */
public class EventThread implements Runnable {

     /**
      * This method executes thread by adding the object of each month
      * in the array list of Month and also, by adding all the federal
      * holidays to the respective month's eventDaysArrayList
      */
     public void run() {
          String[] months = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
          int[] noOfDays = {31,28,31,30,31,30,31,31,30,31,30,31};
          for(int i=0;i<months.length;i++) {
               CalendarView.monthList.add(new Month(months[i],noOfDays[i]));
          }

          CalendarView.monthList.get(0).eventDaysArrayList.add(new EventDay(1,"New Year's Day",Color.black));
          CalendarView.monthList.get(0).eventDaysArrayList.add(new EventDay(21,"Birthday of Martin Luther King, Jr.",Color.black));
          CalendarView.monthList.get(1).eventDaysArrayList.add(new EventDay(18,"Washington's Birthday",Color.black));
          CalendarView.monthList.get(4).eventDaysArrayList.add(new EventDay(27,"Memorial Day",Color.black));
          CalendarView.monthList.get(6).eventDaysArrayList.add(new EventDay(4,"Independence Day",Color.black));
          CalendarView.monthList.get(8).eventDaysArrayList.add(new EventDay(2,"Labour Day",Color.black));
          CalendarView.monthList.get(9).eventDaysArrayList.add(new EventDay(14,"Columbus Day",Color.black));
          CalendarView.monthList.get(10).eventDaysArrayList.add(new EventDay(10,"Veterans Day",Color.black));
          CalendarView.monthList.get(10).eventDaysArrayList.add(new EventDay(28,"Thanksgiving Day",Color.black));
          CalendarView.monthList.get(11).eventDaysArrayList.add(new EventDay(25,"Christmas Day",Color.black));
     }
}

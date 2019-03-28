/**
 * Filename: EventPanel.java
 *
 * Author: Aayushi Mehta (SCU ID: W1549753)
 *
 * Description: Implementation of EventPanel class
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Implementation of EventPanel
 */
public class EventPanel {
    private JLabel eventHeader, eventName, eventDate, eventColor, eventHistory;
    private JTextField eventNameField, eventDateField;
    private JTextArea historyTextArea;
    private JButton addEvent, deleteEvent;
    private JComboBox colorList;
    private JPanel eventPanel = new JPanel();

    private String[] colors = {"White", "Blue", "Red", "Yellow", "Orange", "Magenta"};
    private Color[] colorObject = {Color.white, Color.blue, Color.red, Color.yellow, Color.orange, Color.magenta};

    //Regular expression for validating the input date
    private String DATE_PATTERN = "(0[1-9]|1[012])/(0[1-9]|[12][0-9]|3[01])/(2019)";

    /**
     * This method is used to create the event
     * panel which allows to add or delete an event
     * @return returns generated event panel
     */
    public JPanel getEventPanel() {
        eventPanel.setPreferredSize(new Dimension(340,180));
        eventPanel.setBorder(BorderFactory.createEmptyBorder(10,10,0,10));

        eventHeader=new JLabel("Customize Calendar", SwingConstants.CENTER);
        eventHeader.setFont(new Font("Times New Roman",Font.BOLD,18));
        eventHeader.setPreferredSize(new Dimension(340,30));
        eventPanel.add(eventHeader);

        eventName=new JLabel("Event Name:", SwingConstants.LEFT);
        eventName.setFont(new Font("Times New Roman",Font.PLAIN,12));
        eventName.setPreferredSize(new Dimension(100,20));
        eventPanel.add(eventName);

        eventDate=new JLabel("Event Date:", SwingConstants.LEFT);
        eventDate.setFont(new Font("Times New Roman",Font.PLAIN,12));
        eventDate.setPreferredSize(new Dimension(200,20));
        eventPanel.add(eventDate);

        eventNameField=new JTextField();
        eventNameField.setPreferredSize(new Dimension(100,35));
        eventPanel.add(eventNameField);

        eventDateField=new JTextField();
        eventDateField.setPreferredSize(new Dimension(100,35));
        eventPanel.add(eventDateField);

        addEvent=new JButton("Add Event");
        addEvent.setPreferredSize(new Dimension(90,35));
        addEvent.addActionListener(new ActionListener() {
            /**
             * This method adds an event to the calendar based on chosen color
             * and date. It also validates the date format and if the input date
             * is not valid, then the appropriate message is shown.
             * @param event     object of ActionEvent
             */
            public void actionPerformed(ActionEvent event) {
                int eventMonth=0;
                int eventDate=0;

                if (eventDateField.getText().matches(DATE_PATTERN)) {
                    historyTextArea.append(eventNameField.getText() + ", " + eventDateField.getText() + "\n");

                    try {
                        eventMonth = Integer.parseInt(eventDateField.getText(0, 2));
                        eventDate = Integer.parseInt(eventDateField.getText(3, 2));
                        Month currentMonth=CalendarView.monthList.get(eventMonth-1);
                        currentMonth.eventDaysArrayList.add(new EventDay(eventDate, eventNameField.getText(), colorObject[colorList.getSelectedIndex()]));
                        CalendarView.updateGridLayout(CalendarView.currentCellWidth, CalendarView.currentCellHeight);
                        eventNameField.setText("");
                        eventDateField.setText("");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(null,"The date and its format(MM/DD/YYYY) should be valid.");
                }
            }
        });
        eventPanel.add(addEvent);

        eventColor=new JLabel("Change Text Color:");
        eventColor.setFont(new Font("Times New Roman", Font.PLAIN,12));
        eventColor.setPreferredSize(new Dimension(300, 20));
        eventPanel.add(eventColor);

        colorList=new JComboBox<>(colors);
        colorList.setSelectedIndex(0);
        colorList.setFont(new Font("Times New Roman",Font.PLAIN,14));
        colorList.setPreferredSize(new Dimension(300,35));
        eventPanel.add(colorList);

        eventHistory=new JLabel("History:");
        eventHistory.setFont(new Font("Times New Roman", Font.PLAIN,12));
        eventHistory.setPreferredSize(new Dimension(300, 20));
        eventPanel.add(eventHistory);

        historyTextArea=new JTextArea();
        historyTextArea.setPreferredSize(new Dimension(300,300));
        historyTextArea.setFont(new Font("Comic Sans", Font.PLAIN,12));
        eventPanel.add(historyTextArea);

        deleteEvent=new JButton("Delete Event");
        deleteEvent.setPreferredSize(new Dimension(105,35));
        deleteEvent.addActionListener(new ActionListener() {
            /**
             * This method deletes one or more events from the calendar
             * based on the selected events in the event history
             * @param event     object of ActionEvent
             */
            public void actionPerformed(ActionEvent event) {
                int eventMonth=0;
                int eventDate=0;
                String[] selectedEvents=historyTextArea.getSelectedText().split("\n");
                try {
                    for(int i=0;i<selectedEvents.length;i++) {
                        String eventInfo = selectedEvents[i];
                        String[] nameDate = eventInfo.split(", ");

                        eventMonth = Integer.parseInt(nameDate[1].substring(0, 2));
                        eventDate = Integer.parseInt(nameDate[1].substring(3, 5));

                        Month currentMonth = CalendarView.monthList.get(eventMonth - 1);
                        for (int j=0;j<currentMonth.eventDaysArrayList.size();j++) {
                            if (currentMonth.eventDaysArrayList.get(j).eventDay == eventDate
                                    && currentMonth.eventDaysArrayList.get(j).eventName.equals(nameDate[0])) {
                                currentMonth.eventDaysArrayList.remove(j);
                            }
                        }
                    }
                    CalendarView.updateGridLayout(CalendarView.currentCellWidth, CalendarView.currentCellHeight);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

                String selectedText = historyTextArea.getSelectedText()+"\n";
                String oldText = historyTextArea.getText();
                String newText = oldText.replace(selectedText,"");
                historyTextArea.setText(newText);
            }
        });
        eventPanel.add(deleteEvent);

        return eventPanel;
    }
}

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;

public class DaysCreater extends JPanel {
    private JLabel label,customDayLabel;
    private JTextArea customDayTextArea;
    private JPanel  borderPanel,customDayBorderPanel;
    JPanel gridPanel = null;
    JTextPane textArea;

    private String[] customDays = {"Dec - 30", "Dec - 31", "Jan - 1", "Jan - 2", "Jan - 3", "Jan - 4"};
    private String[] dayName = {"Sunday", "Monday", "Wednesday", "Thursday", "Friday", "Saturday"};

    /**
     * This method is used to create grid panel
     * according to width and height of the cell
     * @param width     width of the cell
     * @param height    height of the cell
     * @return returns grid panel
     */
    public JPanel getGridPanel(int width, int height) {
        gridPanel= new JPanel(new GridLayout(0,7,5,5));
        gridPanel.removeAll();
        createCustomDays(0,2, width, height);
        createDays(width, height);
        createCustomDays(2,6, width, height);
        return gridPanel;
    }

    int totalDays;

    /**
     * This method is used to create the labels and text areas for the days
     * of the current year. It checks whether a new month starts or not. And
     * if yes, then it displays it with a different color.
     * @param width     width of the cell based on the view mode
     * @param height    height of the cell based on the view mode
     */
    public void createDays(int width, int height) {
        for(int i=0;i<12;i++) {
            totalDays = 2;
            for(int k=0;k<i;k++) {
                totalDays+=CalendarView.monthList.get(k).getNumberOfDays();
            }
            Month currentMonth=CalendarView.monthList.get(i);

            for (int j=1;j<=currentMonth.getNumberOfDays();j++) {
                borderPanel = new JPanel(new BorderLayout());
                if(CalendarView.calendarViewMode==2) {
                    label = new JLabel(CalendarView.days[(totalDays + (j - 1)) % 7] + " : " + CalendarView.monthList.get(i).getMonthName() + " - " + j, SwingConstants.CENTER);
                }else {
                    label = new JLabel(CalendarView.monthList.get(i).getMonthName() + " - " + j, SwingConstants.CENTER);
                }
                if(j==1) {
                    label.setForeground(new Color(255,102,0));
                }

                label.setBackground(Color.white);
                label.setOpaque(true);
                label.setFont(new Font("Times New Roman",1,14));
                label.setPreferredSize(new Dimension(width, 20));

                borderPanel.add(label, BorderLayout.NORTH);

                textArea = new JTextPane();
                SimpleAttributeSet attributeSet = new SimpleAttributeSet();
                EventDay currentDay = null;
                try {
                    for (int k = 0; k < currentMonth.eventDaysArrayList.size(); k++) {
                        currentDay = currentMonth.eventDaysArrayList.get(k);
                        if (currentDay.eventDay == j) {
                            StyleConstants.setForeground(attributeSet, currentDay.eventColor);
                            Document doc = textArea.getStyledDocument();
                            doc.insertString(doc.getLength(),currentDay.eventName+"\n", attributeSet);
                        }
                    }
                } catch(Exception e) {
                    System.out.println(e);
                }
                textArea.setOpaque(true);
                textArea.setBackground(new Color(77,166,255));
                textArea.setMaximumSize(new Dimension(width, height));
                textArea.setMinimumSize(new Dimension(width, height));
                textArea.setPreferredSize(new Dimension(width, height));
                textArea.setEditable(false);

                borderPanel.add(textArea, BorderLayout.CENTER);

                gridPanel.add(borderPanel);
            }
        }
    }

    /**
     * This method is used to create days which are included in the calendar,
     * but they are not in the current year(2019), e.g. December 31, 2018.
     * @param startIndex    index of the customDays array from which custom days should be printed
     * @param endIndex      index of the customDays array up to which custom days should be printed
     * @param width         width of the cell based on the view mode
     * @param height        height of the cell based on the view mode
     */
    public void createCustomDays(int startIndex, int endIndex, int width, int height) {
        for(int i=startIndex;i<endIndex;i++){
            customDayBorderPanel = new JPanel(new BorderLayout());
            if(CalendarView.calendarViewMode==2) {
                customDayLabel = new JLabel(dayName[i] + " : " + customDays[i], SwingConstants.CENTER);
            } else{
                customDayLabel = new JLabel(customDays[i], SwingConstants.CENTER);
            }

            customDayLabel.setBackground(Color.white);
            customDayLabel.setForeground(Color.gray);
            customDayLabel.setOpaque(true);
            customDayLabel.setFont(new Font("Times New Roman",1,14));
            customDayLabel.setPreferredSize(new Dimension(width, 20));

            customDayBorderPanel.add(customDayLabel, BorderLayout.NORTH);

            customDayTextArea = new JTextArea();
            customDayTextArea.setBackground(new Color(77,166,255));
            customDayTextArea.setOpaque(true);
            customDayTextArea.setPreferredSize(new Dimension(width, height));
            customDayTextArea.setEditable(false);

            customDayBorderPanel.add(customDayTextArea, BorderLayout.CENTER);

            gridPanel.add(customDayBorderPanel);
        }
    }
}

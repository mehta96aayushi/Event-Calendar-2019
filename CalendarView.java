/**
 *Filename: CalendarView.java
 *
 * Author: Aayushi Mehta (SCU ID: W1549753)
 *
 * Description: Implementation of CalendarView class
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Implementation of CalendarView
 */
public class CalendarView extends JFrame {
    private JLabel scuImage, currentYear, dayLabel;
    private ImageIcon scuLogo;
    private JRadioButton monthRadioButton, weekRadioButton, dayRadioButton;
    private static JScrollPane scrollPane;
    private JPanel topPanel = new JPanel();
    private JPanel northPanel = new JPanel(new BorderLayout());
    private JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    static JPanel gridPanel = new JPanel(new BorderLayout());

    static int currentCellWidth, currentCellHeight;

    //represent current mode of the calendar (0 - month, 1 - week, 2 - day)
    static int calendarViewMode = 0;

    static String[] days = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};

    static ArrayList<Month> monthList = new ArrayList<>();         //Create an array list of Month class
    static DaysCreater daysCalender = new DaysCreater();

    /**
     * Default constructor of CalendarView class
     */
    public CalendarView() throws ParseException {
        Thread t=new Thread(new EventThread());
        t.start();

        createTopPanel();
        createHeaderPanel();

        gridPanel.add(daysCalender.getGridPanel(90,100));
        currentCellWidth = 90;
        currentCellHeight = 100;
        createScrollBar();
        createEventPanel();

        gridPanel.setBackground(new Color(191,191,191));
        setTitle("Calender");
        setSize(500,250);
        setResizable(false);
        setPreferredSize(new Dimension(1370,720));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        pack();
    }

    /**
     * This method is used to display SCU logo and the current year
     * on the top most part of the output window of calendar.
     */
    public void createTopPanel() {
        try{
            scuLogo = new ImageIcon(getClass().getResource("SCU-Seal_OutlinedRed15.png"));
            scuImage = new JLabel(scuLogo);
            topPanel.add(scuImage);
        } catch (Exception fe){
            System.out.println("SCU image logo not found.");
        }

        currentYear = new JLabel("2019");
        currentYear.setForeground(Color.white);
        currentYear.setFont(new Font("Times New Roman",1,16));
        topPanel.add(currentYear);

        createRadioButtons();

        topPanel.setBackground(Color.black);
        northPanel.add(topPanel, BorderLayout.NORTH);
        northPanel.setBackground(Color.black);
        add(northPanel,BorderLayout.NORTH);
    }

    /**
     * This method is used to create radio buttons, for monthly, weekly and
     * daily view of the calendar, on the top most part of the output window.
     */
    public void createRadioButtons(){
        monthRadioButton = new JRadioButton("Month");
        monthRadioButton.setPreferredSize(new Dimension(100,25));
        monthRadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if(calendarViewMode==2) {
                    northPanel.add(headerPanel);
                    northPanel.validate();
                    northPanel.repaint();
                }
                calendarViewMode=0;
                updateGridLayout(90,100 );
            }
        });
        topPanel.add(monthRadioButton);

        weekRadioButton = new JRadioButton("Week");
        weekRadioButton.setPreferredSize(new Dimension(100,25));
        weekRadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if(calendarViewMode==2) {
                    northPanel.add(headerPanel);
                    northPanel.validate();
                    northPanel.repaint();
                }
                calendarViewMode=1;
                updateGridLayout(90,540 );
            }
        });
        topPanel.add(weekRadioButton);

        dayRadioButton = new JRadioButton("Day");
        dayRadioButton.setPreferredSize(new Dimension(100,25));
        dayRadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                calendarViewMode=2;
                updateGridLayout(995,550);
                northPanel.remove(headerPanel);
                northPanel.validate();
                northPanel.repaint();
            }
        });
        topPanel.add(dayRadioButton);

        ButtonGroup radioGroup =new ButtonGroup();
        radioGroup.add(monthRadioButton);
        radioGroup.add(weekRadioButton);
        radioGroup.add(dayRadioButton);
    }

    /**
     * This method is used to create the labels of
     * the days of the week in the calendar.
     */
    public void createHeaderPanel() {
        for(int i=0; i<7; i++){
            dayLabel = new JLabel(days[i], SwingConstants.CENTER);
            dayLabel.setOpaque(true);
            dayLabel.setBackground(Color.black);
            dayLabel.setForeground(Color.white);
            dayLabel.setFont(new Font("Times New Roman",1,14));
            dayLabel.setPreferredSize(new Dimension(140,40));

            headerPanel.add(dayLabel);
        }
        headerPanel.setBackground(Color.black);
        northPanel.add(headerPanel, BorderLayout.SOUTH);
        add(northPanel,BorderLayout.NORTH);
    }

    /**
     * This method is used to generate grid layout
     * according to the view mode of the calendar
     * @param width     new width of the cell based on the view mode
     * @param height    new height of the cell based on the view mode
     */
    public static void updateGridLayout(int width,int height) {
        gridPanel.removeAll();
        gridPanel.add(daysCalender.getGridPanel(width,height));
        gridPanel.validate();
        gridPanel.repaint();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                scrollPane.getVerticalScrollBar().setValue(0);
                scrollPane.getHorizontalScrollBar().setValue(0);
            }
        });
    }

    /**
     * This method is used to create the scrollbar in the calendar.
     */
    public void createScrollBar() {
        scrollPane = new JScrollPane(gridPanel,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        add(scrollPane);
    }

    /**
     * This method is used to insert the event panel
     */
    public void createEventPanel() {
        EventPanel e=new EventPanel();
        add(e.getEventPanel(),BorderLayout.EAST);
    }

    /**
     * Main method of the program
     * @param args command line arguments(ignored)
     */
    public static void main(String[] args) throws ParseException {
        new CalendarView();
    }
}

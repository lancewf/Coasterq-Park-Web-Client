package com.coasterq.client.view.enterwaittime;

import com.coasterq.client.data.ParkClock;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;

public class TimeOfDayControl extends HorizontalPanel
{
   // --------------------------------------------------------------------------
   // Private Data
   // --------------------------------------------------------------------------
   
   private ListBox hourSelection;
   private ListBox minuteSelection;
   private ListBox amPmSelection;
   private ParkClock parkClock;
   
   // --------------------------------------------------------------------------
   // Constructor
   // --------------------------------------------------------------------------
   
   public TimeOfDayControl(ParkClock parkClock)
   {
      this.parkClock = parkClock;
      hourSelection = createHourSelection(parkClock);
      minuteSelection = createMinuteSelection(parkClock);
      amPmSelection = createAmPmSelection(parkClock);
      add(hourSelection);
      add(new Label(":"));
      add(minuteSelection);
      add(amPmSelection);
      
      setToCurrentTime();
   }
   // --------------------------------------------------------------------------
   // Public Members
   // --------------------------------------------------------------------------
   
   public void setToCurrentTime()
   {
      if(parkClock.get12Hour() == 12)
      {
         hourSelection.setSelectedIndex(0);
      }
      else
      {
         hourSelection.setSelectedIndex(parkClock.get12Hour());
      }
      
      if(parkClock.isPm())
      {
         amPmSelection.setSelectedIndex(1);
      }
      else
      {
         amPmSelection.setSelectedIndex(0);
      }
      
      int timeMin = parkClock.getMinutes();
      timeMin = Math.round((timeMin/5))*5;
      
      minuteSelection.setSelectedIndex(timeMin/5);
   }
   
   public int getMin()
   {
      String minString = minuteSelection.getValue(
         minuteSelection.getSelectedIndex());
      
      int min = Integer.parseInt(minString);
      
      return min;
   }
   
   public int getHour()
   {
      String hourString = hourSelection.getValue(
         hourSelection.getSelectedIndex());
      
      String amPm = amPmSelection.getValue(
         amPmSelection.getSelectedIndex());
      
      int hour = Integer.parseInt(hourString);
      
      if(amPm.equalsIgnoreCase("PM") && hour < 12)
      {
         hour += 12;
      }
      else if(amPm.equalsIgnoreCase("AM") && hour == 12)
      {
         hour = 0;
      }
      
      return hour;
   }
   
   public int getDayOfMonth()
   {
      return parkClock.getDayOfMonth();
   }
   
   public int getMonth()
   {
      return parkClock.getMonth();
   }
   
   public int getYear()
   {
      return parkClock.getYear();
   }
   
   // --------------------------------------------------------------------------
   // Private Members
   // --------------------------------------------------------------------------
   
   private ListBox createHourSelection(ParkClock parkClock)
   {  
      ListBox hourCombobox = new ListBox();
      
      hourCombobox.addItem("12", "12");
      hourCombobox.addItem("1", "1");
      hourCombobox.addItem("2", "2");
      hourCombobox.addItem("3", "3");
      hourCombobox.addItem("4", "4");
      hourCombobox.addItem("5", "5");
      hourCombobox.addItem("6", "6");
      hourCombobox.addItem("7", "7");
      hourCombobox.addItem("8", "8");
      hourCombobox.addItem("9", "9");
      hourCombobox.addItem("10", "10");
      hourCombobox.addItem("11", "11");

      return hourCombobox;
   }

   private ListBox createAmPmSelection(ParkClock parkClock)
   {
      ListBox amPmCombobox = new ListBox();
      
      amPmCombobox.addItem("AM", "AM");
      amPmCombobox.addItem("PM", "PM");
      
      return amPmCombobox;
   }

   private ListBox createMinuteSelection(ParkClock parkClock)
   {
      ListBox minutesCombobox = new ListBox();
      
      minutesCombobox.addItem("00", "0");
      minutesCombobox.addItem("05", "5");
      minutesCombobox.addItem("10", "10");
      minutesCombobox.addItem("15", "15");
      minutesCombobox.addItem("20", "20");
      minutesCombobox.addItem("25", "25");
      minutesCombobox.addItem("30", "30");
      minutesCombobox.addItem("35", "35");
      minutesCombobox.addItem("40", "40");
      minutesCombobox.addItem("45", "45");
      minutesCombobox.addItem("50", "50");
      minutesCombobox.addItem("55", "55");
      
      return minutesCombobox;
   }
}

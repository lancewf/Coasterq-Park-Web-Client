package com.coasterq.client.view.enterwaittime;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;

public class WaitTimeControl extends HorizontalPanel
{
   // --------------------------------------------------------------------------
   // Private Data
   // --------------------------------------------------------------------------
   
   private ListBox hourSelection;
   private ListBox minuteSelection;
   
   // --------------------------------------------------------------------------
   // Constructor
   // --------------------------------------------------------------------------
   
   public WaitTimeControl()
   {
      hourSelection = createHourSelection();
      minuteSelection = createMinuteSelection();
      add(hourSelection);
      add(new Label(" hour"));
      add(minuteSelection);
      add(new Label(" min"));
   }
   
   // --------------------------------------------------------------------------
   // Public Members
   // --------------------------------------------------------------------------
   
   public int getWaitTimeMin()
   {
      String hourString = hourSelection.getValue(
         hourSelection.getSelectedIndex());
      
      int hour = Integer.parseInt(hourString);
      
      String minString = minuteSelection.getValue(
         minuteSelection.getSelectedIndex());
      
      int min = Integer.parseInt(minString);
      
      int totalMins = hour*60 + min;
      
      return totalMins;
   }
   
   // --------------------------------------------------------------------------
   // Private Members
   // --------------------------------------------------------------------------
   
   private ListBox createHourSelection()
   {
      ListBox hourCombobox = new ListBox();
      
      hourCombobox.addItem("0", "0");
      hourCombobox.addItem("1", "1");
      hourCombobox.addItem("2", "2");
      hourCombobox.addItem("3", "3");
      hourCombobox.addItem("4", "4");
      
      return hourCombobox;
   }

   private ListBox createMinuteSelection()
   {
      ListBox minutesCombobox = new ListBox();
      
      minutesCombobox.addItem("0", "0");
      minutesCombobox.addItem("5", "5");
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
      
      minutesCombobox.setSelectedIndex(3);
      
      return minutesCombobox;
   }
}

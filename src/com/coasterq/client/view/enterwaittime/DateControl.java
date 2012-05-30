package com.coasterq.client.view.enterwaittime;

import java.util.Date;

import com.coasterq.client.data.ParkClock;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class DateControl extends VerticalPanel
{
   // --------------------------------------------------------------------------
   // Private Data
   // --------------------------------------------------------------------------

   private ParkClock parkClock;
   private Label datelabel;
   private static DateTimeFormat dateformat = 
      DateTimeFormat.getFormat("MMMM d, y");
   
   // --------------------------------------------------------------------------
   // Constructor
   // --------------------------------------------------------------------------

   public DateControl(ParkClock parkClock)
   {
      this.parkClock = parkClock;
      
      addStyleName("enterWaitTimeSubPanel");
      
      datelabel = new Label("");
      
      add(datelabel);
      
      setCurrentDate();
   }

   // --------------------------------------------------------------------------
   // Public Members
   // --------------------------------------------------------------------------

   public void setCurrentDate()
   {
      Date date = parkClock.getDateObject();
      
      String text = dateformat.format(date);
      
      datelabel.setText(text);
   }
}

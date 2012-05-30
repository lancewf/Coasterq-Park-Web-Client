package com.coasterq.client.view;

import com.coasterq.client.data.ParkLocationManager;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class LocationPanel extends VerticalPanel
{
   // --------------------------------------------------------------------------
   // Private Class Data
   // --------------------------------------------------------------------------
   
   private static final String OUTSIDE_THE_PARK = "We cannot detect that you are inside the park";
   private static final String INSIDE_PARK = "You are currently inside the park";
   private static final String NOT_SUPPORTED = "Location not supported";
   
   // --------------------------------------------------------------------------
   // Private Instance Data
   // --------------------------------------------------------------------------
   
   private Label label = new Label();
   private ParkLocationManager parkLocationManager;
   
   // --------------------------------------------------------------------------
   // Constructor
   // --------------------------------------------------------------------------
   
   public LocationPanel(ParkLocationManager parkLocationManager)
   {
      this.parkLocationManager = parkLocationManager;
      
      add(label);
      
      initialize();
   }
   
   // --------------------------------------------------------------------------
   // Private Members
   // --------------------------------------------------------------------------
   
   private void initialize()
   {
      if (parkLocationManager.isSupported())
      {
         displayLocation();
         
         Timer t = new Timer()
         {
            public void run()
            {
               displayLocation();
            }
         };

         // Schedule the timer to run every 5 seconds.
         t.scheduleRepeating(5000);
      }
      else
      {
         label.setText(NOT_SUPPORTED);
      }
   }
   
   private void displayLocation()
   {
      String text = "";
      
      if(parkLocationManager.isInisidePark())
      {
         text += INSIDE_PARK;
      }
      else
      {
         text += OUTSIDE_THE_PARK;
      }
      
      label.setText(text);
   }
}

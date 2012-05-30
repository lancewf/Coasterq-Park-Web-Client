package com.coasterq.client.view;

import com.coasterq.client.data.Ride;
import com.finfrock.client.DataChangeListener;
import com.google.gwt.user.client.ui.Label;

public class CurrentWaitTimeControl extends Label
{
   // --------------------------------------------------------------------------
   // Private Data
   // --------------------------------------------------------------------------

   private Ride ride;
   
   // --------------------------------------------------------------------------
   // Constructor
   // --------------------------------------------------------------------------

   public CurrentWaitTimeControl(Ride ride)
   {
      this.ride = ride;
      
      setText(getFormatTime());
      
      ride.addDataChangeListener(new DataChangeListener()
      {
         @Override
         public void onDataChange()
         {
            setText(getFormatTime());
         }
      });
   }
   
   // --------------------------------------------------------------------------
   // Private Members
   // --------------------------------------------------------------------------

   private String getFormatTime()
   {
      String text = "";
      
      int timeMin = ride.getCurrentWaitTime();
      
      if (timeMin != -1)
      {
         timeMin = Math.round((timeMin / 5)) * 5;

         int hours = timeMin / 60;
         int mins = timeMin % 60;

         if (hours > 0)
         {
            text += hours + " hr ";
         }

         text += mins + " min";
      }
      else
      {
         text = "Closed";
      }
      
      return text;
   }
}

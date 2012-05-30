package com.coasterq.client.connection;

import com.coasterq.client.data.ParkClock;
import com.finfrock.client.Loadable;
import com.finfrock.client.Returnable;
import com.google.gwt.user.client.Timer;

public class ParkClockUpdateAgent
{
   // --------------------------------------------------------------------------
   // Private Data
   // --------------------------------------------------------------------------

   private ParkClockRetriever parkClockRetriever;
   private ParkClock parkClock;
   
   // --------------------------------------------------------------------------
   // Constructor
   // --------------------------------------------------------------------------

   public ParkClockUpdateAgent(ServiceLocations serviceLocations, 
                               ParkClock parkClock)
   {
      this.parkClock = parkClock;
      parkClockRetriever = new ParkClockRetriever(serviceLocations);
   }
   
   // --------------------------------------------------------------------------
   // Public Members
   // --------------------------------------------------------------------------
   
   public void start()
   {
      Timer t = new Timer() {
         public void run() {
            parkClockRetriever.startLoad(new Returnable<Loadable>(){
               @Override
               public void returned(Loadable loadable)
               {
                  ParkClockRetriever parkClockRetriever = (ParkClockRetriever)loadable;
                  
                  ParkClockData parkClockData = parkClockRetriever.getObject();
                  
                  parkClock.setParkClockData(parkClockData);
               }
            });
         }
       };

       // Schedule the timer to run every 5 minutes.
       t.scheduleRepeating(300000);
   }
}

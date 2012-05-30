package com.coasterq.client.data;

import java.util.List;

import com.coasterq.client.connection.RidesRetriever;
import com.coasterq.client.connection.ServiceLocations;
import com.finfrock.client.Loadable;
import com.finfrock.client.Returnable;
import com.google.gwt.user.client.Timer;

/**
 * This class is used to update the rides in the ride manager from the
 * server. This class will request data from the server ever 5 minutes. 
 * 
 * @author lancewf
 *
 */
public class RideUpdateAgent
{
   // --------------------------------------------------------------------------
   // Private Data
   // --------------------------------------------------------------------------

   private RidesRetriever rideDataInitialRetriever;
   private RideManager rideManager;

   // --------------------------------------------------------------------------
   // Constructor
   // --------------------------------------------------------------------------

   public RideUpdateAgent(ServiceLocations serviceLocations, RideManager rideManager)
   {
      this.rideManager = rideManager;
      rideDataInitialRetriever = new RidesRetriever(serviceLocations);
   }
   
   // --------------------------------------------------------------------------
   // Public Members
   // --------------------------------------------------------------------------

   public void start()
   {
      Timer t = new Timer() 
      {
        public void run() 
        {
           rideDataInitialRetriever.startLoad(new Returnable<Loadable>()
           {
              @Override
              public void returned(Loadable loadable)
              {
                 RidesRetriever rideDataInitialRetriever = (RidesRetriever)loadable;
                 
                 List<Ride> rides = rideDataInitialRetriever.getObject();
                 
                 rideManager.setRides(rides);
              }
           });
        }
      };

      // Schedule the timer to run every 5 minutes.
      t.scheduleRepeating(300000);
   }
}
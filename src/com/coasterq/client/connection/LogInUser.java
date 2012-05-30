package com.coasterq.client.connection;

import java.util.List;

import com.coasterq.client.data.ItineraryRide;
import com.coasterq.client.data.ItineraryRideManagerable;
import com.coasterq.client.data.RideManagerable;
import com.finfrock.client.Loadable;
import com.finfrock.client.Returnable;
import com.finfrock.client.SenderAndReceiver;
import com.google.gwt.user.client.Window;

/**
 * Log in the facebook user to the Coasterq server.
 * @author lancewf
 *
 */
public class LogInUser extends SenderAndReceiver
{
   // --------------------------------------------------------------------------
   // Private Data
   // --------------------------------------------------------------------------

   private ServiceLocations serviceLocations;
   private RideManagerable rideManager;
   private ItineraryRideManagerable itineraryRideManager;
   private String facebookUserId;
   
   // --------------------------------------------------------------------------
   // Constructor
   // --------------------------------------------------------------------------

   public LogInUser(ServiceLocations serviceLocations, 
                    RideManagerable rideManager, 
                    ItineraryRideManagerable itineraryRideManager)
   {
      super(serviceLocations.getUserLogInAddress());
      
      this.serviceLocations = serviceLocations;
      this.rideManager = rideManager;
      this.itineraryRideManager = itineraryRideManager;
   }
   
   // --------------------------------------------------------------------------
   // Public Members
   // --------------------------------------------------------------------------

   public void sendToServer(String facebookUserId)
   {
      this.facebookUserId = facebookUserId;
      
      send();
   }
   
   // --------------------------------------------------------------------------
   // Overridden Members
   // --------------------------------------------------------------------------

   @Override
   protected void reponse(String reponse)
   {
      if(reponse.equals("returning user"))
      {
         updateItinerary();
      }
   }
   
   @Override
   protected String getData()
   {
      return "facebookuserid=" + facebookUserId;
   }
   
   // --------------------------------------------------------------------------
   // Private Members
   // --------------------------------------------------------------------------

   private void updateItinerary()
   {
      ItineraryRidesRetriever retriever = new ItineraryRidesRetriever(
         serviceLocations);

      retriever.startLoad(new Returnable<Loadable>()
      {
         @Override
         public void returned(Loadable object)
         {
            ItineraryRidesRetriever retriever = (ItineraryRidesRetriever) object;

            ItineraryRidesBuilder itineraryRideManagerBuilder = 
               new ItineraryRidesBuilder(rideManager, retriever);

            itineraryRideManagerBuilder.build();

            List<ItineraryRide> itineraryRides = itineraryRideManagerBuilder
               .getItineraryRides();

            itineraryRideManager.setItineraryRides(itineraryRides);
         }
      });
   }

}

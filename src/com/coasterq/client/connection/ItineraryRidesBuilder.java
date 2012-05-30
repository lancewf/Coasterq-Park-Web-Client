package com.coasterq.client.connection;

import java.util.ArrayList;
import java.util.List;

import com.coasterq.client.data.ItineraryRide;
import com.coasterq.client.data.Ride;
import com.coasterq.client.data.RideManagerable;
import com.finfrock.client.RetrieverAndroid15;

public class ItineraryRidesBuilder
{
   // --------------------------------------------------------------------------
   // Private Data
   // --------------------------------------------------------------------------

   private List<ItineraryRide> itineraryRides;
   private RetrieverAndroid15<List<ItineraryRideData>> itineraryRidesRetriever;
   private RideManagerable rideManager;
   
   // --------------------------------------------------------------------------
   // Constructor Members
   // --------------------------------------------------------------------------

   public ItineraryRidesBuilder(RideManagerable rideManager,
                     RetrieverAndroid15<List<ItineraryRideData>> itineraryRidesRetriever)
   {
      this.rideManager = rideManager;
      this.itineraryRidesRetriever = itineraryRidesRetriever;
   }
   
   // --------------------------------------------------------------------------
   // Public Members
   // --------------------------------------------------------------------------

   public void build()
   {
      List<ItineraryRideData> itineraryRideDatas = 
         itineraryRidesRetriever.getObject();

      itineraryRides = new ArrayList<ItineraryRide>();

      for (ItineraryRideData itineraryRideData : itineraryRideDatas)
      {
         ItineraryRide itineraryRide = new ItineraryRide();

         itineraryRide.setPriority(itineraryRideData.getPriority());

         Ride ride = rideManager.getRide(itineraryRideData.getRideId());

         itineraryRide.setRide(ride);

         itineraryRides.add(itineraryRide);
      }
   }
   
   public List<ItineraryRide> getItineraryRides()
   {
      return itineraryRides;
   }
}

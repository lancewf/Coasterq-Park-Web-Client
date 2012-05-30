package com.coasterq.client.connection;

import java.util.ArrayList;
import java.util.List;

import com.coasterq.client.data.Ride;
import com.finfrock.client.RetrieverAndroid15;
import com.google.gwt.core.client.JsArray;

public class RidesRetriever extends RetrieverAndroid15<List<Ride>>
{
   // --------------------------------------------------------------------------
   // Constructor
   // --------------------------------------------------------------------------

   public RidesRetriever(ServiceLocations serviceLocations)
   {
      super(serviceLocations.getInitialRideDataAddress());
   }
   
   // --------------------------------------------------------------------------
   // Retriever Members
   // --------------------------------------------------------------------------
   
   @Override
   protected List<Ride> parseText(String rawText)
   {
      JsArray<RideData> rideDatas = asArrayOfRideData(rawText);
      List<Ride> rides = new ArrayList<Ride>();
      
      if(rideDatas != null)
      {
         for(int index = 0; index < rideDatas.length(); index++)
         {
            RideData rideData = rideDatas.get(index);
            Ride ride = new Ride();
            
            ride.setId(rideData.getId());
            ride.setCurrentWaitTime(rideData.getCurrentWaitTime());
            ride.setLocation(rideData.getLocation());
            ride.setName(rideData.getName());
            ride.setHeightRequirement(rideData.getHeightRequirement());
            ride.setHasFastPass(rideData.getHasFastPass());
            
            rides.add(ride);
         }
      }
      
      return rides;
   }
   
   // --------------------------------------------------------------------------
   // Private Members
   // --------------------------------------------------------------------------
   
   private final native JsArray<RideData> asArrayOfRideData(String json) 
   /*-{
      return eval(json);
   }-*/;
}
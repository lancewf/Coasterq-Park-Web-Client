package com.coasterq.client.data;

import java.util.List;

import com.finfrock.client.DataChangeListener;

public interface ItineraryRideManagerable
{

   /**
    * Add a new itinerary ride.
    * 
    * @param rideId - the ride id of the new itinerary ride. 
    */
   public abstract void addNewItineraryRide(Ride ride);

   public abstract List<ItineraryRide> getItineraryRides();

   public abstract void removeItineraryRide(ItineraryRide itineraryRide);

   public abstract void addDataChangeListener(DataChangeListener dataChangeListener);

   public abstract void removeDataChangeListener(DataChangeListener dataChangeListener);

   public abstract boolean doesContainRide(Ride ride);

   public abstract void increaseItineraryRidePriority(ItineraryRide itineraryRide);

   public abstract void decreaseItineraryRidePriority(ItineraryRide itineraryRide);

   public abstract void setItineraryRides(List<ItineraryRide> itineraryRides);

}
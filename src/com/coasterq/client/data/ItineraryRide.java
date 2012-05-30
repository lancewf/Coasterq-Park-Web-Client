package com.coasterq.client.data;

public class ItineraryRide implements Comparable<ItineraryRide>
{
   // --------------------------------------------------------------------------
   // Private Data
   // --------------------------------------------------------------------------
  
   private int priority;
   
   private Ride ride;
   
   // --------------------------------------------------------------------------
   // Public Members
   // --------------------------------------------------------------------------
   
   public int getPriority()
   {
      return priority;
   }
   
   public void setPriority(int priority)
   {
      this.priority = priority;
   }

   public Ride getRide()
   {
      return ride;
   }
   
   public void setRide(Ride ride)
   {
      this.ride = ride;
   }

   // --------------------------------------------------------------------------
   // Comparable Members
   // --------------------------------------------------------------------------
   
   @Override
   public int compareTo(ItineraryRide itineraryRide)
   {
      return getPriority() - itineraryRide.getPriority();
   }

}

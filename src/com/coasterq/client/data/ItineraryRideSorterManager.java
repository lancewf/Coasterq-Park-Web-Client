package com.coasterq.client.data;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.coasterq.client.data.ItineraryRide;
import com.finfrock.client.DataChangeListener;

public class ItineraryRideSorterManager implements ItineraryRideManagerable
{
   // --------------------------------------------------------------------------
   // Private Data
   // --------------------------------------------------------------------------

   private ItineraryRideManagerable itineraryRideManager;
   
   public static final int RIDE_NAME = 1;

   public static final int LAND_NAME = 2;
   
   public static final int CURRENT_TIME = 3;
   
   private int sortBy = CURRENT_TIME;
   
   private boolean isReversed = false;
   
   // --------------------------------------------------------------------------
   // Constructor
   // --------------------------------------------------------------------------
   
   public ItineraryRideSorterManager(
                                  ItineraryRideManagerable itineraryRideManager)
   {
      this.itineraryRideManager = itineraryRideManager;
   }
   
   // --------------------------------------------------------------------------
   // Public Members
   // --------------------------------------------------------------------------

   public void setSortBy(int sortByType)
   {
      if(sortBy == sortByType)
      {
         isReversed = !isReversed;
      }
      else
      {
         isReversed = false;
      }
      
      sortBy = sortByType;
   }
   
   public List<ItineraryRide> getItineraryRides()
   {
      List<ItineraryRide> itineraryRides = 
         itineraryRideManager.getItineraryRides();
      
      return sort(itineraryRides);
   }
   
   @Override
   public void addDataChangeListener(DataChangeListener dataChangeListener)
   {
      itineraryRideManager.addDataChangeListener(dataChangeListener);
   }

   @Override
   public void addNewItineraryRide(Ride ride)
   {
      itineraryRideManager.addNewItineraryRide(ride);
   }

   @Override
   public void decreaseItineraryRidePriority(ItineraryRide itineraryRide)
   {
      itineraryRideManager.decreaseItineraryRidePriority(itineraryRide);  
   }

   @Override
   public boolean doesContainRide(Ride ride)
   {
      return itineraryRideManager.doesContainRide(ride);
   }

   @Override
   public void increaseItineraryRidePriority(ItineraryRide itineraryRide)
   {
      itineraryRideManager.increaseItineraryRidePriority(itineraryRide);
   }

   @Override
   public void removeDataChangeListener(DataChangeListener dataChangeListener)
   {
      itineraryRideManager.removeDataChangeListener(dataChangeListener);  
   }

   @Override
   public void removeItineraryRide(ItineraryRide itineraryRide)
   {
      itineraryRideManager.removeItineraryRide(itineraryRide);
   }
   
   @Override
   public void setItineraryRides(List<ItineraryRide> itineraryRides)
   {
      itineraryRideManager.setItineraryRides(itineraryRides);
   }

   // --------------------------------------------------------------------------
   // Private Members
   // --------------------------------------------------------------------------
   
   private List<ItineraryRide> sort(List<ItineraryRide> itineraryRides)
   {
      if(sortBy == RIDE_NAME)
      {
         itineraryRides = sortByRideName(itineraryRides);
      }
      else if(sortBy == LAND_NAME)
      {
         itineraryRides = sortByLandName(itineraryRides);
      }
      else if(sortBy == CURRENT_TIME)
      {
         itineraryRides = sortByCurrentTime(itineraryRides);
      }
      
      if(isReversed)
      {
         Collections.reverse(itineraryRides);
      }
      
      return itineraryRides;
   }
   
   private List<ItineraryRide> 
      sortByCurrentTime(List<ItineraryRide> itineraryRides)
   {
      Collections.sort(itineraryRides, new Comparator<ItineraryRide>()
         {
            @Override
            public int compare(ItineraryRide itineraryRide1, 
                               ItineraryRide itineraryRide2)
            {
               return itineraryRide1.getRide().getCurrentWaitTime()
                     - itineraryRide2.getRide().getCurrentWaitTime();
            }

         });
      
      return itineraryRides;
   }

   private List<ItineraryRide> sortByLandName(List<ItineraryRide> itineraryRides)
   {
      Collections.sort(itineraryRides, new Comparator<ItineraryRide>()
         {
            @Override
            public int compare(ItineraryRide itineraryRide1, 
                               ItineraryRide itineraryRide2)
            {
               return itineraryRide1.getRide().getLocation().toUpperCase().compareTo(
                  itineraryRide2.getRide().getLocation().toUpperCase());
            }

         });
      return itineraryRides;
   }

   private List<ItineraryRide> sortByRideName(List<ItineraryRide> itineraryRides)
   {
      Collections.sort(itineraryRides, new Comparator<ItineraryRide>()
         {
            @Override
            public int compare(ItineraryRide itineraryRide1, 
                               ItineraryRide itineraryRide2)
            {
               return itineraryRide1.getRide().getName().toUpperCase().compareTo(
                  itineraryRide2.getRide().getName().toUpperCase());
            }
         });
      return itineraryRides;
   }
}

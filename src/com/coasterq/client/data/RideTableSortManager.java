package com.coasterq.client.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.finfrock.client.DataChangeListener;

public class RideTableSortManager implements RideManagerable
{
   public static final int RIDE_NAME = RideSorter.RIDE_NAME;

   public static final int LAND_NAME = RideSorter.LAND_NAME;

   public static final int CURRENT_TIME = RideSorter.CURRENT_TIME;

   public static final int FASSPASS = RideSorter.FASSPASS;

   public static final int ADD_TO_MY_Q = 5;

   // --------------------------------------------------------------------------
   // Private Data
   // --------------------------------------------------------------------------

   private List<Ride> rides = new ArrayList<Ride>();

   private int sortBy = RIDE_NAME;

   private boolean isReversed = false;

   private ItineraryRideManagerable itineraryRideManager;

   private List<DataChangeListener> dataChangeListeners = 
      new ArrayList<DataChangeListener>();
 
   private RideSorter rideSorter = new RideSorter();

   // --------------------------------------------------------------------------
   // Constructor
   // --------------------------------------------------------------------------

   public RideTableSortManager(ItineraryRideManagerable itineraryRideManager, 
                               RideManagerable rideManagerable)
   {
      this.itineraryRideManager = itineraryRideManager;
      
      rideManagerable.addDataChangeListener(new DataChangeListener()
      {
         @Override
         public void onDataChange()
         {
            if(sortBy == RideSorter.CURRENT_TIME)
            {
               dataChanged();
            }
         }
      });
   }

   // --------------------------------------------------------------------------
   // Public Members
   // --------------------------------------------------------------------------

   public void setSortBy(int sortBy)
   {
      if (this.sortBy == sortBy)
      {
         isReversed = !isReversed;
      }
      else
      {
         isReversed = false;
      }

      this.sortBy = sortBy;

      dataChanged();
   }

   public void setRides(List<Ride> rides)
   {
      this.rides = rides;

      dataChanged();
   }

   @Override
   public Ride getRide(int rideId)
   {
      for (Ride ride : rides)
      {
         if (ride.getId() == rideId)
         {
            return ride;
         }
      }
      return null;
   }

   @Override
   public List<Ride> getRides()
   {      
      if (sortBy == RIDE_NAME)
      {
         rideSorter.setRides(rides);
         
         rides =  rideSorter.sort(RideSorter.RIDE_NAME);
      }
      else if (sortBy == LAND_NAME)
      {
         rideSorter.setRides(rides);
         
         rides =  rideSorter.sort(RideSorter.LAND_NAME);
      }
      else if (sortBy == CURRENT_TIME)
      {
         rideSorter.setRides(rides);
         
         rides =  rideSorter.sort(RideSorter.CURRENT_TIME);
      }
      else if (sortBy == FASSPASS)
      {
         rideSorter.setRides(rides);
         
         rides =  rideSorter.sort(RideSorter.FASSPASS);
      }
      else if (sortBy == ADD_TO_MY_Q)
      {
         rides = sortByInItinerary();
      }

      if (isReversed)
      {
         Collections.reverse(rides);
      }

      return rides;
   }

   @Override
   public void removeDataChangeListener(DataChangeListener dataChangeListener)
   {
      dataChangeListeners.remove(dataChangeListener);
   }

   @Override
   public void addDataChangeListener(DataChangeListener dataChangeListener)
   {
      dataChangeListeners.add(dataChangeListener);
   }

   // --------------------------------------------------------------------------
   // Private Members
   // --------------------------------------------------------------------------

   private List<Ride> sortByInItinerary()
   {
      Collections.sort(rides, new Comparator<Ride>()
      {
         @Override
         public int compare(Ride ride1, Ride ride2)
         {
            boolean doesContainRide1 = itineraryRideManager
               .doesContainRide(ride1);

            boolean doesContainRide2 = itineraryRideManager
               .doesContainRide(ride2);

            if (doesContainRide1 && doesContainRide2)
            {
               return 0;
            }
            else if (!doesContainRide1 && doesContainRide2)
            {
               return -1;
            }
            else if (doesContainRide1 && !doesContainRide2)
            {
               return 1;
            }
            else
            // (!doesContainRide1 && !doesContainRide2)
            {
               return 0;
            }
         }

      });

      return rides;
   }

   private void dataChanged()
   {
      for (DataChangeListener dataChangeListener : dataChangeListeners)
      {
         dataChangeListener.onDataChange();
      }
   }
}

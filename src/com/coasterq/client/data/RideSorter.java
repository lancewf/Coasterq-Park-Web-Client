package com.coasterq.client.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RideSorter
{

   public static final int RIDE_NAME = 1;

   public static final int LAND_NAME = 2;
   
   public static final int CURRENT_TIME = 3;
   
   public static final int FASSPASS = 4;
   
   // --------------------------------------------------------------------------
   // Private Data
   // --------------------------------------------------------------------------
   
   private List<Ride> rides = new ArrayList<Ride>();
   
   // --------------------------------------------------------------------------
   // Constructor
   // --------------------------------------------------------------------------
   
   public RideSorter()
   {
      //
      // Do nothing
      //
   }
   
   // --------------------------------------------------------------------------
   // Public Members
   // --------------------------------------------------------------------------

   public void setRides(List<Ride> rides)
   {
      this.rides = rides;
   }
   
   public List<Ride> sort(int sortBy)
   {
      if(sortBy == RIDE_NAME)
      {
         return sortByRideName();
      }
      else if(sortBy == LAND_NAME)
      {
         return sortByLandName();
      }
      else if(sortBy == CURRENT_TIME)
      {
         return sortByCurrentTime();
      }
      else if(sortBy == FASSPASS)
      {
         return sortByFasspass();
      }
      
      return rides;
   }

   // --------------------------------------------------------------------------
   // Private Members
   // --------------------------------------------------------------------------

   private List<Ride> sortByFasspass()
   {  
      return rides;
   }

   private List<Ride> sortByCurrentTime()
   {
      Collections.sort(rides, new Comparator<Ride>()
      {
         @Override
         public int compare(Ride ride1, Ride ride2)
         {
            return ride1.getCurrentWaitTime() - ride2.getCurrentWaitTime();
         }

      });
      
      return rides;
   }

   private List<Ride> sortByLandName()
   {
      Collections.sort(rides, new Comparator<Ride>()
         {
            @Override
            public int compare(Ride ride1, Ride ride2)
            {
               return ride1.getLocation().compareToIgnoreCase(ride2
                  .getLocation());
            }

         });
      return rides;
   }

   private List<Ride> sortByRideName()
   {
      Collections.sort(rides, new Comparator<Ride>()
         {
            @Override
            public int compare(Ride ride1, Ride ride2)
            {
               return ride1.getName().compareToIgnoreCase(ride2.getName());
            }

         });
      return rides;
   }
   
}

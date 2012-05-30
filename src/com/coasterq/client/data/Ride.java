package com.coasterq.client.data;

import java.util.ArrayList;
import java.util.List;

import com.finfrock.client.DataChangeListener;
import com.google.gwt.user.client.Random;

public class Ride
{
   // --------------------------------------------------------------------------
   // Private Data
   // --------------------------------------------------------------------------
  
   private String name;

   private int id;

   private String landName;

   private int heightRequirement;

   private boolean hasFastPass;

   private int currentTime;
   
   private FuzzySet fuzzySet = new FuzzySet(25200, 75600, 50400);
   
   private List<DataChangeListener> dataChangeListeners = 
      new ArrayList<DataChangeListener>();

   private List<Point> futurePoints;
   
   private List<Point> pastPoints;
   
   // --------------------------------------------------------------------------
   // Constructor
   // --------------------------------------------------------------------------

   public Ride()
   {
     pastPoints = new ArrayList<Point>();
      
      for(int x = 25200; x < 43200 ; x += 1000)
      {
         double y = adjustForTimeOfDay(Random.nextInt(40) + 100, 
            Random.nextInt(20) + 10, x);

         pastPoints.add(new Point(x, (int)y));
      }
      
      futurePoints = new ArrayList<Point>();
      
      for(int x = 43200; x < 82800 ; x += 1000)
      {
         double y = adjustForTimeOfDay(Random.nextInt(40) + 100, 
            Random.nextInt(20) + 10, x);

         futurePoints.add(new Point(x, (int)y));
      }
   }
   
   // --------------------------------------------------------------------------
   // Public Members
   // --------------------------------------------------------------------------

   public String getLocation()
   {
      return landName;
   }

   public void setLocation(String landName)
   {
      this.landName = landName;
   }

   public void setHeightRequirement(int heightRequirement)
   {
      this.heightRequirement = heightRequirement;
   }

   public void setHasFastPass(boolean hasFastPass)
   {
      this.hasFastPass = hasFastPass;
   }
   
   public int getHeightRequirement()
   {
      return heightRequirement;
   }

   public boolean getHasFastPass()
   {
      return hasFastPass;
   }

   public int getId()
   {
      return id;
   }
   
   public void setId(int id)
   {
      this.id = id;
   }

   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public int getCurrentWaitTime()
   {
      return currentTime;
   }

   public void setCurrentWaitTime(int currentTime)
   {
      if(this.currentTime != currentTime)
      {
         this.currentTime = currentTime;
         
         dataChanged();
      }
   }

   public boolean isRideOpen()
   {
      if (currentTime != -1)
      {
         return true;
      }
      else
      {
         return false;
      }
   }

   public String toString()
   {
      return name;
   }

   public boolean equals(Ride ride)
   {
      return this.getId() == ride.getId();
   }
   
   public List<Point> getPastPoints()
   {
      return pastPoints;
   }
   
   public List<Point> getFuturePoints()
   {
      return futurePoints;
   }
   
   /* (non-Javadoc)
    * @see com.coasterq.client.data.RideManagerable#addDataChangeListener(com.coasterq.client.common.DataChangeListener)
    */
   public void addDataChangeListener(DataChangeListener dataChangeListener)
   {
      dataChangeListeners.add(dataChangeListener);
   }
   
   /* (non-Javadoc)
    * @see com.coasterq.client.data.RideManagerable#removeDataChangeListener(com.coasterq.client.common.DataChangeListener)
    */
   public void removeDataChangeListener(DataChangeListener dataChangeListener)
   {
      dataChangeListeners.remove(dataChangeListener);
   }
   
   // --------------------------------------------------------------------------
   // Private Members
   // --------------------------------------------------------------------------
   
   private void dataChanged()
   {
      for(DataChangeListener dataChangeListener : dataChangeListeners)
      {
         dataChangeListener.onDataChange();
      }
   }
   
   private int adjustForTimeOfDay(int highWaitTimeOfDay, int lowWaitTimeOfDay, 
                                  int secondsInToday)
   {
      double memberValue = 
         fuzzySet.getMemberValue(secondsInToday);
      
      int waitTimeRange = highWaitTimeOfDay - lowWaitTimeOfDay;
      
      int additionToLow = (int)(memberValue * waitTimeRange);
      
      int adjustedWaitTime = lowWaitTimeOfDay + additionToLow;
       
      return adjustedWaitTime;
   }
}

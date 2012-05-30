package com.coasterq.client.data;

import com.coasterq.client.connection.ParkOpenRangeData;

public class ParkOpenRange
{
   private ParkOpenRangeData parkOpenRangeData;
   
   public ParkOpenRange(ParkOpenRangeData parkOpenRangeData)
   {
      this.parkOpenRangeData = parkOpenRangeData;
   }
   
   public boolean isWithinRange(int hour, int min, int day, 
                                int month, int year)
   {
      if(parkOpenRangeData.getDayOfMonth() == day && 
            parkOpenRangeData.getMonth() == month && 
            parkOpenRangeData.getYear() == year)
      {
         
         if(hour > parkOpenRangeData.getOpenHour() && 
               hour < parkOpenRangeData.getCloseHour())
         {
            return true;
         }
         // if hour is the same as the open hour and the open and close hour are
         // not the same. 
         else if(hour == parkOpenRangeData.getOpenHour() && 
               min >= parkOpenRangeData.getOpenMin() && 
               parkOpenRangeData.getOpenHour() != parkOpenRangeData.getCloseHour())
         {
            return true;
         }
         // if the hour is the same as the close hour and the open close hour
         // are not the same. 
         else if(hour == parkOpenRangeData.getCloseHour() && 
               min <= parkOpenRangeData.getCloseMin() && 
               parkOpenRangeData.getOpenHour() != parkOpenRangeData.getCloseHour())
         {
            return true;
         }
         // Open, close, and hour are the same. 
         else if(parkOpenRangeData.getOpenHour() == parkOpenRangeData.getCloseHour() && 
               hour == parkOpenRangeData.getCloseHour() &&
            min >= parkOpenRangeData.getOpenMin() && 
            min <= parkOpenRangeData.getCloseMin())
         {
            return true;
         }
      }

      return false;
   }
}

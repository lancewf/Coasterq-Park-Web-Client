package com.coasterq.client.data;

import java.util.Date;
import java.util.List;

import com.coasterq.client.connection.ParkClockData;

public class ParkClock
{
   // -------------------------------------------------------------------------
   // Private data
   // -------------------------------------------------------------------------
   
   private Date dateTime;
   private List<ParkOpenRange> parkOpenRanges;
   
   // -------------------------------------------------------------------------
   // Constructor
   // -------------------------------------------------------------------------
   
   public ParkClock(ParkClockData parkClockData, 
                    List<ParkOpenRange> parkOpenRanges)
   {
      this.parkOpenRanges = parkOpenRanges;
      
      setParkClockData(parkClockData);
   }
   
   // -------------------------------------------------------------------------
   // Public Members
   // -------------------------------------------------------------------------
   
   @SuppressWarnings("deprecation")
   public void setParkClockData(ParkClockData parkClockData)
   {
      dateTime = new Date(parkClockData.getYear() - 1900, 
         parkClockData.getMonth() - 1, 
         parkClockData.getDayOfMonth(), parkClockData.get24Hour(), 
         parkClockData.getMinutes());
   }
   
   public Date getDateObject()
   {
      return dateTime;
   }
   
   public boolean isOpen()
   {
      for(ParkOpenRange parkOpenRange : parkOpenRanges)
      {
         if(parkOpenRange.isWithinRange(get24Hour(), getMinutes(), 
            getDayOfMonth(), getMonth(), getYear()))
         {
            return true;
         }
      }
      
      return false;
   }
   
   public int get12Hour()
   {
      if(get24Hour() > 12)
      {
         return get24Hour() - 12;
      }
      else if(get24Hour() == 0)
      {
         return 12;
      }
      else
      {
         return get24Hour();
      }
   }
   
   @SuppressWarnings("deprecation")
   public int get24Hour()
   {
      return dateTime.getHours();
   }
   
   @SuppressWarnings("deprecation")
   public int getMinutes()
   {
      return dateTime.getMinutes();
   }
   
   @SuppressWarnings("deprecation")
   public int getDayOfMonth()
   {
      return dateTime.getDate();
   }
   
   @SuppressWarnings("deprecation")
   public int getYear()
   {
      return dateTime.getYear() + 1900;
   }
   
   @SuppressWarnings("deprecation")
   public int getMonth()
   {
      return dateTime.getMonth() + 1;
   }
   
   public boolean isPm()
   {
      if(get24Hour() >= 12)
      {
         return true;
      }
      else
      {
         return false;   
      }
   }
}

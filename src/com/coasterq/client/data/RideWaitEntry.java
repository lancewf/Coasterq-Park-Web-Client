package com.coasterq.client.data;

public class RideWaitEntry
{
   // -------------------------------------------------------------------------
   // Private data
   // -------------------------------------------------------------------------

   private Ride ride;

   private int waitTimeMin;

   private int timeHour;

   private int timeMin;

   private int dayOfMonth;

   private int month;

   private int year;
   
   private boolean isInsidePark;
   
   private Double latitude;
   
   private Double longitude;

   // -------------------------------------------------------------------------
   // Public Members
   // -------------------------------------------------------------------------

   public Ride getRide()
   {
      return ride;
   }

   public void setRide(Ride ride)
   {
      this.ride = ride;
   }

   public int getWaitTimeMin()
   {
      return waitTimeMin;
   }

   public void setWaitTimeMin(int waitTimeMin)
   {
      this.waitTimeMin = waitTimeMin;
   }

   public int getTimeHour()
   {
      return timeHour;
   }

   public void setTimeHour(int timeHour)
   {
      this.timeHour = timeHour;
   }

   public int getTimeMin()
   {
      return timeMin;
   }

   public void setTimeMin(int timeMin)
   {
      this.timeMin = timeMin;
   }

   public int getDayOfMonth()
   {
      return dayOfMonth;
   }

   public void setDayOfMonth(int dayOfMonth)
   {
      this.dayOfMonth = dayOfMonth;
   }

   public int getMonth()
   {
      return month;
   }

   public void setMonth(int month)
   {
      this.month = month;
   }

   public int getYear()
   {
      return year;
   }

   public void setYear(int year)
   {
      this.year = year;
   }

   public boolean isInsidePark()
   {
      return isInsidePark;
   }
   
   public void setIsInsidePark(boolean isInsidePark)
   {
      this.isInsidePark = isInsidePark;
   }

   public double getLatitude()
   {
      return latitude;
   }

   public void setLatitude(Double latitude)
   {
      this.latitude = latitude;
   }
   
   public Double getLongitude()
   {
      return longitude;
   }

   public void setLongitude(Double longitude)
   {
      this.longitude = longitude;
   }
}

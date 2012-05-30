package com.coasterq.client.data;

import java.util.Date;

import com.finfrock.client.FacebookUser;

public class FriendStatus
{
   // --------------------------------------------------------------------------
   // Private Data
   // --------------------------------------------------------------------------

   private FacebookUser facebookUser;
   private Ride ride;
   private Date dateTime;
   private int waitTime;

   // --------------------------------------------------------------------------
   // Constructor
   // --------------------------------------------------------------------------

   public FriendStatus() 
   {
      //
      // Do nothing
      //
   }
   
   // --------------------------------------------------------------------------
   // Public Members
   // --------------------------------------------------------------------------

   public FacebookUser getFacebookUser()
   {
      return facebookUser;
   }

   public void setFacebookUser(FacebookUser facebookUser)
   {
      this.facebookUser = facebookUser;
   }

   public Ride getRide()
   {
      return ride;
   }

   public void setRide(Ride ride)
   {
      this.ride = ride;
   }

   public Date getDateTime()
   {
      return dateTime;
   }
   
   public void setDateTime(Date dateTime)
   {
      this.dateTime = dateTime;
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

   public void setWaitTime(int waitTime)
   {
      this.waitTime = waitTime;
   }
   
   public int getWaitTime()
   {
      return waitTime;
   }
}

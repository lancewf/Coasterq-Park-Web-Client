package com.coasterq.client.connection;

import com.google.gwt.core.client.GWT;

public class TestServiceLocations implements ServiceLocations
{
   @Override
   public String getItineraryLoadAddress()
   {
      return GWT.getHostPageBaseURL() + "itineraryServices.php";
   }

   @Override
   public String getInitialRideDataAddress()
   {
      return GWT.getHostPageBaseURL() + "services.php";
   }

   @Override
   public String getItineraryRideUpdaterAddress()
   {
      return GWT.getHostPageBaseURL() + "update.php";
   }

   @Override
   public String getWaitTimeSenderAddress()
   {
      return GWT.getHostPageBaseURL() + "update.php";
   }

   @Override
   public String getParkInformationAddress()
   {
      return GWT.getHostPageBaseURL() + "parkname.php";
   }

   @Override
   public String getParkClockAddress()
   {
      return GWT.getHostPageBaseURL() + "clock.php";
   }

   @Override
   public String getParkOpenRangesAddress()
   {
      return GWT.getHostPageBaseURL() + "parkOpenRanges.php";
   }

   @Override
   public String getUserLogInAddress()
   {
      return GWT.getHostPageBaseURL() + "clock.php";
   }

   @Override
   public String getUserLogOutAddress()
   {
      return GWT.getHostPageBaseURL() + "logOutUser";
   }
   
   @Override
   public String getBaseUrl()
   {
      return GWT.getHostPageBaseURL();
   }

   @Override
   public String getHomeUrl()
   {
      return GWT.getHostPageBaseURL();
   }

   @Override
   public String getFriendStatusDataAddress()
   {
      return GWT.getHostPageBaseURL() + "getUserFriendsRideStatus.php";
   }
}

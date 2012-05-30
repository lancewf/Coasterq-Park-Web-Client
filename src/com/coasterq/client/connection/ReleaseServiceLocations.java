package com.coasterq.client.connection;

public class ReleaseServiceLocations implements ServiceLocations
{
   @Override
   public String getItineraryLoadAddress()
   {
      return getNativeBaseServicesUrl() + "getInitialItineraryRides";
   }

   @Override
   public String getInitialRideDataAddress()
   {
      return getNativeBaseServicesUrl() + "getInitialRides";
   }

   @Override
   public String getItineraryRideUpdaterAddress()
   {
      return getNativeBaseServicesUrl() + "updateItinerary";
   }

   @Override
   public String getWaitTimeSenderAddress()
   {
      return getNativeBaseServicesUrl() + "enterTimeForRide";
   }

   @Override
   public String getParkInformationAddress()
   {
      return getNativeBaseServicesUrl() + "getParkInformation";
   }

   @Override
   public String getParkClockAddress()
   {
      return getNativeBaseServicesUrl() + "getClockUpdate";
   }

   @Override
   public String getParkOpenRangesAddress()
   {
      return getNativeBaseServicesUrl() + "getParkOpenRanges";
   }
   
   @Override
   public String getUserLogInAddress()
   {
      return getNativeBaseServicesUrl() + "logInUser";
   }

   @Override
   public String getUserLogOutAddress()
   {
      return getNativeBaseServicesUrl() + "logOutUser";
   }
   
   @Override
   public String getBaseUrl()
   {
      return getNativeBaseUrl();
   }
   
   @Override
   public String getHomeUrl()
   {
      return getNativeHomeUrl();
   }
   
   @Override
   public String getFriendStatusDataAddress()
   {
      return getNativeBaseServicesUrl() + "getUserFriendsRideStatus";
   }
   
   // --------------------------------------------------------------------------
   // Private Members
   // --------------------------------------------------------------------------
   
   private static native String getNativeHomeUrl()
   /*-{
      return $wnd.home_url;
   }-*/;
   
   private static native String getNativeBaseUrl() 
   /*-{
      return $wnd.base_url;
   }-*/;
   
   private static native String getNativeBaseServicesUrl() 
   /*-{
      return $wnd.base_services_url;
   }-*/;
}

package com.coasterq.client.connection;

public interface ServiceLocations
{
   String getItineraryLoadAddress();

   String getInitialRideDataAddress();

   String getItineraryRideUpdaterAddress();
   
   String getWaitTimeSenderAddress();

   String getParkInformationAddress();

   String getParkClockAddress();

   String getParkOpenRangesAddress();

   String getUserLogInAddress();

   String getUserLogOutAddress();
   
   String getBaseUrl();
   
   String getHomeUrl();

   String getFriendStatusDataAddress();
}

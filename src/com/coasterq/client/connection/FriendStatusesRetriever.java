package com.coasterq.client.connection;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.coasterq.client.data.FriendStatus;
import com.coasterq.client.data.Ride;
import com.coasterq.client.data.RideManagerable;
import com.finfrock.client.FacebookUser;
import com.finfrock.client.RetrieverAndroid15;
import com.google.gwt.core.client.JsArray;

public class FriendStatusesRetriever extends RetrieverAndroid15<List<FriendStatus>>
{
   // --------------------------------------------------------------------------
   // Private Data
   // --------------------------------------------------------------------------
   
   private RideManagerable rideManager;
   
   // --------------------------------------------------------------------------
   // Constructor
   // --------------------------------------------------------------------------

   public FriendStatusesRetriever(RideManagerable rideManager, 
                                  ServiceLocations serviceLocations)
   {
      super(serviceLocations.getFriendStatusDataAddress());
      
      this.rideManager = rideManager;
   }
   
   // --------------------------------------------------------------------------
   // Retriever Members
   // --------------------------------------------------------------------------
   
   @SuppressWarnings("deprecation")
   @Override
   protected List<FriendStatus> parseText(String rawText)
   {
      JsArray<FriendStatusData> friendStatusDataJsArray = 
         asArrayOfFriendStatusData(rawText);
      
      List<FriendStatus> friendStatuses = new ArrayList<FriendStatus>();
      
      for(int index = 0; index < friendStatusDataJsArray.length(); index++)
      {
         FriendStatusData friendStatusData = friendStatusDataJsArray.get(index);
         
         Ride ride = rideManager.getRide(friendStatusData.getRideId());
         
         FriendStatus friendStatus = new FriendStatus();
         
         FacebookUser user = new FacebookUser(friendStatusData.getFacebookUid());
         
         friendStatus.setFacebookUser(user);
         friendStatus.setRide(ride);
         
         Date dateTime = new Date(friendStatusData.getYear() - 1900, 
            friendStatusData.getMonth() - 1, 
            friendStatusData.getDayOfMonth(), friendStatusData.get24Hour(), 
            friendStatusData.getMinutes());
         
         friendStatus.setDateTime(dateTime);
         
         friendStatus.setWaitTime(friendStatusData.getWaitTime());
         
         friendStatuses.add(friendStatus);
      }
      
      return friendStatuses;
   }
   
   // --------------------------------------------------------------------------
   // Private Members
   // --------------------------------------------------------------------------
   
   private final native JsArray<FriendStatusData> 
      asArrayOfFriendStatusData(String json) 
   /*-{
      return eval(json);
   }-*/;
}

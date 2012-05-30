package com.coasterq.client.connection;

import com.coasterq.client.data.RideWaitEntry;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;

public class WaitTimeSender
{
   // --------------------------------------------------------------------------
   // Private Data
   // --------------------------------------------------------------------------

   private ServiceLocations serviceLocations;
   
   // --------------------------------------------------------------------------
   // Constructor
   // --------------------------------------------------------------------------

   public WaitTimeSender(ServiceLocations serviceLocations)
   {
      this.serviceLocations = serviceLocations;
   }
   
   // --------------------------------------------------------------------------
   // Public Members
   // --------------------------------------------------------------------------

   public void update(RideWaitEntry rideWaitEntry)
   {
      String url = URL.encode(serviceLocations.getWaitTimeSenderAddress());
      
      RequestBuilder builder = new RequestBuilder(RequestBuilder.POST, url);

      builder.setHeader("Content-Type", "application/x-www-form-urlencoded");
      
      String requestData = getText(rideWaitEntry);

      try
      {
         builder.sendRequest(requestData, new RequestCallback()
         {
            public void onError(Request request, Throwable exception)
            {

            }

            public void onResponseReceived(Request request, Response response)
            {
//               if(!response.getText().equals("success"))
//               {
//                  DialogUtility.ShowDialog(response.getText());
//               }
            }
         });
      }
      catch (RequestException e)
      {

      } 
   }
   
   // --------------------------------------------------------------------------
   // Private Members
   // --------------------------------------------------------------------------

   private String getText(RideWaitEntry rideWaitEntryToSend)
   {
      String serverUpdateString = 
         "rideId=" + rideWaitEntryToSend.getRide().getId() + 
         "&waitmin=" + rideWaitEntryToSend.getWaitTimeMin() +
         "&timehour=" + rideWaitEntryToSend.getTimeHour() +
         "&timemin=" + rideWaitEntryToSend.getTimeMin() +
         "&dayofmonth=" +rideWaitEntryToSend.getDayOfMonth() +
         "&month=" + rideWaitEntryToSend.getMonth() +
         "&year=" + rideWaitEntryToSend.getYear() +
         "&isinsidepark=" +rideWaitEntryToSend.isInsidePark() +
         "&latitude=" + rideWaitEntryToSend.getLatitude() +
         "&longitude=" + rideWaitEntryToSend.getLongitude();
      
      return serverUpdateString;
   }
}

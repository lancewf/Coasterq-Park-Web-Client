package com.coasterq.client.connection;

import java.util.List;

import com.coasterq.client.data.ItineraryRide;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.Window;

public class ItineraryRideUpdater
{
   // --------------------------------------------------------------------------
   // Private Data
   // --------------------------------------------------------------------------

   private ServiceLocations serviceLocations;
   
   // --------------------------------------------------------------------------
   // Constructor
   // --------------------------------------------------------------------------

   public ItineraryRideUpdater(ServiceLocations serviceLocations)
   {
      this.serviceLocations = serviceLocations;
   }
   
   // --------------------------------------------------------------------------
   // Public Members
   // --------------------------------------------------------------------------

   public void update(List<ItineraryRide> itineraryRides)
   {
      String url = URL.encode(serviceLocations.getItineraryRideUpdaterAddress());
      
      RequestBuilder builder = new RequestBuilder(RequestBuilder.POST, url);

      builder.setHeader("Content-Type", "application/x-www-form-urlencoded");
      
      String requestData = getItineraryRidesText(itineraryRides);

      try
      {
         builder.sendRequest(
            "itineraryRides=" + requestData, new RequestCallback()
         {
            public void onError(Request request, Throwable exception)
            {

            }

            public void onResponseReceived(Request request, Response response)
            {
               if(!response.getText().equals("success"))
               {
                  Window.alert(response.getText());
               }
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

   private String getItineraryRidesText(
                                    List<ItineraryRide> itineraryRides)
   {
      String serverUpdateString = "";
      
      for (ItineraryRide itineraryRide : itineraryRides)
      {
         serverUpdateString += itineraryRide.getPriority() + "-"
               + itineraryRide.getRide().getId() + ",";
      }
      
      if (serverUpdateString.length() > 0)
      {
         serverUpdateString = serverUpdateString.substring(0,
            serverUpdateString.length() - 1);
      }
      
      return serverUpdateString;
   }
}

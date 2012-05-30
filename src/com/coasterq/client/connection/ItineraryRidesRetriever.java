package com.coasterq.client.connection;

import java.util.ArrayList;
import java.util.List;

import com.finfrock.client.RetrieverAndroid15;
import com.google.gwt.core.client.JsArray;

public class ItineraryRidesRetriever extends RetrieverAndroid15<List<ItineraryRideData>>
{
   // --------------------------------------------------------------------------
   // Constructor
   // --------------------------------------------------------------------------

   public ItineraryRidesRetriever(ServiceLocations serviceLocations)
   {
      super(serviceLocations.getItineraryLoadAddress());
   }
   
   // --------------------------------------------------------------------------
   // Retriever Members
   // --------------------------------------------------------------------------
   
   @Override
   protected List<ItineraryRideData> parseText(String rawText)
   {
      List<ItineraryRideData> itineraryRideDatas = 
         new ArrayList<ItineraryRideData>();
      
      JsArray<ItineraryRideData> itineraryRideDatasJsArray = 
         asArrayOfItineraryRideData(rawText);
      for(int index = 0; 
      index < itineraryRideDatasJsArray.length(); index++)
      {
         ItineraryRideData itineraryRideData = 
            itineraryRideDatasJsArray.get(index);
         
         itineraryRideDatas.add(itineraryRideData);
      }
      
      return itineraryRideDatas;
   }
   
   // --------------------------------------------------------------------------
   // Private Members
   // --------------------------------------------------------------------------
   
   private final native JsArray<ItineraryRideData> 
      asArrayOfItineraryRideData(String json) 
   /*-{
      return eval(json);
   }-*/;
}
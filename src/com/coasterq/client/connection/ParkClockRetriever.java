package com.coasterq.client.connection;

import com.finfrock.client.RetrieverAndroid15;
import com.google.gwt.core.client.JsArray;

public class ParkClockRetriever extends RetrieverAndroid15<ParkClockData>
{
   // --------------------------------------------------------------------------
   // Constructor
   // --------------------------------------------------------------------------

   public ParkClockRetriever(ServiceLocations serviceLocations)
   {
      super(serviceLocations.getParkClockAddress());
   }
   
   // --------------------------------------------------------------------------
   // Retriever Members
   // --------------------------------------------------------------------------
   
   @Override
   protected ParkClockData parseText(String rawText)
   {
      JsArray<ParkClockData> parkClocksJsArray = 
         asArrayOfParkClock(rawText);
      
      ParkClockData parkClock = parkClocksJsArray.get(0);
      
      return parkClock;
   }
   
   // --------------------------------------------------------------------------
   // Private Members
   // --------------------------------------------------------------------------
   
   private final native JsArray<ParkClockData> 
      asArrayOfParkClock(String json) 
   /*-{
      return eval(json);
   }-*/;
}
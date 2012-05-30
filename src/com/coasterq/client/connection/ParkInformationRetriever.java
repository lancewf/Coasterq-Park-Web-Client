package com.coasterq.client.connection;

import com.coasterq.client.data.ParkInformation;
import com.finfrock.client.RetrieverAndroid15;
import com.google.gwt.core.client.JsArray;

public class ParkInformationRetriever extends RetrieverAndroid15<ParkInformation>
{
   // --------------------------------------------------------------------------
   // Constructor
   // --------------------------------------------------------------------------

   public ParkInformationRetriever(ServiceLocations serviceLocations)
   {
      super(serviceLocations.getParkInformationAddress());
   }
   
   // --------------------------------------------------------------------------
   // Retriever Members
   // --------------------------------------------------------------------------
   
   @Override
   protected ParkInformation parseText(String rawText)
   {
      JsArray<ParkInformation> parkInformationsJsArray = 
         asArrayOfParkInformation(rawText);
      
      ParkInformation parkInformation = parkInformationsJsArray.get(0);
      
      return parkInformation;
   }
   
   // --------------------------------------------------------------------------
   // Private Members
   // --------------------------------------------------------------------------
   
   private final native JsArray<ParkInformation> 
      asArrayOfParkInformation(String json) 
   /*-{
      return eval(json);
   }-*/;
}
package com.coasterq.client.connection;

import java.util.ArrayList;
import java.util.List;

import com.coasterq.client.data.ParkOpenRange;
import com.finfrock.client.RetrieverAndroid15;
import com.google.gwt.core.client.JsArray;

public class ParkOpenRangeRetriever extends RetrieverAndroid15<List<ParkOpenRange>>
{
   // --------------------------------------------------------------------------
   // Constructor
   // --------------------------------------------------------------------------

   public ParkOpenRangeRetriever(ServiceLocations serviceLocations)
   {
      super(serviceLocations.getParkOpenRangesAddress());
   }
   
   // --------------------------------------------------------------------------
   // Retriever Members
   // --------------------------------------------------------------------------
   
   @Override
   protected List<ParkOpenRange> parseText(String rawText)
   {
      JsArray<ParkOpenRangeData> parkOpenRangesJsArray = 
         asArrayOfParkOpenRanges(rawText);
      
      List<ParkOpenRange> parkOpenRanges = new ArrayList<ParkOpenRange>();
      for(int index = 0; index < parkOpenRangesJsArray.length(); index++)
      {
         ParkOpenRangeData parkOpenRangeData = 
            parkOpenRangesJsArray.get(index);
         
         ParkOpenRange parkOpenRange = new ParkOpenRange(parkOpenRangeData);
         
         parkOpenRanges.add(parkOpenRange);
      }
      
      return parkOpenRanges;
   }
   
   // --------------------------------------------------------------------------
   // Private Members
   // --------------------------------------------------------------------------
   
   private final native JsArray<ParkOpenRangeData> 
      asArrayOfParkOpenRanges(String json) 
   /*-{
      return eval(json);
   }-*/;
}

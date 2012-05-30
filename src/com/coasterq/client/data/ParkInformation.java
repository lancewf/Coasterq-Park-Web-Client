package com.coasterq.client.data;

import com.google.gwt.core.client.JavaScriptObject;

public class ParkInformation extends JavaScriptObject
{
   protected ParkInformation() 
   {
      
   }
   
   public final native String getParkName()
   /*-{
      return this.parkName;
   }-*/;
   
   public final native boolean getIsFasspassAvailable()
   /*-{
      return this.isFasspassAvailable;
   }-*/;

   public final native double getLatitude()
   /*-{
      return this.latitude;
   }-*/;

   public final native double getLongitude()
   /*-{
      return this.longitude;
   }-*/;
}
 
package com.coasterq.client.connection;

import com.google.gwt.core.client.JavaScriptObject;

public class ParkClockData extends JavaScriptObject
{
   protected ParkClockData() 
   {
      
   }
   
   public final native int get24Hour()
   /*-{
      return this.hour;
   }-*/;
   
   public final native int getMinutes()
   /*-{
      return this.minutes;
   }-*/;
   
   public final native int getDayOfMonth()
   /*-{
      return this.dayOfMonth;
   }-*/;
   
   public final native int getYear()
   /*-{
      return this.year;
   }-*/;
   
   public final native int getMonth()
   /*-{
      return this.month;
   }-*/;
}

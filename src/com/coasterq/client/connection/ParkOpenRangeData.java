package com.coasterq.client.connection;

import com.google.gwt.core.client.JavaScriptObject;

public class ParkOpenRangeData extends JavaScriptObject
{
   protected ParkOpenRangeData() 
   {
      
   }
   
   public final native int getOpenHour()
   /*-{
      return this.openHour;
   }-*/;
   
   public final native int getOpenMin()
   /*-{
      return this.openMin;
   }-*/;
   
   public final native int getCloseHour()
   /*-{
      return this.closeHour;
   }-*/;
   
   public final native int getCloseMin()
   /*-{
      return this.closeMin;
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

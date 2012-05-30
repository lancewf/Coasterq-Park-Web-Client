package com.coasterq.client.view;

import com.coasterq.client.data.Point;
import com.coasterq.client.data.Ride;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.googlecode.gchart.client.GChart;

public class RideGraph extends GChart
{
   // --------------------------------------------------------------------------
   // Private Class Data
   // --------------------------------------------------------------------------

   private static final double RESIZE_PERCENT = 0.70;
   
   // --------------------------------------------------------------------------
   // Private Instance Data
   // --------------------------------------------------------------------------

   private Ride selectedRide;

   private int width = 0;
   private int height = 0;
   
   // --------------------------------------------------------------------------
   // Constructor
   // --------------------------------------------------------------------------

   public RideGraph()
   {
      initialize();
   }
   
   // --------------------------------------------------------------------------
   // Public Members
   // --------------------------------------------------------------------------

   public void setRide(Ride ride)
   {
      this.selectedRide = ride;
      
      updatePoints();
   }
   
   // --------------------------------------------------------------------------
   // Private Members
   // --------------------------------------------------------------------------

   private void initialize()
   {
      width = (int)(Window.getClientWidth() * RESIZE_PERCENT);
      height = (int)(Window.getClientHeight() * RESIZE_PERCENT);
      
      setChartSize(width, height);
      setPadding("25px 15px 30px 60px");
      setBorderStyle("none");
      setChartTitleThickness(30);

      // configure x-axis
      getXAxis().setHasGridlines(false);
      getXAxis().setTickLabelFontSize(10);
      getXAxis().setAxisLabelThickness(20);
      getXAxis().setTickLabelFontSize(5);
      
      // configure y-axis
      getYAxis().setAxisMin(0);
      getYAxis().setAxisMax(150);
      getYAxis().setHasGridlines(false);
      getYAxis().setTickCount(10); // ticks at: -1, -0.5, 0, 0.5, 1
      getYAxis().setTicksPerLabel(2); // labels at: -1, 0, 1
      getYAxis().setTickLabelThickness(10);
      getYAxis().setTickLabelFontSize(10);
      getYAxis().setAxisLabelThickness(20);
      getYAxis().addTick(0, "0 min");
      getYAxis().addTick(30, "30 min");
      getYAxis().addTick(60, "1 hour");
      getYAxis().addTick(90, "1 1/2 hours");
      getYAxis().addTick(120, "2 hours");
      getYAxis().addTick(150, "2 1/2 hours");
      
      // add/configure cosine curve
      addCurve();
      getCurve().getSymbol().setSymbolType(SymbolType.LINE);
      getCurve().getSymbol().setFillThickness(4); // px line width
      getCurve().getSymbol().setBorderWidth(1);
      getCurve().getSymbol().setBorderColor("#ff0000");
      getCurve().getSymbol().setBackgroundColor("#ffc6de");
      getCurve().getSymbol().setWidth(0); // remove square symbols
      getCurve().getSymbol().setHeight(0); // marking each data point
      getCurve().getSymbol().setHoverSelectionEnabled(false);
      getCurve().getSymbol().setHoverAnnotationEnabled(false);
      
      // add future curve
      addCurve();
      getCurve().getSymbol().setSymbolType(SymbolType.LINE);
      getCurve().getSymbol().setFillThickness(4); // px line width
      getCurve().getSymbol().setBorderWidth(1);
      getCurve().getSymbol().setBorderColor("#0000ff");
      getCurve().getSymbol().setBackgroundColor("#ffc6de");
      getCurve().getSymbol().setWidth(0); // remove square symbols
      getCurve().getSymbol().setHeight(0); // marking each data point
      getCurve().getSymbol().setHoverSelectionEnabled(false);
      getCurve().getSymbol().setHoverAnnotationEnabled(false);
      
      update();
      
      Window.addResizeHandler(new ResizeHandler()
      {
         @Override
         public void onResize(ResizeEvent event)
         {
            width = (int)(event.getWidth() * RESIZE_PERCENT);
            height = (int)(event.getHeight() * RESIZE_PERCENT);
            
            setChartSize(width, height);
            
            updateXAxis();
            
            update();
         }
      });
   }
   
   private void updatePoints()
   {
//      minTime = Integer.MAX_VALUE;
//      maxTime = Integer.MIN_VALUE;
//      
      addPastPoints(selectedRide);
      addFuturePoints(selectedRide);
      
      updateXAxis();
      
      update();
   }
   
   private void updateXAxis()
   {
      getXAxis().clearTicks();
      int totalNumberOfHours = 17;
      
      double tickWidth = 45;
      double spaceAfterTick = tickWidth;
      double tickAndSpaceWith = tickWidth + spaceAfterTick;
      
      double numberOfTickAndSpaceAllowed = (width - tickWidth)/tickAndSpaceWith;
      
      int numberOfHours = (int)Math.round(numberOfTickAndSpaceAllowed);
      
      //Add one for the last tick without a space. 
      numberOfHours++;
      
      int hourSpaceBetweenTicks = 0;
      
      if(numberOfHours >= totalNumberOfHours)
      {
         hourSpaceBetweenTicks = 1;
      }
      else
      {
         hourSpaceBetweenTicks = totalNumberOfHours/numberOfHours;
      }
      
      int middleTime = 54000;
      int middleHour = 15;
      int timePerHourtotalTimeDiff = 3600;  
      
      getXAxis().addTick(middleTime, getHourDisplay(middleHour));
      
      //forward from 1500
      int hour = middleHour;
      while(true)
      {
         hour += hourSpaceBetweenTicks;
         if(hour > 23)
         {
           break;  
         }
         
         getXAxis().addTick(hour * timePerHourtotalTimeDiff, 
            getHourDisplay(hour));
      }
      
      //Backward from 1500
      hour = middleHour;
      while(true)
      {
         hour -= hourSpaceBetweenTicks;
         if(hour < 7)
         {
           break;  
         }
         
         getXAxis().addTick(hour * timePerHourtotalTimeDiff, 
            getHourDisplay(hour));
      }
   }
   
   private String getHourDisplay(int hour)
   {
      String text = "";
      if(hour < 12)
      {
         text = hour + " AM";
      }
      else if(hour == 12)
      {
         text = "12 PM";
      }
      else if(hour > 12)
      {
         hour -= 12;
         
         text = hour + " PM";
      }
      
      return text;
   }
   
   private void addFuturePoints(Ride ride)
   {
      Curve curve = getCurve(1);

      curve.clearPoints();

      for(Point point : ride.getFuturePoints())
      {
//         if(point.getX() < minTime)
//         {
//            minTime = point.getX();
//         }
//         if(point.getX() > maxTime)
//         {
//            maxTime = point.getX();
//         }
//         
         curve.addPoint(point.getX(), point.getY());
      }
   }
   
   private void addPastPoints(Ride ride)
   {
      Curve curve = getCurve(0);

      curve.clearPoints();

      for(Point point : ride.getPastPoints())
      {
//         if(point.getX() < minTime)
//         {
//            minTime = point.getX();
//         }
//         if(point.getX() > maxTime)
//         {
//            maxTime = point.getX();
//         }
         
         curve.addPoint(point.getX(), point.getY());
      }
   }
}

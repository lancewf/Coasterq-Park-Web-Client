package com.coasterq.client.view.myqueue;

import com.coasterq.client.connection.CQImageBundle;
import com.coasterq.client.data.ItineraryRideManagerable;
import com.coasterq.client.data.ParkInformation;
import com.coasterq.client.view.Legend;
import com.finfrock.client.DataChangeListener;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class MyQPanel extends VerticalPanel
{
   private ItineraryRideTable queTable;
   private VerticalPanel emptyItinerary;
   private Legend legend;
   private ItineraryRideManagerable itineraryRideManager;
   
   // --------------------------------------------------------------------------
   // Constructor
   // --------------------------------------------------------------------------

   public MyQPanel(ItineraryRideManagerable itineraryRideManager, 
                   ParkInformation parkInformation, CQImageBundle cqImageBundle)
   {
      this.itineraryRideManager = itineraryRideManager;
      
      createPanel(itineraryRideManager, parkInformation, cqImageBundle);
   }

   // --------------------------------------------------------------------------
   // Public Members
   // --------------------------------------------------------------------------
  
   public void refreshView()
   {
      // TODO Auto-generated method stub
      
   }
   
   public String getName()
   {
      return "My Rides Q";
   }
   
   // --------------------------------------------------------------------------
   // Private Members
   // --------------------------------------------------------------------------

   private void createPanel(ItineraryRideManagerable itineraryRideManager, 
                            ParkInformation parkInformation, 
                            CQImageBundle cqImageBundle)
   {
      queTable = new ItineraryRideTable(itineraryRideManager, cqImageBundle);

      emptyItinerary = createEmptyPanel();
      
      setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
      add(queTable);
      add(emptyItinerary);
      
      legend = new Legend(parkInformation, cqImageBundle);

      setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
      add(legend);
      
      itineraryRideManager.addDataChangeListener(new DataChangeListener(){
         @Override
         public void onDataChange()
         {
            setActivePanel();
         }
      });
      
      setActivePanel();
   }
   
   private void setActivePanel()
   {
      if(itineraryRideManager.getItineraryRides().size() > 0)
      {
         queTable.setVisible(true);
         emptyItinerary.setVisible(false);
         legend.setVisible(true);
      }
      else 
      {
         queTable.setVisible(false);
         emptyItinerary.setVisible(true);
         legend.setVisible(false);
      }
   }
   
   private VerticalPanel createEmptyPanel()
   {
      VerticalPanel emptyPanel = new VerticalPanel();
      
      emptyPanel.addStyleName("enterWaitTimePanel");
      
      emptyPanel.add(createClosedLabel());
      
      return emptyPanel;
   }
   
   private Widget createClosedLabel()
   {
      HorizontalPanel labelPanel = new HorizontalPanel();
      
      Label emptylabel1 = new Label(
         "You currently have no rides in your Q. " + 
         "Select the ride search tab to add rides to your Q.");
      
      emptylabel1.setStyleName("cq-Label");
      
      labelPanel.add(emptylabel1);
      
      labelPanel.addStyleName("enterWaitTimeSubPanel");
      
      return labelPanel;
   }
}
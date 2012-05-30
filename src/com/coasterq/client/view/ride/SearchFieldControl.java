package com.coasterq.client.view.ride;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.coasterq.client.data.ParkInformation;
import com.coasterq.client.data.Ride;
import com.coasterq.client.data.RideSearchManager;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.HasHorizontalAlignment.HorizontalAlignmentConstant;

public class SearchFieldControl extends Grid
{
   // --------------------------------------------------------------------------
   // Private Data
   // --------------------------------------------------------------------------

   private RideSearchManager rideSearchManager;
   
   // --------------------------------------------------------------------------
   // Constructor
   // --------------------------------------------------------------------------
   
   public SearchFieldControl(RideSearchManager rideSearchManager, 
                             ParkInformation parkInformation)
   {
      super(4, 2);
      
      this.rideSearchManager = rideSearchManager;
      
      initialize(parkInformation);
   }
   
   // --------------------------------------------------------------------------
   // Private Members
   // --------------------------------------------------------------------------
   
   private void initialize(ParkInformation parkInformation) 
   {
      int currentRow = 0;
      
      setText(currentRow, 0, "Location:");
      getCellFormatter().setHorizontalAlignment(currentRow, 0, HasHorizontalAlignment.ALIGN_LEFT);
      setWidget(currentRow, 1, createLocationSelection());
      getCellFormatter().setHorizontalAlignment(currentRow, 1, HasHorizontalAlignment.ALIGN_LEFT);
      currentRow++;
      
      if(parkInformation.getIsFasspassAvailable())
      {
         setText(currentRow, 0, "Fastpass Available:");
         getCellFormatter().setHorizontalAlignment(currentRow, 0, HasHorizontalAlignment.ALIGN_LEFT);
         setWidget(currentRow, 1, createFastpassSelection());
         getCellFormatter().setHorizontalAlignment(currentRow, 1, HasHorizontalAlignment.ALIGN_LEFT);
         currentRow++;
      }
      else
      {
         resize(3, 2);  
      }
      
      setText(currentRow, 0, "Height Requirement:");
      getCellFormatter().setHorizontalAlignment(currentRow, 0, HasHorizontalAlignment.ALIGN_LEFT);
      setWidget(currentRow, 1, createHeightRequirementSelection());
      getCellFormatter().setHorizontalAlignment(currentRow, 1, HasHorizontalAlignment.ALIGN_LEFT);
      currentRow++;
      
      setText(currentRow, 0, "Current Wait Time:");
      getCellFormatter().setHorizontalAlignment(currentRow, 0, HasHorizontalAlignment.ALIGN_LEFT);
      setWidget(currentRow, 1, createCurrentWaitTimeSelection());
      getCellFormatter().setHorizontalAlignment(currentRow, 1, HasHorizontalAlignment.ALIGN_LEFT);
      currentRow++;
   }

   private ListBox createFastpassSelection()
   {
      ListBox fastpassCombobox = new ListBox();
      
      fastpassCombobox.addItem(RideSearchManager.ALL_SELECTION);
      fastpassCombobox.addItem(RideSearchManager.YES_SELECTION);
      fastpassCombobox.addItem(RideSearchManager.NO_SELECTION);
      
      fastpassCombobox.addChangeHandler(new ChangeHandler(){
         @Override
         public void onChange(ChangeEvent event)
         {
            ListBox listBox = (ListBox)event.getSource();
            
            String text = 
               listBox.getItemText(listBox.getSelectedIndex());
            
            rideSearchManager.setFastpassSelection(text);
         }
      });
      
      return fastpassCombobox;
   }
   
   private ListBox createLocationSelection()
   {
      ListBox locationCombobox = new ListBox();
      
      locationCombobox.addItem(RideSearchManager.ALL_SELECTION);
      
      for(String location : getLocations())
      {
         locationCombobox.addItem(location);
      }
      
      locationCombobox.addChangeHandler(new ChangeHandler(){
         @Override
         public void onChange(ChangeEvent event)
         {
            ListBox listBox = (ListBox)event.getSource();
            
            String text = 
               listBox.getItemText(listBox.getSelectedIndex());
            
            rideSearchManager.setLocationSelection(text);
         }
         
      });
      
      return locationCombobox;
   }
   
   private List<String> getLocations()
   {
      List<String> locations = new ArrayList<String>();
      
      for(Ride ride : rideSearchManager.getRides())
      {
         if(!locations.contains(ride.getLocation()))
         {
            locations.add(ride.getLocation());
         }
      }
      
      Collections.sort(locations);
      
      return locations;
   }
   
   private ListBox createHeightRequirementSelection()
   {
      ListBox combobox = new ListBox();
      
      combobox.addItem(RideSearchManager.ALL_SELECTION);
      combobox.addItem(RideSearchManager.YES_SELECTION);
      combobox.addItem(RideSearchManager.NO_SELECTION);

      combobox.addChangeHandler(new ChangeHandler(){
         @Override
         public void onChange(ChangeEvent event)
         {
            ListBox listBox = (ListBox)event.getSource();
            
            String text = 
               listBox.getItemText(listBox.getSelectedIndex());
            
            rideSearchManager.setHeightRequirementSelection(text);
         }
         
      });
      
      return combobox;
   }
   
   private ListBox createCurrentWaitTimeSelection()
   {
      ListBox combobox = new ListBox();
      
      combobox.addItem(RideSearchManager.ALL_SELECTION);
      combobox.addItem(RideSearchManager.MAX_WAIT_TIME_30);
      combobox.addItem(RideSearchManager.MAX_WAIT_TIME_60);
      combobox.addItem(RideSearchManager.MAX_WAIT_TIME_90);
      combobox.addItem(RideSearchManager.MAX_WAIT_TIME_120);
      
      combobox.addChangeHandler(new ChangeHandler(){
         @Override
         public void onChange(ChangeEvent event)
         {
            ListBox listBox = (ListBox)event.getSource();
            
            String text = 
               listBox.getItemText(listBox.getSelectedIndex());
            
            rideSearchManager.setCurrentWaitTimeSelection(text);
         }
         
      });
      
      return combobox;
   }
}

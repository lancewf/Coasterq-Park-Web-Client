package com.coasterq.client.data;

import com.finfrock.client.LocationManager;

public class ParkLocationManager
{
   // --------------------------------------------------------------------------
   // Private Data
   // --------------------------------------------------------------------------
   
   private LocationManager locationManager;
   private ParkInformation parkInformation;
   private static double RADIUS_OF_PARK = 3; //KM 
   
   // --------------------------------------------------------------------------
   // Constructor
   // --------------------------------------------------------------------------
   
   public ParkLocationManager(LocationManager locationManager, 
                              ParkInformation parkInformation)
   {
      this.locationManager = locationManager;
      this.parkInformation = parkInformation;
   }
   
   // --------------------------------------------------------------------------
   // Public Members
   // --------------------------------------------------------------------------
   
   public boolean isInisidePark()
   {
      if (locationManager.isSupported())
      {
         double distance = locationManager.findDistanceFrom(parkInformation
            .getLatitude(), parkInformation.getLongitude());

         if (distance <= RADIUS_OF_PARK)
         {
            return true;
         }
      }
      
      return false;
   }
   
   public boolean isSupported()
   {
      return locationManager.isSupported();
   }
   
   public Double getLongitude()
   {
      Double longitude = null;
      
      if(locationManager.isSupported())
      {
         return locationManager.getLongitude();
      }
      
      return longitude;
   }
   
   public Double getLatitude()
   {
      Double latitude = null;
      
      if(locationManager.isSupported())
      {
         return locationManager.getLatitude();
      }
      
      return latitude;
   }
   
//   public double 
}

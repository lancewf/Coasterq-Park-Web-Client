package com.coasterq.client.connection;

import java.util.List;

import com.coasterq.client.data.ItineraryRide;
import com.coasterq.client.data.ItineraryRideManager;
import com.coasterq.client.data.ItineraryRideManagerable;
import com.coasterq.client.data.ParkClock;
import com.coasterq.client.data.ParkInformation;
import com.coasterq.client.data.ParkOpenRange;
import com.coasterq.client.data.Ride;
import com.coasterq.client.data.RideManager;
import com.coasterq.client.data.RideManagerable;
import com.coasterq.client.data.RideUpdateAgent;
import com.finfrock.client.Loadable;
import com.finfrock.client.RetrieverAndroid15;

public class DataBuilder
{
   // --------------------------------------------------------------------------
   // Private Data
   // --------------------------------------------------------------------------

   private RetrieverAndroid15<List<ParkOpenRange>> parkOpenRangeRetriever;
   
   private RetrieverAndroid15<List<Ride>> rideDataInitialRetriever;

   private RetrieverAndroid15<List<ItineraryRideData>> itineraryRidesRetriever;
   
   private RetrieverAndroid15<ParkInformation> parkInformationRetriever;
   
   private RetrieverAndroid15<ParkClockData> parkClockRetriever;
   
   private RideManagerable rideManagerable;

   private ItineraryRideManagerable itineraryRideManager;

   private ParkInformation parkInformation;

   private ParkClock parkClock;
   
   private ServiceLocations serviceLocations;
   
   // --------------------------------------------------------------------------
   // Constructor Members
   // --------------------------------------------------------------------------

   public DataBuilder(ServiceLocations serviceLocations)
   {
      this.serviceLocations = serviceLocations;
   }
   
   // --------------------------------------------------------------------------
   // Public Members
   // --------------------------------------------------------------------------

   public void setLoadable(Loadable loadable)
   {
      if (loadable instanceof RidesRetriever)
      {
         rideDataInitialRetriever = (RidesRetriever) loadable;
      }
      else if (loadable instanceof ItineraryRidesRetriever)
      {
         itineraryRidesRetriever = (ItineraryRidesRetriever) loadable;
      }
      else if (loadable instanceof ParkInformationRetriever)
      {
         parkInformationRetriever = (ParkInformationRetriever) loadable;
      }
      else if (loadable instanceof ParkOpenRangeRetriever)
      {
         parkOpenRangeRetriever = (ParkOpenRangeRetriever) loadable;
      }
      else if (loadable instanceof ParkClockRetriever)
      {
         parkClockRetriever = (ParkClockRetriever) loadable;
      }
   }
   
   public void build()
   {
      RideManager rideManager = 
         new RideManager(rideDataInitialRetriever.getObject());
      
      new RideUpdateAgent(serviceLocations, rideManager).start();
      
      rideManagerable = rideManager;

      itineraryRideManager = buildItineraryRideManager(rideManagerable);
      
      parkInformation = parkInformationRetriever.getObject();

      parkClock = buildParkClock();
      
      new ParkClockUpdateAgent(serviceLocations, parkClock).start();
   }
   
   public ParkClock getParkClock()
   {
      return parkClock;
   }

   public ParkInformation getParkInformation()
   {
      return parkInformation;
   }

   public RideManagerable getRideManager()
   {
      return rideManagerable;
   }

   public ItineraryRideManagerable getItineraryRideManager()
   {
      return itineraryRideManager;
   }
   
   // --------------------------------------------------------------------------
   // Private Members
   // --------------------------------------------------------------------------
   
   private ParkClock buildParkClock()
   {
      ParkClockData parkClockData = parkClockRetriever.getObject();

      ParkClock parkClock = new ParkClock(parkClockData, parkOpenRangeRetriever
         .getObject());
      
      return parkClock;
   }

   private ItineraryRideManagerable buildItineraryRideManager(
                                                  RideManagerable rideManager)
   {
      ItineraryRidesBuilder itineraryRideManagerBuilder = 
         new ItineraryRidesBuilder(rideManager, itineraryRidesRetriever);
      
       itineraryRideManagerBuilder.build();
      
       List<ItineraryRide> itineraryRides = 
          itineraryRideManagerBuilder.getItineraryRides();
      
      ItineraryRideManager itineraryRideManager = 
         new ItineraryRideManager(rideManager, itineraryRides, serviceLocations);

      return itineraryRideManager;
   }
}

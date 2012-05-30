package com.coasterq.client.data;

import java.util.List;

import com.finfrock.client.DataChangeListener;

public interface RideManagerable
{

   public abstract List<Ride> getRides();

   public abstract Ride getRide(int rideId);

   public abstract void addDataChangeListener(DataChangeListener dataChangeListener);

   public abstract void removeDataChangeListener(DataChangeListener dataChangeListener);

}
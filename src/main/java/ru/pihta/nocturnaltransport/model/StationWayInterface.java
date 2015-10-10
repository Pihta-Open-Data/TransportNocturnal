package ru.pihta.nocturnaltransport.model;

import java.util.ArrayList;

import java.time.LocalTime;

/**
 * Created by Lev on 10.10.2015.
 */
public interface StationWayInterface {

    //
    StationInterface getStation();

    StationWayInterface getNext();

    ArrayList<StationWayInterface> getTransfers();

    LocalTime getFirst(boolean odd);

    LocalTime getLast(boolean odd);

    LocalTime getInterval();

    //

    void setReachTime(LocalTime time);

    LocalTime getReachTime();
}

package ru.pihta.nocturnaltransport.model;

import java.util.ArrayList;

import java.time.LocalTime;

/**
 * Created by Lev on 10.10.2015.
 */
public interface EntityStationWay {

    //
    StationInterface getStation();

    EntityStationWay getNext();

    ArrayList<EntityStationWay> getTransfers();

    LocalTime getFirst(boolean odd);

    LocalTime getLast(boolean odd);

    LocalTime getInterval();

    //

    void setReachTime(LocalTime time);

    LocalTime getReachTime();
}

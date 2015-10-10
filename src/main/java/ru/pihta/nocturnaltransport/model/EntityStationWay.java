package ru.pihta.nocturnaltransport.model;

import java.util.ArrayList;

import java.time.LocalTime;

/**
 * Created by Lev on 10.10.2015.
 */
public interface EntityStationWay {

    EntityStationWay getNext();

    ArrayList<EntityStationWay> getTransfers();

    LocalTime getFirstTrain(boolean odd);

    LocalTime getLastTrain(boolean odd);

    LocalTime getReachTime();
}

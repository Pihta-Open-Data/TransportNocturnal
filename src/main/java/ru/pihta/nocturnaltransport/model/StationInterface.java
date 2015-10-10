package ru.pihta.nocturnaltransport.model;

import ru.pihta.nocturnaltransport.model.structures.PairStationWay;
import ru.pihta.nocturnaltransport.model.structures.StationType;

/**
 * Created by Lev on 10.10.2015.
 */
public interface StationInterface {

    PairStationWay getPair();

    StationType getType();
}

package ru.pihta.nocturnaltransport.model;

import ru.pihta.nocturnaltransport.model.structures.StationType;

import java.util.List;

/**
 * Created by Lev on 10.10.2015.
 */
public interface StationInterface {

    List<StationWayInterface> getWays();

    StationType getType();
}

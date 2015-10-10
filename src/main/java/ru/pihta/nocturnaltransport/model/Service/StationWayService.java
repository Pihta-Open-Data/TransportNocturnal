package ru.pihta.nocturnaltransport.model.Service;


import ru.pihta.nocturnaltransport.model.Station;
import ru.pihta.nocturnaltransport.model.StationWay;

import java.util.List;

public interface StationWayService {

    public StationWay getById(int id);

    public List<StationWay> getAll();

    public List<StationWay> getStationWays(Station station);
}

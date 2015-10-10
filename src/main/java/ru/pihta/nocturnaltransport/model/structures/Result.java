package ru.pihta.nocturnaltransport.model.structures;

import ru.pihta.nocturnaltransport.model.StationWay;
import ru.pihta.nocturnaltransport.model.StationWayInterface;

import java.util.Set;

/**
 * Created by Lev on 10.10.2015.
 */
public class Result {

    private Set<StationWay> stationWay;

    private Set<RouteLine> routeLines;

    public Result(Set<StationWay> stationWay, Set<RouteLine> routeLines) {
        if (stationWay == null) {
            throw new IllegalArgumentException("entityStationWays is null!");
        }
        if (routeLines == null) {
            throw new IllegalArgumentException("routeLines is null!");
        }
        this.stationWay = stationWay;
        this.routeLines = routeLines;
    }

    public Set<StationWay> getStationWay() {
        return stationWay;
    }

    public Set<RouteLine> getRouteLines() {
        return routeLines;
    }
}

package ru.pihta.nocturnaltransport.model.structures;

import ru.pihta.nocturnaltransport.model.Station;
import java.util.Set;

/**
 * Created by Lev on 10.10.2015.
 */
public class Result {

    private Set<Station> stations;

    private Set<RouteLine> routeLines;

    public Result(Set<Station> stations, Set<RouteLine> routeLines) {
        if (stations == null) {
            throw new IllegalArgumentException("stations is null!");
        }
        if (routeLines == null) {
            throw new IllegalArgumentException("routeLines is null!");
        }
        this.stations = stations;
        this.routeLines = routeLines;
    }

    public Set<Station> getStations() {
        return stations;
    }

    public Set<RouteLine> getRouteLines() {
        return routeLines;
    }
}

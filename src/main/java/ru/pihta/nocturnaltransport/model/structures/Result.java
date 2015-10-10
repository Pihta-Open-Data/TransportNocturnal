package ru.pihta.nocturnaltransport.model.structures;

import ru.pihta.nocturnaltransport.model.EntityStationWay;

import java.util.Set;

/**
 * Created by Lev on 10.10.2015.
 */
public class Result {

    private Set<EntityStationWay> entityStationWays;

    private Set<RouteLine> routeLines;

    public Result(Set<EntityStationWay> entityStationWays, Set<RouteLine> routeLines) {
        if (entityStationWays == null) {
            throw new IllegalArgumentException("entityStationWays is null!");
        }
        if (routeLines == null) {
            throw new IllegalArgumentException("routLines is null!");
        }
        this.entityStationWays = entityStationWays;
        this.routeLines = routeLines;
    }

    public Set<EntityStationWay> getEntityStationWays() {
        return entityStationWays;
    }

    public Set<RouteLine> getRouteLines() {
        return routeLines;
    }
}

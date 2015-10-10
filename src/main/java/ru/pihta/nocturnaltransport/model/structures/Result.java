package ru.pihta.nocturnaltransport.model.structures;

import ru.pihta.nocturnaltransport.model.EntityStationWay;

import java.util.Set;

/**
 * Created by Lev on 10.10.2015.
 */
public class Result {

    private Set<EntityStationWay> entityStationWays;

    private Set<RoutLine> routLines;

    public Result(Set<EntityStationWay> entityStationWays, Set<RoutLine> routLines) {
        if (entityStationWays == null) {
            throw new IllegalArgumentException("entityStationWays is null!");
        }
        if (routLines == null) {
            throw new IllegalArgumentException("routLines is null!");
        }
        this.entityStationWays = entityStationWays;
        this.routLines = routLines;
    }

    public Set<EntityStationWay> getEntityStationWays() {
        return entityStationWays;
    }

    public Set<RoutLine> getRoutLines() {
        return routLines;
    }
}

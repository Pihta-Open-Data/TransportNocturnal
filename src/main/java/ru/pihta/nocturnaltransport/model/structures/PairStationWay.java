package ru.pihta.nocturnaltransport.model.structures;

import ru.pihta.nocturnaltransport.model.EntityStationWay;

/**
 * Created by Lev on 10.10.2015.
 */
public class PairStationWay {

    private EntityStationWay first;

    private EntityStationWay second;

    public PairStationWay(EntityStationWay first, EntityStationWay second) {
        if (first == null) {
            throw new IllegalArgumentException("first is null!");
        }
        if (second == null) {
            throw new IllegalArgumentException("second is null!");
        }
        this.first = first;
        this.second = second;
    }

    public EntityStationWay getFirst() {
        return first;
    }

    public EntityStationWay getSecond() {
        return second;
    }
}

package ru.pihta.nocturnaltransport.model.structures;

import ru.pihta.nocturnaltransport.model.StationWay;
import ru.pihta.nocturnaltransport.model.StationWayInterface;

/**
 * Created by Lev on 10.10.2015.
 */
public class PairStationWay {

    private StationWay first;

    private StationWay second;

    public PairStationWay(StationWay first, StationWay second) {
        if (first == null) {
            throw new IllegalArgumentException("first is null!");
        }
        if (second == null) {
            throw new IllegalArgumentException("second is null!");
        }
        this.first = first;
        this.second = second;
    }

    public StationWay getFirst() {
        return first;
    }

    public StationWay getSecond() {
        return second;
    }
}

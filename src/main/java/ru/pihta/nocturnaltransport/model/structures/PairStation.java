package ru.pihta.nocturnaltransport.model.structures;

import ru.pihta.nocturnaltransport.model.Station;

/**
 * Created by Lev on 10.10.2015.
 */
public class PairStation {

    private int first;

    private int second;

    public PairStation(Station first, Station second) {
        if (first == null) {
            throw new IllegalArgumentException("first is null!");
        }
        if (second == null) {
            throw new IllegalArgumentException("second is null!");
        }
        this.first = first.getId();
        this.second = second.getId();
    }

    public int getFirst() {
        return first;
    }

    public int getSecond() {
        return second;
    }
}

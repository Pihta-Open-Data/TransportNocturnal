package ru.pihta.nocturnaltransport.model.structures;

import ru.pihta.nocturnaltransport.model.Station;

/**
 * Created by Lev on 10.10.2015.
 */
public class RouteLine {

    private boolean transfer;

    PairStation pair;

    public RouteLine(PairStation pair, boolean transfer) {
        if (pair == null) {
            throw new IllegalArgumentException("pair is null!");
        }
        this.pair = pair;
        this.transfer = transfer;
    }

    public boolean isTransfer() {
        return transfer;
    }

    public int getFirst() {
        return pair.getFirst();
    }

    public int getSecond() {
        return pair.getSecond();
    }
}

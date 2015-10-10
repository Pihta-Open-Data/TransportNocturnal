package ru.pihta.nocturnaltransport.model.structures;

import ru.pihta.nocturnaltransport.model.StationWay;
import ru.pihta.nocturnaltransport.model.StationWayInterface;

/**
 * Created by Lev on 10.10.2015.
 */
public class RouteLine {

    private boolean transfer;

    PairStationWay pair;

    public RouteLine(PairStationWay pair, boolean transfer) {
        if (pair == null) {
            throw new IllegalArgumentException("pair is null!");
        }
        this.pair = pair;
        this.transfer = transfer;
    }

    public boolean isTransfer() {
        return transfer;
    }

    public StationWay getFrom() {
        return pair.getFirst();
    }

    public StationWay getTo() {
        return pair.getSecond();
    }
}

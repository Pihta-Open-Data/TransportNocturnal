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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RouteLine routeLine = (RouteLine) o;

        if (transfer != routeLine.transfer) return false;
        if (pair != null ? !pair.equals(routeLine.pair) : routeLine.pair != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (transfer ? 1 : 0);
        result = 31 * result + (pair != null ? pair.hashCode() : 0);
        return result;
    }
}

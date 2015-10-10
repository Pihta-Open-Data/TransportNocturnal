package ru.pihta.nocturnaltransport.model.structures;

import ru.pihta.nocturnaltransport.model.StationWay;
import ru.pihta.nocturnaltransport.model.StationWayInterface;

import java.time.LocalTime;

/**
 * Created by Lev on 10.10.2015.
 */
public class WayToCheck {

    private StationWay way;

    private LocalTime timeNow;

    public WayToCheck(StationWay way, LocalTime timeNow) {
        if (way == null) {
            throw new IllegalArgumentException("way is null!");
        }
        if (timeNow == null) {
            throw new IllegalArgumentException("timeNow is null!");
        }
        this.way = way;
        this.timeNow = timeNow;
    }

    public StationWay getWay() {
        return way;
    }

    public LocalTime getTimeNow() {
        return timeNow;
    }
}

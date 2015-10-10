package ru.pihta.nocturnaltransport.model.structures;

import ru.pihta.nocturnaltransport.model.EntityStationWay;

import java.time.LocalTime;

/**
 * Created by Lev on 10.10.2015.
 */
public class WayToCheck {

    private EntityStationWay way;

    private LocalTime timeNow;

    public WayToCheck(EntityStationWay way, LocalTime timeNow) {
        if (way == null) {
            throw new IllegalArgumentException("way is null!");
        }
        if (timeNow == null) {
            throw new IllegalArgumentException("timeNow is null!");
        }
        this.way = way;
        this.timeNow = timeNow;
    }

    public EntityStationWay getWay() {
        return way;
    }

    public LocalTime getTimeNow() {
        return timeNow;
    }
}

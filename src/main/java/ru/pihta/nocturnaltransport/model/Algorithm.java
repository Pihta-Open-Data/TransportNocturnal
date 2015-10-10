package ru.pihta.nocturnaltransport.model;

import ru.pihta.nocturnaltransport.model.structures.*;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Lev on 10.10.2015.
 */
public class Algorithm {


    /** Universal transfer interval in minutes */
    // TODO: consider this
    private static final int transferInterval = 5;

    /** Universal time of underground transfers closing */
    // TODO: consider this
    private static final LocalTime underTranfClos = LocalTime.of(1, 0, 0, 0);

    /***
     * Gets all available station ways and routes
     */
    public Result getRoutLines(boolean odd, StationInterface station, LocalTime timeNow) {

        Set<EntityStationWay> resultWays = new HashSet<>();
        Set<RoutLine> resultLines = new HashSet<>();

        ArrayList<WayToCheck> waysToCheck = new ArrayList<>();

        // adding start points
        waysToCheck.add(new WayToCheck(station.getPair().getFirst(), timeNow));
        waysToCheck.add(new WayToCheck(station.getPair().getSecond(), timeNow));

        while (waysToCheck.size() > 0) { // check routes while they exist
            checkRoute(odd, waysToCheck.get(0), waysToCheck, resultWays, resultLines);
        }

        // TODO: clear lists collisions (unique identifier), stations
        // TODO: clear temp data
        return new Result(resultWays, resultLines);
    }

    /***
     * Checks route through half-line
     */
    private void checkRoute(boolean odd, WayToCheck wayToCheck, ArrayList<WayToCheck> waysToCheck,
                            Set<EntityStationWay> resultWays, Set<RoutLine> resultLines) {

        EntityStationWay way = wayToCheck.getWay();
        LocalTime timeNow = wayToCheck.getTimeNow();

        // TODO: consider the interval for bus and others
        if (way.getStation().getType() == StationType.UNDERGROUND) {
            int undInterval = getUndergroundInterval(timeNow);

            // if last train will be sooner then the interval, state that we will use it
            if (ChronoUnit.MINUTES.between(way.getLast(odd), timeNow) < undInterval) {
                timeNow = way.getLast(odd);
            }
            else {
                // stating that we have to wait for maximum interval
                timeNow.plusMinutes(undInterval);
            }
        }

        do {

            if (way.getReachTime() != null && way.getReachTime().isBefore(timeNow)) {
                return; // if we have already reached this earlier, there is no need to check next ones
            }

            way.setReachTime(timeNow); // flag when we have reached it
            resultWays.add(way); // adding to reached ways

            ArrayList<EntityStationWay> transfers = way.getTransfers();
            // checking transfers
            if (transfers != null) {
                for (EntityStationWay transfer : transfers) {

                    if (way.getStation().getType() == StationType.UNDERGROUND &&
                            transfer.getStation().getType() == StationType.UNDERGROUND) {
                        // if both station ways are underground, check transfer closing time
                        if (timeNow.isAfter(underTranfClos)) {
                            continue; // we are late to move to that route
                        }
                    }

                    // TODO: consider adding this line
                    // adding line
                    resultLines.add(new RoutLine(new PairStationWay(way, transfer), true));

                    // adding transfer to check the new route. incrementing the time with transfer interval
                    // TODO: consider const interval
                    waysToCheck.add(new WayToCheck(transfer, timeNow.plusMinutes(transferInterval)));
                }
            }

            EntityStationWay next = way.getNext();

            // if there next station exists
            if (next != null) {

                // TODO: consider this moment
                // counting the interval between station ways using last transport units
                long intervalToNext = ChronoUnit.MINUTES.between(next.getLast(odd), way.getLast(odd));

                // TODO: consider adding this line
                // adding line
                resultLines.add(new RoutLine(new PairStationWay(way, next), false));

                // checking the ability to go next
                LocalTime nextTime = timeNow.plusMinutes(intervalToNext);
                if (!nextTime.isAfter(next.getLast(odd)) && nextTime.isBefore(next.getFirst(odd))) {
                    // continuing
                    way = next;
                    timeNow = nextTime;
                } else {
                    way = null;
                    timeNow = null;
                }
            }
        } while (way != null);
    }

    //TODO: do the clearing

    /***
     * Return interval in minutes
     * @param time time now
     * @return interval in minutes
     */
    private int getUndergroundInterval(LocalTime time) {
        // no trains from closing to 3:00, mock
        if (time.isAfter(LocalTime.of(0, 0, 0, 0)) && time.isBefore(LocalTime.of(3, 0, 0, 0))) {
            return 7;
        }
        else {
            return 5;
        }
    }

}

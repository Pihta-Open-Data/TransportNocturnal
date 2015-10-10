package ru.pihta.nocturnaltransport.model;

import ru.pihta.nocturnaltransport.model.structures.*;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Lev on 10.10.2015.
 */
public class Algorithms {


    /** Universal transfer interval in minutes */
    // TODO: consider this
    private static final int transferInterval = 5;

    /** Universal time of underground transfers closing */
    // TODO: consider this
    private static final LocalTime underTranfClos = LocalTime.of(1, 0, 0, 0);

    /***
     * Gets all available station ways and routes
     */
    public static Result getRouteLines(boolean odd, StationInterface station, LocalTime timeNow) {

        Set<EntityStationWay> resultWays = new HashSet<>();
        Set<RouteLine> resultLines = new HashSet<>();

        List<EntityStationWay> starts = station.getWays();
        if (starts == null || starts.size() == 0) {
            return null; // no ways
        }

        List<WayToCheck> waysToCheck = new ArrayList<>();

        // adding start points
        for (EntityStationWay way : starts) {
            waysToCheck.add(new WayToCheck(way, timeNow));
        }

        while (waysToCheck.size() > 0) { // check routes while they exist
            checkRoute(odd, waysToCheck.get(0), waysToCheck, resultWays, resultLines);
        }

        return new Result(resultWays, resultLines);
    }

    /***
     * Checks route through half-line
     */
    private static void checkRoute(boolean odd, WayToCheck wayToCheck, List<WayToCheck> waysToCheck,
                            Set<EntityStationWay> resultWays, Set<RouteLine> resultLines) {

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
                    resultLines.add(new RouteLine(new PairStationWay(way, transfer), true));

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
                resultLines.add(new RouteLine(new PairStationWay(way, next), false));

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
            else { // no next
                way = null;
                timeNow = null;
            }
        } while (way != null);
    }

    /***
     * Return interval in minutes
     * @param time time now
     * @return interval in minutes
     */
    private static int getUndergroundInterval(LocalTime time) {
        // no trains from closing to 3:00, mock
        if (time.isAfter(LocalTime.of(0, 0, 0, 0)) && time.isBefore(LocalTime.of(3, 0, 0, 0))) {
            return 7;
        }
        else {
            return 5;
        }
    }

    /***
     * Gets all lines
     */
    public static Result getAllLines(StationInterface station) {

        Set<EntityStationWay> resultWays = new HashSet<>();
        Set<RouteLine> resultLines = new HashSet<>();

        List<EntityStationWay> starts = station.getWays();
        if (starts == null || starts.size() == 0) {
            return null; // no ways
        }

        List<EntityStationWay> waysToCheck = new ArrayList<>();

        // adding start points
        for (EntityStationWay way : starts) {
            waysToCheck.add(way);
        }

        while (waysToCheck.size() > 0) { // check routes while they exist
            makeRoute(waysToCheck.get(0), waysToCheck, resultWays, resultLines);
        }

        return new Result(resultWays, resultLines);
    }

    /***
     * Make all route
     */
    private static void makeRoute(EntityStationWay entityStationWay, List<EntityStationWay> waysToCheck,
                                   Set<EntityStationWay> resultWays, Set<RouteLine> resultLines) {

        EntityStationWay way = entityStationWay;

        do {

            way.setReachTime(LocalTime.now()); // flag when we have reached it

            ArrayList<EntityStationWay> transfers = way.getTransfers();
            // checking transfers
            if (transfers != null) {
                for (EntityStationWay transfer : transfers) {

                    // TODO: consider adding this line
                    // adding line
                    resultLines.add(new RouteLine(new PairStationWay(way, transfer), true));

                    // adding way
                    waysToCheck.add(transfer);
                }
            }

            EntityStationWay next = way.getNext();

            // if there next station exists
            if (next != null) {
                way = next;
            }

            else {
                way = null; // no next
            }
        } while (way != null);
    }

}

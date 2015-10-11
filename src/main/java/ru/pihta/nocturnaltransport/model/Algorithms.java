package ru.pihta.nocturnaltransport.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.pihta.nocturnaltransport.model.Service.StationWayService;
import ru.pihta.nocturnaltransport.model.Service.TransferService;
import ru.pihta.nocturnaltransport.model.structures.*;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class Algorithms {

    @Autowired
    TransferService transferService;

    @Autowired
    StationWayService stationWayService;

    /** Universal transfer interval in minutes */
    // TODO: consider this
    private static final int transferInterval = 5;

    /** Universal time of underground transfers closing */
    // TODO: consider this
    private final LocalTime underTransClos = LocalTime.of(1, 0, 0, 0);


    List<StationWay> markedWays = new ArrayList<>();

    /***
     * Gets all available station ways and routes
     */
    public Result getRouteLines(boolean odd, Station station, LocalTime timeNow) {

        Set<Station> resultStations = new HashSet<>();
        Set<RouteLine> resultLines = new HashSet<>();

        List<StationWay> starts = stationWayService.getStationWays(station);
        if (starts == null || starts.size() == 0) {
            return null; // no ways
        }

        List<WayToCheck> waysToCheck = new ArrayList<>();

        // adding start points
        for (StationWay way : starts) {
            waysToCheck.add(new WayToCheck(way, timeNow));
        }

        while (waysToCheck.size() > 0) { // check routes while they exist
            checkRoute(odd, waysToCheck.get(0), waysToCheck, resultStations, resultLines);
            waysToCheck.remove(0);
            if (markedWays.size() > 500) {
                break;
            }
        }

        return new Result(resultStations, resultLines);
    }

    /***
     * Checks route through half-line
     */
    private void checkRoute(boolean odd, WayToCheck wayToCheck, List<WayToCheck> waysToCheck,
                            Set<Station> resultStations, Set<RouteLine> resultLines) {

        StationWay way = wayToCheck.getWay();
        LocalTime timeNow = wayToCheck.getTimeNow();

        // TODO: consider the interval for bus and others
        if (StationType.values()[way.getStation().getStationType()] == StationType.UNDERGROUND) {
            int undInterval = getUndergroundInterval(timeNow);

            // if last train will be sooner then the interval, state that we will use it
            if (ChronoUnit.MINUTES.between(getLastTrain(way, odd), timeNow) < undInterval) {
                timeNow = getLastTrain(way, odd);
            }
            else {
                // stating that we have to wait for maximum interval
                timeNow.plusMinutes(undInterval);
            }
        }

        do {
            boolean found = false;
            for (StationWay w : markedWays) {
                if (w.getId() == way.getId()) {
                    // if we have already reached this earlier, there is no need to check next ones
                    if (w.getReachTime().isBefore(timeNow)) {
                        return;
                    }
                    else {
                        w.setReachTime(timeNow); // flag when we have reached it
                    }
                    found = true;
                    break;
                }
            }

            if (!found) {
                way.setReachTime(timeNow);
                markedWays.add(way);
                resultStations.add(way.getStation()); // adding to reached ways
            }

            List<StationWay> transfers = transferService.getTransferStationWays(way);
            // checking transfers
            if (transfers != null) {
                for (StationWay transfer : transfers) {

                    if (StationType.values()[way.getStation().getStationType()] == StationType.UNDERGROUND &&
                            StationType.values()[transfer.getStation().getStationType()] == StationType.UNDERGROUND) {
                        // if both station ways are underground, check transfer closing time
                        if (timeNow.isAfter(underTransClos)) {
                            continue; // we are late to move to that route
                        }
                    }

                    // TODO: consider adding this line
                    // adding line
                    resultLines.add(new RouteLine(new PairStation(way.getStation(), transfer.getStation()), true));

                    // adding transfer to check the new route. incrementing the time with transfer interval
                    // TODO: consider const interval
                    waysToCheck.add(new WayToCheck(transfer, timeNow.plusMinutes(transferInterval)));
                }
            }

            StationWay next = way.getNext();

            // if there next station exists
            if (next != null) {

                // TODO: consider this moment
                // counting the interval between station ways using last transport units
                long intervalToNext = ChronoUnit.MINUTES.between(getLastTrain(next, odd), getLastTrain(way, odd));

                // TODO: consider adding this line
                // adding line
                resultLines.add(new RouteLine(new PairStation(way.getStation(), next.getStation()), false));

                // checking the ability to go next
                LocalTime nextTime = timeNow.plusMinutes(intervalToNext);
                // TODO: consider checking first
                if (! (nextTime.isAfter(getLastTrain(next, odd)) && nextTime.isBefore(getFirstTrain(next, odd)))) {
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

    private List<StationWay> marked = new ArrayList<>();

    /***
     * Gets all lines
     */
    public Result getAllLines(Station station) {

        Set<Station> resultStations = new HashSet<>();
        Set<RouteLine> resultLines = new HashSet<>();

        List<StationWay> starts = stationWayService.getStationWays(station);
        if (starts == null || starts.size() == 0) {
            return null; // no ways
        }

        List<StationWay> waysToCheck = new ArrayList<>();

        // adding start points
        for (StationWay way : starts) {
            waysToCheck.add(way);
        }


        while (waysToCheck.size() > 0) { // check routes while they exist
            StationWay way = waysToCheck.get(0);
            makeRoute(way, waysToCheck, resultStations, resultLines);
            waysToCheck.remove(0);
            if (marked.size() > 500) break;
        }

        marked.clear();
        return new Result(resultStations, resultLines);
    }

    /***
     * Make all route
     */
    private void makeRoute(StationWay stationWay, List<StationWay> waysToCheck,
                                   Set<Station> resultStations, Set<RouteLine> resultLines) {

        StationWay way = stationWay;

        do {

            for (StationWay w : marked) {
                if (w.getId() == way.getId()) {
                    return;
                }
            }



           /*if (marked.size() == 12) {
                return;
            }*/

            marked.add(way); // flag when we have reached it
            resultStations.add(way.getStation()); // adding to reached ways

            boolean a = lol(marked);

            List<StationWay> transfers = transferService.getTransferStationWays(way);
            // checking transfers
            if (transfers != null) {
                for (StationWay transfer : transfers) {

                    // TODO: consider adding this line
                    // adding line
                    resultLines.add(new RouteLine(new PairStation(way.getStation(), transfer.getStation()), true));

                    // adding way
                    waysToCheck.add(transfer);
                }
            }

            StationWay next = way.getNext();

            // if there next station exists
            if (next != null) {
                // adding line
                resultLines.add(new RouteLine(new PairStation(way.getStation(), next.getStation()), false));
                way = next;
            }

            else {
                way = null; // no next
            }
        } while (way != null);
    }

    private LocalTime getLastTrain(StationWay stationWay, boolean odd) {
        return odd? stationWay.getFirstTrainOdd() : stationWay.getLastTrainEven();
    }

    private LocalTime getFirstTrain(StationWay stationWay, boolean odd) {
        return odd? stationWay.getFirstTrainOdd() : stationWay.getLastTrainEven();
    }

    private boolean lol(List<StationWay> e) {
        Set set = new HashSet(e);
        if (set.size() != e.size()) {
            return true;
        }
        return false;
    }

}

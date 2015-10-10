package ru.pihta.nocturnaltransport.controller;

import javassist.tools.web.BadHttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.pihta.nocturnaltransport.model.Algorithms;
import ru.pihta.nocturnaltransport.model.Service.StationService;
import ru.pihta.nocturnaltransport.model.Service.StationWayService;
import ru.pihta.nocturnaltransport.model.Station;
import ru.pihta.nocturnaltransport.model.StationWay;
import ru.pihta.nocturnaltransport.model.structures.Result;
import ru.pihta.nocturnaltransport.model.structures.RouteLine;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@RestController
public class StationController {

    @Autowired
    StationWayService stationWayService;

    @Autowired
    StationService stationService;

    @Autowired
    Algorithms algorithms;

    @RequestMapping(value = "/stations",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Station> getAllStations() {
        return stationService.getAll();
    }

    @RequestMapping(value = "/lines", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<RouteLine> getAllLines() {
        Result result = algorithms.getAllLines(stationService.getById(3));
        return result.getRouteLines();
    }

    @RequestMapping(value = "/activeLines", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<RouteLine> getActiveLines(@RequestParam("stationId") int stationId,
                                         @RequestParam("hours") int hours,
                                         @RequestParam("minutes") int minutes) throws BadHttpRequest{
        if (hours < 0 || hours > 23) {
            throw new BadHttpRequest(new IllegalArgumentException("Wrong number of hours: " + hours));
        }

        if (minutes < 0 || minutes > 59) {
            throw new BadHttpRequest(new IllegalArgumentException("Wrong number of minutes: " + minutes));
        }

        boolean odd = LocalDate.now().getDayOfMonth() % 2 == 1;
        Station station = stationService.getById(stationId);
        LocalTime time = LocalTime.of(hours, minutes);
        return algorithms.getRouteLines(odd, station, time).getRouteLines();
    }
}
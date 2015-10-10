package ru.pihta.nocturnaltransport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.pihta.nocturnaltransport.model.Service.StationWayService;
import ru.pihta.nocturnaltransport.model.StationWay;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    StationWayService stationWayService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String test() {
        return stationWayService.getById(0).getFirstTrainEven().toString();
    }
}

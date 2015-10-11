package ru.pihta.nocturnaltransport.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.pihta.nocturnaltransport.model.StationWay;

import java.time.LocalTime;

@RestController
@RequestMapping("/getTime")
public class TimeController {

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String test() {
        return LocalTime.now().toString();
    }
}

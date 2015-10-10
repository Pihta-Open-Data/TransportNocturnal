package ru.pihta.nocturnaltransport.model;

import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Created by Lev on 10.10.2015.
 */
public class StationWay {

    private int id;

    private Station station;

    private StationWay next;

    private LocalTime lastTrainOdd;

    private LocalTime lastTrainEven;

    private LocalTime firstTrainOdd;

    private LocalTime firstTrainEven;

    private ArrayList<StationWay> transfers;


}

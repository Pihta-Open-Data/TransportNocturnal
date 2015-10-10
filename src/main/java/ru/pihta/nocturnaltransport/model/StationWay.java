package ru.pihta.nocturnaltransport.model;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.ArrayList;

@Entity
@Table(name = "StationWay")
public class StationWay {

    @Id
    @Column(name = "ID")
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name = "StationID")
    private Station station;

    @OneToOne
    @JoinColumn(name = "Next")
    private StationWay next;

    @Temporal(TemporalType.TIME)
    @Column(name = "LastTrainOdd")
    private LocalTime lastTrainOdd;

    @Temporal(TemporalType.TIME)
    @Column(name = "LastTrainEven")
    private LocalTime lastTrainEven;

    @Temporal(TemporalType.TIME)
    @Column(name = "FirstTrainOdd")
    private LocalTime firstTrainOdd;

    @Temporal(TemporalType.TIME)
    @Column(name = "FirstTrainEven")
    private LocalTime firstTrainEven;

    @OneToMany
    @JoinColumn(name = "Transfers")
    private ArrayList<StationWay> transfers;

    public int getId() {
        return id;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public StationWay getNext() {
        return next;
    }

    public void setNext(StationWay next) {
        this.next = next;
    }

    public LocalTime getLastTrainOdd() {
        return lastTrainOdd;
    }

    public void setLastTrainOdd(LocalTime lastTrainOdd) {
        this.lastTrainOdd = lastTrainOdd;
    }

    public LocalTime getLastTrainEven() {
        return lastTrainEven;
    }

    public void setLastTrainEven(LocalTime lastTrainEven) {
        this.lastTrainEven = lastTrainEven;
    }

    public LocalTime getFirstTrainOdd() {
        return firstTrainOdd;
    }

    public void setFirstTrainOdd(LocalTime firstTrainOdd) {
        this.firstTrainOdd = firstTrainOdd;
    }

    public LocalTime getFirstTrainEven() {
        return firstTrainEven;
    }

    public void setFirstTrainEven(LocalTime firstTrainEven) {
        this.firstTrainEven = firstTrainEven;
    }

    public ArrayList<StationWay> getTransfers() {
        return transfers;
    }

    public void setTransfers(ArrayList<StationWay> transfers) {
        this.transfers = transfers;
    }

}

package ru.pihta.nocturnaltransport.model;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "station_way")
public class StationWay {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;

//    @ManyToOne
//    @JoinColumn(name = "StationID")
//    private Station station;

    @OneToOne
    @JoinColumn(name = "next_way")
    private StationWay next;

    @Convert(converter = LocalTimePersistenceConverter.class)
    @Column(name = "last_train_odd")
    private LocalTime lastTrainOdd;

    @Convert(converter = LocalTimePersistenceConverter.class)
    @Column(name = "last_train_even")
    private LocalTime lastTrainEven;

    @Convert(converter = LocalTimePersistenceConverter.class)
    @Column(name = "first_train_odd")
    private LocalTime firstTrainOdd;

    @Convert(converter = LocalTimePersistenceConverter.class)
    @Column(name = "first_train_even")
    private LocalTime firstTrainEven;

    @OneToMany
    @JoinColumn(name = "transfers")
    private List<StationWay> transfers;

    public int getId() {
        return id;
    }

//    public Station getStation() {
//        return station;
//    }
//
//    public void setStation(Station station) {
//        this.station = station;
//    }

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

    public List<StationWay> getTransfers() {
        return transfers;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTransfers(List<StationWay> transfers) {
        this.transfers = transfers;
    }

}

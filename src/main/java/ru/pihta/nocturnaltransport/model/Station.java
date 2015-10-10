package ru.pihta.nocturnaltransport.model;

import ru.pihta.nocturnaltransport.model.structures.RouteLine;
import ru.pihta.nocturnaltransport.model.structures.StationType;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "station")
public class Station {

    @Id
    @GeneratedValue
    @Column
    private int id;

    @Column
    private double latitude;

    @Column
    private double longitude;

    @Column(name = "open_time")
    @Convert(converter = LocalTimePersistenceConverter.class)
    private LocalTime openTime;

    @Column(name = "close_time")
    @Convert(converter = LocalTimePersistenceConverter.class)
    private LocalTime closeTime;

    @Column(name = "type_id")
    private int stationType;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public LocalTime getOpenTime() {
        return openTime;
    }

    public void setOpenTime(LocalTime openTime) {
        this.openTime = openTime;
    }

    public LocalTime getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(LocalTime closeTime) {
        this.closeTime = closeTime;
    }

    public int getStationType() {
        return stationType;
    }

    public void setStationType(int stationType) {
        this.stationType = stationType;
    }
}

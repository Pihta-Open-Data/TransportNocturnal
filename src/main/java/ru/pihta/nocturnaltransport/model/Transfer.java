package ru.pihta.nocturnaltransport.model;

import javax.persistence.*;

@Entity
@Table(name = "transfer")
public class Transfer {

    @Id
    @GeneratedValue
    @Column
    private int id;

    @ManyToOne
    @JoinColumn(name = "start_id")
    private StationWay start;

    @ManyToOne
    @JoinColumn(name = "finish_id")
    private StationWay finish;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public StationWay getStart() {
        return start;
    }

    public void setStart(StationWay start) {
        this.start = start;
    }

    public StationWay getFinish() {
        return finish;
    }

    public void setFinish(StationWay finish) {
        this.finish = finish;
    }
}

package ru.pihta.nocturnaltransport.model.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.pihta.nocturnaltransport.model.Station;
import ru.pihta.nocturnaltransport.model.StationWay;

@Repository
public class StationWayDAO {


    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public void create(StationWay stationWay) {
        getSession().save(stationWay);
    }

    public void update(StationWay stationWay) {
        getSession().update(stationWay);
    }

    public void delete(StationWay stationWay) {
        getSession().delete(stationWay);
    }

    public void getByStation(Station station) {
        return  getSession().createCriteria(StationWay.class).add(Restrictions.eq("StationId", station.getId()));
    }
}

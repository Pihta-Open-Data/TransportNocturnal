package ru.pihta.nocturnaltransport.model.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.pihta.nocturnaltransport.model.Station;
import ru.pihta.nocturnaltransport.model.StationWay;
import ru.pihta.nocturnaltransport.model.structures.PairStationWay;

import java.util.List;

@Repository
@Transactional
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

    }

    public StationWay getById(int id) {
        return (StationWay) getSession().createCriteria(StationWay.class).add(Restrictions.eq("id", id)).uniqueResult();
    }

    public List<StationWay> getAll() {
        return getSession().createCriteria(StationWay.class).list();
    }

    public List<StationWay> getStationWays(Station station) {
        return getSession().createCriteria(StationWay.class).add(Restrictions.eq("id", station.getId())).list();
    }
}

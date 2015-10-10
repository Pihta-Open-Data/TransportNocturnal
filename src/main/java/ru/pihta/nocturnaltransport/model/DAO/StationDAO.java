package ru.pihta.nocturnaltransport.model.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.pihta.nocturnaltransport.model.Station;
import ru.pihta.nocturnaltransport.model.structures.PairStationWay;

import java.util.List;

@Repository
public class StationDAO {

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public Station getById(int id) {
        return (Station) getSession().createCriteria(Station.class).add(Restrictions.eq("id", id)).uniqueResult();
    }

    public List<Station> getAll() {
        return getSession().createCriteria(Station.class).list();
    }

}

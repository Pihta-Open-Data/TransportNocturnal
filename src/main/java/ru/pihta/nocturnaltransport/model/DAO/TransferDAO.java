package ru.pihta.nocturnaltransport.model.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.pihta.nocturnaltransport.model.StationWay;
import ru.pihta.nocturnaltransport.model.Transfer;

import java.util.List;

@Repository
@Transactional
public class TransferDAO {

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public List<Transfer> getStationWayTransfers(StationWay stationWay) {
        return getSession().createCriteria(Transfer.class)
                .add(Restrictions
                        .or(Restrictions.eq("startId", stationWay.getId()),
                                Restrictions.eq("finishId", stationWay.getId()))).list();
    }
}

package ru.pihta.nocturnaltransport.model.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pihta.nocturnaltransport.model.DAO.StationWayDAO;
import ru.pihta.nocturnaltransport.model.StationWay;

import java.util.List;

@Service
@Transactional
public class StationWayServiceImpl implements StationWayService {

    @Autowired
    private StationWayDAO stationWayDAO;

    @Override
    public StationWay getById(int id) {
        return stationWayDAO.getById(id);
    }

    @Override
    public List<StationWay> getAll() {
        return stationWayDAO.getAll();
    }
}

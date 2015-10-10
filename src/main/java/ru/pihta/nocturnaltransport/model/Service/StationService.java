package ru.pihta.nocturnaltransport.model.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pihta.nocturnaltransport.model.DAO.StationDAO;
import ru.pihta.nocturnaltransport.model.Station;
import ru.pihta.nocturnaltransport.model.structures.PairStationWay;

import java.util.List;

@Service
public class StationService {

    @Autowired
    StationDAO stationDAO;

    public Station getById(int id) {
        return stationDAO.getById(id);
    }

    public List<Station> getAll() {
        return stationDAO.getAll();
    }
}

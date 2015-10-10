package ru.pihta.nocturnaltransport.model.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pihta.nocturnaltransport.model.DAO.TransferDAO;
import ru.pihta.nocturnaltransport.model.StationWay;
import ru.pihta.nocturnaltransport.model.Transfer;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransferService {

    @Autowired
    private TransferDAO transferDAO;

    public List<StationWay> getTransferStationWays(StationWay stationWay) {
        List<Transfer> transfers = transferDAO.getStationWayTransfers(stationWay);
        List<StationWay> result = new ArrayList<>();
        for(Transfer transfer : transfers) {
            if (transfer.getStart().getId() == stationWay.getId()) {
                result.add(transfer.getFinish());
            } else {
                result.add(transfer.getStart());
            }
        }

        return result;
    }
}

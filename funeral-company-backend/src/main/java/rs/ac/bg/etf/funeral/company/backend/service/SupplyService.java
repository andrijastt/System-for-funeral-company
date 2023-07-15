package rs.ac.bg.etf.funeral.company.backend.service;

import org.springframework.stereotype.Service;
import rs.ac.bg.etf.funeral.company.backend.entity.MaterialSupply;
import rs.ac.bg.etf.funeral.company.backend.entity.Supply;

import java.util.List;

public interface SupplyService {

    List<Supply> getAllSupplies();
    Supply saveSupply(Supply supply);
    Supply updateSupply(Supply supply);
    Supply supplyArrived(Long supplyID);
    String removeSupply(Long supplyID);
    Supply updateMaterialSupply(List<MaterialSupply> materialSupplyList, Long supplyID);
    List<Supply> searchSupply(String name);
}

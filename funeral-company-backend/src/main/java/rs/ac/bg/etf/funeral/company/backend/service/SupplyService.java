package rs.ac.bg.etf.funeral.company.backend.service;

import org.springframework.stereotype.Service;
import rs.ac.bg.etf.funeral.company.backend.entity.Supply;

import java.util.List;

public interface SupplyService {

    List<Supply> getAllSupplies();

    Supply saveSupply(Supply supply);
}

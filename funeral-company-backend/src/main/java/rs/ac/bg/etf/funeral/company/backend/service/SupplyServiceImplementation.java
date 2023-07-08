package rs.ac.bg.etf.funeral.company.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.bg.etf.funeral.company.backend.entity.Supply;
import rs.ac.bg.etf.funeral.company.backend.repository.SupplyRepository;

import java.util.List;

@Service
public class SupplyServiceImplementation implements SupplyService{

    @Autowired
    private SupplyRepository supplyRepository;

    @Override
    public List<Supply> getAllSupplies() {
        return supplyRepository.findAll();
    }
}

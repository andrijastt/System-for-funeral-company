package rs.ac.bg.etf.funeral.company.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.bg.etf.funeral.company.backend.entity.MaterialSupply;
import rs.ac.bg.etf.funeral.company.backend.entity.MaterialSupplyPK;
import rs.ac.bg.etf.funeral.company.backend.entity.Supply;
import rs.ac.bg.etf.funeral.company.backend.repository.MaterialSupplyRepository;
import rs.ac.bg.etf.funeral.company.backend.repository.SupplyRepository;

import java.util.Date;
import java.util.List;

@Service
public class SupplyServiceImplementation implements SupplyService{

    @Autowired
    private SupplyRepository supplyRepository;

    @Autowired
    private MaterialSupplyRepository materialSupplyRepository;

    @Override
    public List<Supply> getAllSupplies() {
        return supplyRepository.findAll();
    }

    @Override
    public Supply saveSupply(Supply supply) {
        supply.setDateOrdered(new Date());

        List<MaterialSupply> listTemp = supply.getMaterialSupplyList();
        supply.setMaterialSupplyList(null);
        supplyRepository.saveAndFlush(supply);

        for(MaterialSupply materialSupply: listTemp){
            materialSupply.getMaterialSupplyPK().setSupplyID(supply.getSupplyID());
            materialSupplyRepository.save(materialSupply);
        }

        supply.setMaterialSupplyList(listTemp);

        return supplyRepository.save(supply);
    }
}

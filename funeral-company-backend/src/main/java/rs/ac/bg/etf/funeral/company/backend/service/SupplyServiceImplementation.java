package rs.ac.bg.etf.funeral.company.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.bg.etf.funeral.company.backend.entity.Material;
import rs.ac.bg.etf.funeral.company.backend.entity.MaterialSupply;
import rs.ac.bg.etf.funeral.company.backend.entity.MaterialSupplyPK;
import rs.ac.bg.etf.funeral.company.backend.entity.Supply;
import rs.ac.bg.etf.funeral.company.backend.repository.MaterialRepository;
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

    @Autowired
    private MaterialRepository materialRepository;

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

    @Override
    public Supply supplyArrived(Long supplyID) {
        Supply supply = supplyRepository.findById(supplyID).get();
        supply.setDateArrived(new Date());

        for(MaterialSupply ms: supply.getMaterialSupplyList()){
            Material material = materialRepository.findById(ms.getMaterialSupplyPK().getMaterialID()).get();
            material.setCount(material.getCount() + ms.getAmount());
            materialRepository.save(material);
        }

        return supplyRepository.save(supply);
    }

    @Override
    public String removeSupply(Long supplyID) {
        Supply supply = supplyRepository.findById(supplyID).get();
        List<MaterialSupply> msList = materialSupplyRepository.findByMaterialSupplyPK_SupplyID((supplyID));
        supply.setMaterialSupplyList(null);
        for(MaterialSupply ms: msList){
            materialSupplyRepository.delete(ms);
        }
        supplyRepository.delete(supply);
        return "Successfully deleted!";
    }

    @Override
    public Supply updateSupply(Supply supply) {
        System.out.println("supply = " + supply);
        Supply supplyDB = supplyRepository.findById(supply.getSupplyID()).get();
        if(!supply.getName().equals("")) supplyDB.setName(supply.getName());
        supplyDB.setDateOrdered(supply.getDateOrdered());
        return supplyRepository.save(supplyDB);
    }

    @Override
    public Supply updateMaterialSupply(List<MaterialSupply> materialSupplyList) {

        List<MaterialSupply> msListDB = materialSupplyRepository.findByMaterialSupplyPK_SupplyID(
                materialSupplyList.get(0).getMaterialSupplyPK().getSupplyID());

        Supply supplyDB = supplyRepository.findById(materialSupplyList.get(0).getMaterialSupplyPK().getSupplyID()).get();
        supplyDB.setMaterialSupplyList(null);
        supplyRepository.saveAndFlush(supplyDB);

        for(MaterialSupply ms: msListDB){
            materialSupplyRepository.delete(ms);
        }

        for(MaterialSupply ms: materialSupplyList){
            materialSupplyRepository.save(ms);
        }
        supplyDB.setMaterialSupplyList(materialSupplyList);
        return supplyRepository.save(supplyDB);
    }
}

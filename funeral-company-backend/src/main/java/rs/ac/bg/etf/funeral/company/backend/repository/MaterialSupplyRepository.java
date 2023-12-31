package rs.ac.bg.etf.funeral.company.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.bg.etf.funeral.company.backend.entity.MaterialSupply;
import rs.ac.bg.etf.funeral.company.backend.entity.MaterialSupplyPK;

import java.util.List;

@Repository
public interface MaterialSupplyRepository extends JpaRepository<MaterialSupply, MaterialSupplyPK> {

    List<MaterialSupply> findByMaterialSupplyPK_SupplyID(Long supplyID);
    List<MaterialSupply> findByMaterialSupplyPK_MaterialID(Long materialID);
    MaterialSupply findByMaterialSupplyPK_MaterialIDAndMaterialSupplyPK_SupplyID(Long materialID, Long supplyID);
}

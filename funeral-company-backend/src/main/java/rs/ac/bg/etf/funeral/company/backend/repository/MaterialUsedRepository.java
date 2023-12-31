package rs.ac.bg.etf.funeral.company.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.bg.etf.funeral.company.backend.entity.MaterialSupply;
import rs.ac.bg.etf.funeral.company.backend.entity.MaterialUsed;
import rs.ac.bg.etf.funeral.company.backend.entity.MaterialUsedPK;

import java.util.List;

@Repository
public interface MaterialUsedRepository extends JpaRepository<MaterialUsed, MaterialUsedPK> {

    List<MaterialUsed> findByMaterialUsedPK_ProductID(Long productID);
    List<MaterialUsed> findByMaterialUsedPK_MaterialID(Long materialID);
}

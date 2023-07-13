package rs.ac.bg.etf.funeral.company.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.bg.etf.funeral.company.backend.entity.MaterialUsed;
import rs.ac.bg.etf.funeral.company.backend.entity.MaterialUsedPK;

@Repository
public interface MaterialUsedRepository extends JpaRepository<MaterialUsed, MaterialUsedPK> {
}

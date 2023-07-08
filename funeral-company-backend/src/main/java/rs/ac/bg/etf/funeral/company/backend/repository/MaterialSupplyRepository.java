package rs.ac.bg.etf.funeral.company.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.bg.etf.funeral.company.backend.entity.MaterialSupply;

@Repository
public interface MaterialSupplyRepository extends JpaRepository<MaterialSupply, Long> {
}

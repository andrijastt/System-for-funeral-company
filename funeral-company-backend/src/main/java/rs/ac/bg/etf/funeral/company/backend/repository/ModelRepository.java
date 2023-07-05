package rs.ac.bg.etf.funeral.company.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.bg.etf.funeral.company.backend.entity.Model;

import java.util.List;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long>{

    List<Model> findByNameContaining(String name);
}

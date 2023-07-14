package rs.ac.bg.etf.funeral.company.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.bg.etf.funeral.company.backend.entity.Item;
import rs.ac.bg.etf.funeral.company.backend.entity.ItemPK;

@Repository
public interface ItemRepository extends JpaRepository<Item, ItemPK> {
}

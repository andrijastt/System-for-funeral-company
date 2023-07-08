package rs.ac.bg.etf.funeral.company.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import rs.ac.bg.etf.funeral.company.backend.entity.Client;
import rs.ac.bg.etf.funeral.company.backend.entity.Contract;

import java.util.List;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {

    public List<Contract> findByValid(boolean valid);
    public List<Contract> findByClient(Client client);

    @Query("SELECT c.currency, sum (c.money) from Contract c where c.client = ?1 group by c.currency")
    public List<Object[]> sumMoneyOfClient(Client client);
//    @Query("SELECT c.currency from Contract c where c.client = ?1 group by c.currency")
//    public List<String> currencyOfClient(Client client);
}

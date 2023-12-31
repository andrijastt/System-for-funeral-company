package rs.ac.bg.etf.funeral.company.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.bg.etf.funeral.company.backend.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByUsernameAndPassword(String username, String password);

}

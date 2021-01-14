package pe.com.jdmm21.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.com.jdmm21.springsecurity.model.UserCredentials;

@Repository
public interface UserRepository extends JpaRepository<UserCredentials, String> {
    UserCredentials findByUsername(String username);

}

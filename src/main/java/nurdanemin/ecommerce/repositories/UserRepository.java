package nurdanemin.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import nurdanemin.ecommerce.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    boolean existsById(Long id);
    User findByCartId(Long cartId);


}

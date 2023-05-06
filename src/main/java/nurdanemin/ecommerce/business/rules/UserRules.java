package nurdanemin.ecommerce.business.rules;

import lombok.AllArgsConstructor;
import nurdanemin.ecommerce.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserRules {
    private final UserRepository repository;

    public void checkIfEmailExists(String email){
        if (repository.existsByEmail(email)){
            throw new RuntimeException("EMAIL_ALREADY_EXISTS");
        }
    }

    public void checkIfExistsById(Long id){
        if (!repository.existsById(id)){
            throw new RuntimeException("NOT_EXISTS_BY_ID");
        }
    }
}

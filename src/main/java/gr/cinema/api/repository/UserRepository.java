package gr.cinema.api.repository;

import gr.cinema.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByFirstName(String firstName);
    User findByEmailAndPassword(String email, String password);
   // User findByFirstName(String firstName);
    Optional<User> findByEmail(String email);
    //Optional<User> findByUsernameOrEmail(String username, String email);
    User findByUsernameAndPassword(String username, String password);
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

}


package org.elis.eventsmanager.repository;

import org.elis.eventsmanager.model.Role;
import org.elis.eventsmanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

  /*  @Query(nativeQuery = true, value = "SELECT * FROM User WHERE email = :email AND password = :password")
    Optional<User> loginWithNativeQuery(String username, String password);

    @Query("SELECT u FROM User u WHERE u.email = :email AND u.password = :password")
    Optional<User> loginWithJPQL(String email, String password);
*/
    //questi metodi per la generazione di query non sono più usati con spring

    Optional<User> findByEmailAndPasswordAndBlockedIsNotNull(String email, String password);
    List<User> findAllByRole(Role role);
    Optional<User> findByEmailAndPassword(String email, String password);
    Optional<User> findById(long id);
    Optional<User> findByEmail(String email);
}

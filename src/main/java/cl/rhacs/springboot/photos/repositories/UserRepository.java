package cl.rhacs.springboot.photos.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.rhacs.springboot.photos.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Searches the repository for the specified email address
     *
     * @param email the email address to find
     * @return the user details if exists
     */
    public Optional<User> findByEmail(String email);

    /**
     * Searches the repository for the specified username
     *
     * @param username the username to find
     * @return the user details if exists
     */
    public Optional<User> findByUsername(String username);

}

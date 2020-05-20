package cl.rhacs.springboot.photos.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.rhacs.springboot.photos.models.Snapper;

@Repository
public interface SnapperRepository extends JpaRepository<Snapper, Long> {

    /**
     * Searches the repository for the specified email address
     *
     * @param email the email address to find
     * @return the user details if exists
     */
    public Optional<Snapper> findByEmail(String email);

    /**
     * Searches the repository for the specified username
     *
     * @param username the username to find
     * @return the user details if exists
     */
    public Optional<Snapper> findByUsername(String username);

}

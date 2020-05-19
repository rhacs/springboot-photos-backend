package cl.rhacs.springboot.photos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.rhacs.springboot.photos.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}

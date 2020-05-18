package cl.rhacs.springboot.photos.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.rhacs.springboot.photos.models.Photo;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {

    /**
     * Searches the repository using the title of the photo
     *
     * @param title the title to look for
     * @return a list of possible candidates
     */
    public List<Photo> findByTitle(String title);

    /**
     * Searches the repository using the photo url
     *
     * @param url the url to look for
     * @return the photo details
     */
    public Optional<Photo> findByUrl(String url);

}

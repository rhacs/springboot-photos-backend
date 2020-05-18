package cl.rhacs.springboot.photos.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import cl.rhacs.springboot.photos.exceptions.PhotoNotFoundException;
import cl.rhacs.springboot.photos.models.Photo;
import cl.rhacs.springboot.photos.repositories.PhotoRepository;

@RestController
@RequestMapping(path = "/photos")
public class PhotoController {

    // Attributes
    // -----------------------------------------------------------------------------------------

    @Autowired
    private PhotoRepository photoRepository;

    // Get Mappings
    // -----------------------------------------------------------------------------------------

    /**
     * Shows a list of {@link Photo}s stored in the database
     *
     * @return All instances of the type photo
     */
    @GetMapping(path = "/")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<List<Photo>> findAllPhotos() {
        return new ResponseEntity<>(photoRepository.findAll(), HttpStatus.OK);
    }

    /**
     * Shows the detail of the specified {@link Photo}
     *
     * @param id the photo id
     * @return the details of the photo
     * @throws PhotoNotFoundException when the photo does not exists
     */
    @GetMapping(path = "/{id:^\\d+$}")
    public ResponseEntity<Photo> findPhotoById(@PathVariable Long id) throws PhotoNotFoundException {
        Optional<Photo> photo = photoRepository.findById(id);

        if (!photo.isPresent()) {
            throw new PhotoNotFoundException(String.format("Photo with id %d was not found", id));
        }

        return new ResponseEntity<>(photo.get(), HttpStatus.OK);
    }

    // Post Mappings
    // -----------------------------------------------------------------------------------------

    /**
     * Adds a new Photo to the repository
     *
     * @param photo the photo to add
     * @return the saved photo
     */
    @PostMapping(path = "/")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<Photo> addPhoto(@RequestBody @Valid final Photo photo) {
        final Photo savedPhoto = photoRepository.save(photo);
        return new ResponseEntity<>(savedPhoto, HttpStatus.CREATED);
    }

    // Put Mappings
    // -----------------------------------------------------------------------------------------

    // TODO

    // Delete Mappings
    // -----------------------------------------------------------------------------------------

    // TODO

}

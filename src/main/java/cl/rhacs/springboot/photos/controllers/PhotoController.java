package cl.rhacs.springboot.photos.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import cl.rhacs.springboot.photos.exceptions.ContentNotFoundException;
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
     * Shows a list of {@link Photo}s stored in the database, but paginated :O
     *
     * @param page the solicited page
     * @param size the number of items per page
     *
     * @return All the instances of the type photo, but paginated
     *
     * @throws ContentNotFoundException  when the repository is empty
     * @throws IndexOutOfBoundsException when the user enters a page value larger
     *                                   than the max value
     * @throws IllegalArgumentException  when an unkown sorting order is selected
     */
    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<Map<String, Object>> getAllPhotos(@RequestParam(defaultValue = "0") final int page,
            @RequestParam(defaultValue = "10") final int size,
            @RequestParam(defaultValue = "photoId") final String sortBy,
            @RequestParam(defaultValue = "asc") final String sortOrder)
            throws ContentNotFoundException, IndexOutOfBoundsException, IllegalArgumentException {
        List<Photo> photos = new ArrayList<>();
        Sort sort = Sort.by(sortBy);

        if (!sortOrder.equals("asc") && !sortOrder.equals("desc")) {
            throw new IllegalArgumentException(String.format(
                    "'%s' is an unknown sorting order. Possible values: 'asc' for ascending order and 'desc' for descending order.",
                    sortOrder));
        } else if (sortOrder.equals("desc")) {
            sort = sort.descending();
        }

        final Pageable pageable = PageRequest.of(page, size, sort);
        final Page<Photo> pagePhotos = photoRepository.findAll(pageable);
        photos = pagePhotos.getContent();

        if (page > pagePhotos.getTotalPages() - 1) {
            throw new IndexOutOfBoundsException(
                    String.format("Page value out of bounds, max page = %d", pagePhotos.getTotalPages() - 1));
        }

        if (photos.isEmpty()) {
            throw new ContentNotFoundException("The repository is empty");
        }

        final Map<String, Object> response = new HashMap<>();
        response.put("photos", photos);
        response.put("currentPage", pagePhotos.getNumber());
        response.put("totalItems", pagePhotos.getTotalElements());
        response.put("totalPages", pagePhotos.getTotalPages());

        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * Shows the detail of the specified {@link Photo}
     *
     * @param id the photo id
     * @return the details of the photo
     * @throws PhotoNotFoundException when the photo does not exists
     */
    @GetMapping(path = "/{id:^\\d+$}")
    public ResponseEntity<Photo> findPhotoById(@PathVariable final Long id) throws PhotoNotFoundException {
        final Optional<Photo> photo = photoRepository.findById(id);

        if (!photo.isPresent()) {
            throw new PhotoNotFoundException(String.format("Photo with id %d was not found", id));
        }

        final Photo foundPhoto = photo.get();
        foundPhoto.setViews(foundPhoto.getViews() + 1);
        photoRepository.save(foundPhoto);

        return new ResponseEntity<>(foundPhoto, new HttpHeaders(), HttpStatus.OK);
    }

    // Post Mappings
    // -----------------------------------------------------------------------------------------

    /**
     * Adds a new Photo to the repository
     *
     * @param photo the photo to add
     * @return the saved photo
     */
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<Photo> addPhoto(@RequestBody @Valid final Photo photo) {
        final Photo savedPhoto = photoRepository.save(photo);
        return new ResponseEntity<>(savedPhoto, new HttpHeaders(), HttpStatus.CREATED);
    }

    // Put Mappings
    // -----------------------------------------------------------------------------------------

    // TODO

    // Patch Mappings
    // -----------------------------------------------------------------------------------------

    // TODO

    // Delete Mappings
    // -----------------------------------------------------------------------------------------

    // TODO

}

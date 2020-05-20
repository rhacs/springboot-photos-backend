package cl.rhacs.springboot.photos.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import cl.rhacs.springboot.photos.exceptions.SnapperNotFoundException;
import cl.rhacs.springboot.photos.models.Snapper;
import cl.rhacs.springboot.photos.repositories.SnapperRepository;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping(path = "/snappers")
public class SnapperController {

    // Attributes
    // -----------------------------------------------------------------------------------------

    @Autowired
    private SnapperRepository snapperRepository;

    // Get Mappings
    // -----------------------------------------------------------------------------------------

    /**
     * Shows the list of {@link Snapper}s inside the repository
     *
     * @return a {@code ResponseEntity} that contains a list of {@code Snapper}s
     */
    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<List<Snapper>> getAllSnappers() {
        return ResponseEntity.ok(snapperRepository.findAll());
    }

    /**
     * Retrieves the information of the specified {@link Snapper} id
     *
     * @param id the {@code Snapper} id
     * @return a {@code ResponseEntity} that contains the {@code Snapper}
     *         information
     * @throws SnapperNotFoundException when the {@code Snapper} id does not match a
     *                                  record on the repository
     */
    @GetMapping(path = "/{id:^\\d+$}")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<Snapper> getSnapperById(@PathVariable Long id) throws SnapperNotFoundException {
        Snapper snapper = snapperRepository.findById(id)
                .orElseThrow(() -> new SnapperNotFoundException("Snapper not found for this id :: " + id));
        return ResponseEntity.ok(snapper);
    }

    // Post Mappings
    // -----------------------------------------------------------------------------------------

    /**
     * Adds a {@link Snapper} to the repository
     *
     * @param userDetails the {@code Snapper} details
     * @return a {@code ResponseEntity} instance with the created {@code Snapper}
     */
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<Snapper> addSnapper(@RequestBody @Valid Snapper snapper) {
        Snapper savedSnapper = snapperRepository.save(snapper);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSnapper);
    }

    // TODO: Put Mappings
    // -----------------------------------------------------------------------------------------

    // TODO: Delete Mappings
    // -----------------------------------------------------------------------------------------

}

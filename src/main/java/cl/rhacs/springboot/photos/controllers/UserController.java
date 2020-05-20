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

import cl.rhacs.springboot.photos.exceptions.UserNotFoundException;
import cl.rhacs.springboot.photos.models.User;
import cl.rhacs.springboot.photos.repositories.UserRepository;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    // Attributes
    // -----------------------------------------------------------------------------------------

    @Autowired
    private UserRepository userRepository;

    // Get Mappings
    // -----------------------------------------------------------------------------------------

    /**
     * Shows the list of {@link User}s inside the repository
     *
     * @return a {@code ResponseEntity} that contains a list of {@code User}s
     */
    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    /**
     * Retrieves the information of the specified {@link User} id
     *
     * @param id the {@code User} id
     * @return a {@code ResponseEntity} that contains the {@code User} information
     * @throws UserNotFoundException when the {@code User} id does not match a
     *                               record on the repository
     */
    @GetMapping(path = "/{id:^\\d+$}")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<User> getUserById(@PathVariable Long id) throws UserNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found for this id :: " + id));
        return ResponseEntity.ok(user);
    }

    // Post Mappings
    // -----------------------------------------------------------------------------------------

    /**
     * Adds a {@link User} to the repository
     *
     * @param userDetails the {@code User} details
     * @return a {@code ResponseEntity} instance with the created {@code User}
     */
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<User> addUser(@RequestBody @Valid User userDetails) {
        User user = userRepository.save(userDetails);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    // TODO: Put Mappings
    // -----------------------------------------------------------------------------------------

    // TODO: Delete Mappings
    // -----------------------------------------------------------------------------------------

}

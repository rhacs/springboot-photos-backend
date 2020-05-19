package cl.rhacs.springboot.photos.models;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "users")
public class User {

    // Attributes
    // -----------------------------------------------------------------------------------------

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long userId;

    @NotNull
    @NotBlank
    @NotEmpty
    @Size(min = 5, max = 30)
    @Column(name = "username", nullable = false, unique = true, updatable = false)
    private String username;

    @NotNull
    @NotBlank
    @NotEmpty
    @Email
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @JsonIgnore
    @OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "user", targetEntity = Photo.class)
    private Set<Photo> photos;

    @JsonIgnore
    @NotNull
    @NotBlank
    @NotEmpty
    @Column(name = "password", nullable = false)
    private String password;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date createdAt;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date updatedAt;

    // Constructors
    // -----------------------------------------------------------------------------------------

    /**
     * Creates a new and empty {@link User}
     */
    public User() {

    }

    /**
     * Creates a new {@link User} given a username, an email address and a password
     *
     * @param username the username to set
     * @param email    the email address
     * @param password the password
     */
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    // Getters
    // -----------------------------------------------------------------------------------------

    /**
     * @return the userId
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @return the photos
     */
    public Set<Photo> getPhotos() {
        return photos;
    }

    // Setters
    // -----------------------------------------------------------------------------------------

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @param photos the photos to set
     */
    public void setPhotos(Set<Photo> photos) {
        this.photos = photos;
    }

    // Inheritances (Object)
    // -----------------------------------------------------------------------------------------

    @Override
    public String toString() {
        return "User [userId= " + userId + ", username=" + username + ", email=" + email + ", photos=" + photos
                + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
    }

}

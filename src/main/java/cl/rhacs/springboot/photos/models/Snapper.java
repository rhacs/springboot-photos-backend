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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "snappers")
public class Snapper {

    // Attributes
    // -----------------------------------------------------------------------------------------

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "snapper_id")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long snapperId;

    @NotNull
    @NotBlank
    @Size(min = 5, max = 30)
    @Pattern(regexp = "^(?!.*?[_-]{2})[A-z][A-z0-9_-]+$")
    @Column(name = "username", nullable = false, unique = true, updatable = false)
    private String username;

    @NotNull
    @NotBlank
    @Email
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotNull
    @NotBlank
    @Column(name = "password", nullable = false)
    @Size(min = 8)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Size(min = 10)
    @Column(columnDefinition = "tinytext default null", name = "biography")
    private String biography;

    @JsonIgnore
    @OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "snapper", targetEntity = Photo.class)
    private Set<Photo> photos;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date createdAt;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date updatedAt;

    // Constructors
    // -----------------------------------------------------------------------------------------

    /**
     * Creates a new and empty {@link Snapper}
     */
    public Snapper() {

    }

    /**
     * Creates a new {@link Snapper} given a username, an email address and a
     * password
     *
     * @param username the username to set
     * @param email    the email address
     * @param password the password
     */
    public Snapper(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    // Getters
    // -----------------------------------------------------------------------------------------

    /**
     * @return the snapperId
     */
    public Long getSnapperId() {
        return snapperId;
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
     * @return the biography
     */
    public String getBiography() {
        return biography;
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
     * @param biography the biography to set
     */
    public void setBiography(String biography) {
        this.biography = biography;
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
        return "Snapper [snapperId= " + snapperId + ", username=" + username + ", email=" + email + ", biography="
                + biography + ", photos=" + photos + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
    }

}

package cl.rhacs.springboot.photos.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.URL;

@Entity
@Table(name = "photos")
public class Photo {

    // Attributes
    // -----------------------------------------------------------------------------------------

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "photo_id")
    private Long photoId;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 50)
    @Column(columnDefinition = "varchar(50) not null", name = "title", nullable = false)
    private String title;

    @Column(columnDefinition = "text default null", name = "description")
    private String description;

    @NotNull
    @NotBlank
    @URL
    @Column(name = "url", nullable = false, unique = true, updatable = false)
    private String url;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(columnDefinition = "bigint default 0", name = "views")
    private Long views;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Date updatedAt;

    // Constructors
    // -----------------------------------------------------------------------------------------

    /**
     * Creates a new and empty {@link Photo}
     */
    public Photo() {
        views = 0L;
    }

    /**
     * Creates a new {@link Photo} given a title, a description and a url
     *
     * @param title       the title
     * @param description the description
     * @param url         the url of the image
     */
    public Photo(final String title, final String description, final String url) {
        this();
        this.title = title;
        this.description = description;
        this.url = url;
    }

    // Getters
    // -----------------------------------------------------------------------------------------

    /**
     * @return the photoId
     */
    public Long getPhotoId() {
        return photoId;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @return the views
     */
    public Long getViews() {
        return views;
    }

    /**
     * @return the createdAt
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * @return the updatedAt
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    // Setters
    // -----------------------------------------------------------------------------------------

    /**
     * @param title the title to set
     */
    public void setTitle(final String title) {
        this.title = title;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(final String url) {
        this.url = url;
    }

    /**
     * @param views the views to set
     */
    public void setViews(final Long views) {
        this.views = views;
    }

    // Inheritances (Object)
    // -----------------------------------------------------------------------------------------

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;

        result = prime * result + ((photoId == null) ? 0 : photoId.hashCode());
        result = prime * result + ((url == null) ? 0 : url.hashCode());

        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        final Photo other = (Photo) obj;

        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (getClass() != obj.getClass())
            return false;

        if (photoId == null) {
            if (other.photoId != null)
                return false;
        } else if (!photoId.equals(other.photoId))
            return false;

        if (url == null) {
            if (other.url != null)
                return false;
        } else if (!url.equals(other.url))
            return false;

        return true;
    }

    @Override
    public String toString() {
        return "Photo [photoId=" + photoId + ", title=" + title + ", description=" + description + ", url=" + url
                + ", views=" + views + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
    }

}

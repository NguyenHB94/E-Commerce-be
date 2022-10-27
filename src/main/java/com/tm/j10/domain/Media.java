package com.tm.j10.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Media.
 */
@Entity
@Table(name = "media")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Media implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Size(max = 40)
    @Column(name = "media_name", length = 40)
    private String mediaName;

    @Size(max = 50)
    @Column(name = "media_type", length = 50)
    private String mediaType;

    @Size(max = 200)
    @Column(name = "media_url", length = 200)
    private String mediaURL;

    @Size(max = 500)
    @Column(name = "media_description", length = 500)
    private String mediaDescription;

    @Size(max = 500)
    @Column(name = "media_alt", length = 500)
    private String mediaAlt;

    @Size(max = 500)
    @Column(name = "media_caption", length = 500)
    private String mediaCaption;

    @Size(max = 20)
    @Column(name = "upload_year", length = 20)
    private String uploadYear;

    @Size(max = 20)
    @Column(name = "upload_month", length = 20)
    private String uploadMonth;

    @ManyToMany(mappedBy = "media")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "category", "media", "tags", "storages" }, allowSetters = true)
    private Set<Product> products = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Media id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMediaName() {
        return this.mediaName;
    }

    public Media mediaName(String mediaName) {
        this.setMediaName(mediaName);
        return this;
    }

    public void setMediaName(String mediaName) {
        this.mediaName = mediaName;
    }

    public String getMediaType() {
        return this.mediaType;
    }

    public Media mediaType(String mediaType) {
        this.setMediaType(mediaType);
        return this;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getMediaURL() {
        return this.mediaURL;
    }

    public Media mediaURL(String mediaURL) {
        this.setMediaURL(mediaURL);
        return this;
    }

    public void setMediaURL(String mediaURL) {
        this.mediaURL = mediaURL;
    }

    public String getMediaDescription() {
        return this.mediaDescription;
    }

    public Media mediaDescription(String mediaDescription) {
        this.setMediaDescription(mediaDescription);
        return this;
    }

    public void setMediaDescription(String mediaDescription) {
        this.mediaDescription = mediaDescription;
    }

    public String getMediaAlt() {
        return this.mediaAlt;
    }

    public Media mediaAlt(String mediaAlt) {
        this.setMediaAlt(mediaAlt);
        return this;
    }

    public void setMediaAlt(String mediaAlt) {
        this.mediaAlt = mediaAlt;
    }

    public String getMediaCaption() {
        return this.mediaCaption;
    }

    public Media mediaCaption(String mediaCaption) {
        this.setMediaCaption(mediaCaption);
        return this;
    }

    public void setMediaCaption(String mediaCaption) {
        this.mediaCaption = mediaCaption;
    }

    public String getUploadYear() {
        return this.uploadYear;
    }

    public Media uploadYear(String uploadYear) {
        this.setUploadYear(uploadYear);
        return this;
    }

    public void setUploadYear(String uploadYear) {
        this.uploadYear = uploadYear;
    }

    public String getUploadMonth() {
        return this.uploadMonth;
    }

    public Media uploadMonth(String uploadMonth) {
        this.setUploadMonth(uploadMonth);
        return this;
    }

    public void setUploadMonth(String uploadMonth) {
        this.uploadMonth = uploadMonth;
    }

    public Set<Product> getProducts() {
        return this.products;
    }

    public void setProducts(Set<Product> products) {
        if (this.products != null) {
            this.products.forEach(i -> i.removeMedia(this));
        }
        if (products != null) {
            products.forEach(i -> i.addMedia(this));
        }
        this.products = products;
    }

    public Media products(Set<Product> products) {
        this.setProducts(products);
        return this;
    }

    public Media addProduct(Product product) {
        this.products.add(product);
        product.getMedia().add(this);
        return this;
    }

    public Media removeProduct(Product product) {
        this.products.remove(product);
        product.getMedia().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Media)) {
            return false;
        }
        return id != null && id.equals(((Media) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Media{" +
            "id=" + getId() +
            ", mediaName='" + getMediaName() + "'" +
            ", mediaType='" + getMediaType() + "'" +
            ", mediaURL='" + getMediaURL() + "'" +
            ", mediaDescription='" + getMediaDescription() + "'" +
            ", mediaAlt='" + getMediaAlt() + "'" +
            ", mediaCaption='" + getMediaCaption() + "'" +
            ", uploadYear='" + getUploadYear() + "'" +
            ", uploadMonth='" + getUploadMonth() + "'" +
            "}";
    }
}

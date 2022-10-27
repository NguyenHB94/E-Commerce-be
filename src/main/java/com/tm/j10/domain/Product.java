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
 * A Product.
 */
@Entity
@Table(name = "product")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Size(max = 300)
    @Column(name = "name", length = 300)
    private String name;

    @Size(max = 100)
    @Column(name = "product_code", length = 100)
    private String productCode;

    @Size(max = 200)
    @Column(name = "product_sku", length = 200)
    private String productSKU;

    @NotNull
    @Min(value = 1L)
    @Max(value = 100000000L)
    @Column(name = "price", nullable = false)
    private Long price;

    @NotNull
    @Min(value = 1L)
    @Max(value = 100000000L)
    @Column(name = "final_price", nullable = false)
    private Long finalPrice;

    @Column(name = "release_date_unix")
    private Long releaseDateUnix;

    @Size(max = 200)
    @Column(name = "release_type", length = 200)
    private String releaseType;

    @Size(max = 300)
    @Column(name = "designer", length = 300)
    private String designer;

    @Size(max = 1000)
    @Column(name = "description", length = 1000)
    private String description;

    @Size(max = 100)
    @Column(name = "model_height", length = 100)
    private String modelHeight;

    @Size(max = 100)
    @Column(name = "model_weight", length = 100)
    private String modelWeight;

    @Size(max = 300)
    @Column(name = "material", length = 300)
    private String material;

    @Size(max = 300)
    @Column(name = "slug", length = 300, unique = true)
    private String slug;

    @Column(name = "is_valid")
    private Boolean isValid;

    @Column(name = "is_enable")
    private Boolean isEnable;

    @Column(name = "status")
    private String status;

    @Size(max = 200)
    @Column(name = "created_by", length = 200)
    private String createdBy;

    @Column(name = "created_date")
    private Long createdDate;

    @Size(max = 200)
    @Column(name = "modified_by", length = 200)
    private String modifiedBy;

    @Column(name = "modified_date")
    private Long modifiedDate;

    @ManyToOne
    private Category category;

    @ManyToMany
    @JoinTable(
        name = "rel_product__media",
        joinColumns = @JoinColumn(name = "product_id"),
        inverseJoinColumns = @JoinColumn(name = "media_id")
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "products" }, allowSetters = true)
    private Set<Media> media = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "rel_product__tag", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "products", "news" }, allowSetters = true)
    private Set<Tag> tags = new HashSet<>();

    @OneToMany(mappedBy = "product")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "product", "productSize", "color", "store", "orderDesc" }, allowSetters = true)
    private Set<Storage> storages = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Product id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public Product name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductCode() {
        return this.productCode;
    }

    public Product productCode(String productCode) {
        this.setProductCode(productCode);
        return this;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductSKU() {
        return this.productSKU;
    }

    public Product productSKU(String productSKU) {
        this.setProductSKU(productSKU);
        return this;
    }

    public void setProductSKU(String productSKU) {
        this.productSKU = productSKU;
    }

    public Long getPrice() {
        return this.price;
    }

    public Product price(Long price) {
        this.setPrice(price);
        return this;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getFinalPrice() {
        return this.finalPrice;
    }

    public Product finalPrice(Long finalPrice) {
        this.setFinalPrice(finalPrice);
        return this;
    }

    public void setFinalPrice(Long finalPrice) {
        this.finalPrice = finalPrice;
    }

    public Long getReleaseDateUnix() {
        return this.releaseDateUnix;
    }

    public Product releaseDateUnix(Long releaseDateUnix) {
        this.setReleaseDateUnix(releaseDateUnix);
        return this;
    }

    public void setReleaseDateUnix(Long releaseDateUnix) {
        this.releaseDateUnix = releaseDateUnix;
    }

    public String getReleaseType() {
        return this.releaseType;
    }

    public Product releaseType(String releaseType) {
        this.setReleaseType(releaseType);
        return this;
    }

    public void setReleaseType(String releaseType) {
        this.releaseType = releaseType;
    }

    public String getDesigner() {
        return this.designer;
    }

    public Product designer(String designer) {
        this.setDesigner(designer);
        return this;
    }

    public void setDesigner(String designer) {
        this.designer = designer;
    }

    public String getDescription() {
        return this.description;
    }

    public Product description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getModelHeight() {
        return this.modelHeight;
    }

    public Product modelHeight(String modelHeight) {
        this.setModelHeight(modelHeight);
        return this;
    }

    public void setModelHeight(String modelHeight) {
        this.modelHeight = modelHeight;
    }

    public String getModelWeight() {
        return this.modelWeight;
    }

    public Product modelWeight(String modelWeight) {
        this.setModelWeight(modelWeight);
        return this;
    }

    public void setModelWeight(String modelWeight) {
        this.modelWeight = modelWeight;
    }

    public String getMaterial() {
        return this.material;
    }

    public Product material(String material) {
        this.setMaterial(material);
        return this;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getSlug() {
        return this.slug;
    }

    public Product slug(String slug) {
        this.setSlug(slug);
        return this;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Boolean getIsValid() {
        return this.isValid;
    }

    public Product isValid(Boolean isValid) {
        this.setIsValid(isValid);
        return this;
    }

    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }

    public Boolean getIsEnable() {
        return this.isEnable;
    }

    public Product isEnable(Boolean isEnable) {
        this.setIsEnable(isEnable);
        return this;
    }

    public void setIsEnable(Boolean isEnable) {
        this.isEnable = isEnable;
    }

    public String getStatus() {
        return this.status;
    }

    public Product status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public Product createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Long getCreatedDate() {
        return this.createdDate;
    }

    public Product createdDate(Long createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedBy() {
        return this.modifiedBy;
    }

    public Product modifiedBy(String modifiedBy) {
        this.setModifiedBy(modifiedBy);
        return this;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Long getModifiedDate() {
        return this.modifiedDate;
    }

    public Product modifiedDate(Long modifiedDate) {
        this.setModifiedDate(modifiedDate);
        return this;
    }

    public void setModifiedDate(Long modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Product category(Category category) {
        this.setCategory(category);
        return this;
    }

    public Set<Media> getMedia() {
        return this.media;
    }

    public void setMedia(Set<Media> media) {
        this.media = media;
    }

    public Product media(Set<Media> media) {
        this.setMedia(media);
        return this;
    }

    public Product addMedia(Media media) {
        this.media.add(media);
        media.getProducts().add(this);
        return this;
    }

    public Product removeMedia(Media media) {
        this.media.remove(media);
        media.getProducts().remove(this);
        return this;
    }

    public Set<Tag> getTags() {
        return this.tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public Product tags(Set<Tag> tags) {
        this.setTags(tags);
        return this;
    }

    public Product addTag(Tag tag) {
        this.tags.add(tag);
        tag.getProducts().add(this);
        return this;
    }

    public Product removeTag(Tag tag) {
        this.tags.remove(tag);
        tag.getProducts().remove(this);
        return this;
    }

    public Set<Storage> getStorages() {
        return this.storages;
    }

    public void setStorages(Set<Storage> storages) {
        if (this.storages != null) {
            this.storages.forEach(i -> i.setProduct(null));
        }
        if (storages != null) {
            storages.forEach(i -> i.setProduct(this));
        }
        this.storages = storages;
    }

    public Product storages(Set<Storage> storages) {
        this.setStorages(storages);
        return this;
    }

    public Product addStorage(Storage storage) {
        this.storages.add(storage);
        storage.setProduct(this);
        return this;
    }

    public Product removeStorage(Storage storage) {
        this.storages.remove(storage);
        storage.setProduct(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Product)) {
            return false;
        }
        return id != null && id.equals(((Product) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Product{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", productCode='" + getProductCode() + "'" +
            ", productSKU='" + getProductSKU() + "'" +
            ", price=" + getPrice() +
            ", finalPrice=" + getFinalPrice() +
            ", releaseDateUnix=" + getReleaseDateUnix() +
            ", releaseType='" + getReleaseType() + "'" +
            ", designer='" + getDesigner() + "'" +
            ", description='" + getDescription() + "'" +
            ", modelHeight='" + getModelHeight() + "'" +
            ", modelWeight='" + getModelWeight() + "'" +
            ", material='" + getMaterial() + "'" +
            ", slug='" + getSlug() + "'" +
            ", isValid='" + getIsValid() + "'" +
            ", isEnable='" + getIsEnable() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate=" + getCreatedDate() +
            ", modifiedBy='" + getModifiedBy() + "'" +
            ", modifiedDate=" + getModifiedDate() +
            "}";
    }
}

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
 * A ShopNew.
 */
@Entity
@Table(name = "shop_new")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ShopNew implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Size(max = 500)
    @Column(name = "new_title", length = 500)
    private String newTitle;

    @Size(max = 3000)
    @Column(name = "new_content", length = 3000)
    private String newContent;

    @Size(max = 300)
    @Column(name = "new_type", length = 300)
    private String newType;

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

    @ManyToMany
    @JoinTable(
        name = "rel_shop_new__tag",
        joinColumns = @JoinColumn(name = "shop_new_id"),
        inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "products", "news" }, allowSetters = true)
    private Set<Tag> tags = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ShopNew id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNewTitle() {
        return this.newTitle;
    }

    public ShopNew newTitle(String newTitle) {
        this.setNewTitle(newTitle);
        return this;
    }

    public void setNewTitle(String newTitle) {
        this.newTitle = newTitle;
    }

    public String getNewContent() {
        return this.newContent;
    }

    public ShopNew newContent(String newContent) {
        this.setNewContent(newContent);
        return this;
    }

    public void setNewContent(String newContent) {
        this.newContent = newContent;
    }

    public String getNewType() {
        return this.newType;
    }

    public ShopNew newType(String newType) {
        this.setNewType(newType);
        return this;
    }

    public void setNewType(String newType) {
        this.newType = newType;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public ShopNew createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Long getCreatedDate() {
        return this.createdDate;
    }

    public ShopNew createdDate(Long createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedBy() {
        return this.modifiedBy;
    }

    public ShopNew modifiedBy(String modifiedBy) {
        this.setModifiedBy(modifiedBy);
        return this;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Long getModifiedDate() {
        return this.modifiedDate;
    }

    public ShopNew modifiedDate(Long modifiedDate) {
        this.setModifiedDate(modifiedDate);
        return this;
    }

    public void setModifiedDate(Long modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Set<Tag> getTags() {
        return this.tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public ShopNew tags(Set<Tag> tags) {
        this.setTags(tags);
        return this;
    }

    public ShopNew addTag(Tag tag) {
        this.tags.add(tag);
        tag.getNews().add(this);
        return this;
    }

    public ShopNew removeTag(Tag tag) {
        this.tags.remove(tag);
        tag.getNews().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ShopNew)) {
            return false;
        }
        return id != null && id.equals(((ShopNew) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ShopNew{" +
            "id=" + getId() +
            ", newTitle='" + getNewTitle() + "'" +
            ", newContent='" + getNewContent() + "'" +
            ", newType='" + getNewType() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate=" + getCreatedDate() +
            ", modifiedBy='" + getModifiedBy() + "'" +
            ", modifiedDate=" + getModifiedDate() +
            "}";
    }
}

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
 * A ProductSize.
 */
@Entity
@Table(name = "product_size")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ProductSize implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Size(max = 200)
    @Column(name = "size_name", length = 200)
    private String sizeName;

    @Size(max = 500)
    @Column(name = "description", length = 500)
    private String description;

    @OneToMany(mappedBy = "productSize")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "product", "productSize", "color", "store", "orderDesc" }, allowSetters = true)
    private Set<Storage> storages = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ProductSize id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSizeName() {
        return this.sizeName;
    }

    public ProductSize sizeName(String sizeName) {
        this.setSizeName(sizeName);
        return this;
    }

    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }

    public String getDescription() {
        return this.description;
    }

    public ProductSize description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Storage> getStorages() {
        return this.storages;
    }

    public void setStorages(Set<Storage> storages) {
        if (this.storages != null) {
            this.storages.forEach(i -> i.setProductSize(null));
        }
        if (storages != null) {
            storages.forEach(i -> i.setProductSize(this));
        }
        this.storages = storages;
    }

    public ProductSize storages(Set<Storage> storages) {
        this.setStorages(storages);
        return this;
    }

    public ProductSize addStorage(Storage storage) {
        this.storages.add(storage);
        storage.setProductSize(this);
        return this;
    }

    public ProductSize removeStorage(Storage storage) {
        this.storages.remove(storage);
        storage.setProductSize(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProductSize)) {
            return false;
        }
        return id != null && id.equals(((ProductSize) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProductSize{" +
            "id=" + getId() +
            ", sizeName='" + getSizeName() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}

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
 * A Color.
 */
@Entity
@Table(name = "color")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Color implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Size(max = 300)
    @Column(name = "color_name", length = 300)
    private String colorName;

    @Size(max = 500)
    @Column(name = "description", length = 500)
    private String description;

    @Size(max = 20)
    @Column(name = "hex_value", length = 20)
    private String hexValue;

    @OneToMany(mappedBy = "color")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "product", "productSize", "color", "store", "orderDesc" }, allowSetters = true)
    private Set<Storage> storages = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Color id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getColorName() {
        return this.colorName;
    }

    public Color colorName(String colorName) {
        this.setColorName(colorName);
        return this;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public String getDescription() {
        return this.description;
    }

    public Color description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHexValue() {
        return this.hexValue;
    }

    public Color hexValue(String hexValue) {
        this.setHexValue(hexValue);
        return this;
    }

    public void setHexValue(String hexValue) {
        this.hexValue = hexValue;
    }

    public Set<Storage> getStorages() {
        return this.storages;
    }

    public void setStorages(Set<Storage> storages) {
        if (this.storages != null) {
            this.storages.forEach(i -> i.setColor(null));
        }
        if (storages != null) {
            storages.forEach(i -> i.setColor(this));
        }
        this.storages = storages;
    }

    public Color storages(Set<Storage> storages) {
        this.setStorages(storages);
        return this;
    }

    public Color addStorage(Storage storage) {
        this.storages.add(storage);
        storage.setColor(this);
        return this;
    }

    public Color removeStorage(Storage storage) {
        this.storages.remove(storage);
        storage.setColor(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Color)) {
            return false;
        }
        return id != null && id.equals(((Color) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Color{" +
            "id=" + getId() +
            ", colorName='" + getColorName() + "'" +
            ", description='" + getDescription() + "'" +
            ", hexValue='" + getHexValue() + "'" +
            "}";
    }
}

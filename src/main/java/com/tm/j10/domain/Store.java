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
 * A Store.
 */
@Entity
@Table(name = "store")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Store implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Size(max = 300)
    @Column(name = "store_name", length = 300)
    private String storeName;

    @Size(max = 500)
    @Column(name = "address", length = 500)
    private String address;

    @Size(max = 100)
    @Column(name = "tel", length = 100)
    private String tel;

    @Size(max = 300)
    @Column(name = "email", length = 300)
    private String email;

    @Size(max = 1000)
    @Column(name = "description", length = 1000)
    private String description;

    @Size(max = 100)
    @Column(name = "working_hour", length = 100)
    private String workingHour;

    @Size(max = 100)
    @Column(name = "manager", length = 100)
    private String manager;

    @OneToMany(mappedBy = "store")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "product", "productSize", "color", "store", "orderDesc" }, allowSetters = true)
    private Set<Storage> storages = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Store id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStoreName() {
        return this.storeName;
    }

    public Store storeName(String storeName) {
        this.setStoreName(storeName);
        return this;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getAddress() {
        return this.address;
    }

    public Store address(String address) {
        this.setAddress(address);
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return this.tel;
    }

    public Store tel(String tel) {
        this.setTel(tel);
        return this;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return this.email;
    }

    public Store email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return this.description;
    }

    public Store description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWorkingHour() {
        return this.workingHour;
    }

    public Store workingHour(String workingHour) {
        this.setWorkingHour(workingHour);
        return this;
    }

    public void setWorkingHour(String workingHour) {
        this.workingHour = workingHour;
    }

    public String getManager() {
        return this.manager;
    }

    public Store manager(String manager) {
        this.setManager(manager);
        return this;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public Set<Storage> getStorages() {
        return this.storages;
    }

    public void setStorages(Set<Storage> storages) {
        if (this.storages != null) {
            this.storages.forEach(i -> i.setStore(null));
        }
        if (storages != null) {
            storages.forEach(i -> i.setStore(this));
        }
        this.storages = storages;
    }

    public Store storages(Set<Storage> storages) {
        this.setStorages(storages);
        return this;
    }

    public Store addStorage(Storage storage) {
        this.storages.add(storage);
        storage.setStore(this);
        return this;
    }

    public Store removeStorage(Storage storage) {
        this.storages.remove(storage);
        storage.setStore(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Store)) {
            return false;
        }
        return id != null && id.equals(((Store) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Store{" +
            "id=" + getId() +
            ", storeName='" + getStoreName() + "'" +
            ", address='" + getAddress() + "'" +
            ", tel='" + getTel() + "'" +
            ", email='" + getEmail() + "'" +
            ", description='" + getDescription() + "'" +
            ", workingHour='" + getWorkingHour() + "'" +
            ", manager='" + getManager() + "'" +
            "}";
    }
}

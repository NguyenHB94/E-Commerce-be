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
 * A Ward.
 */
@Entity
@Table(name = "ward")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Ward implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Size(max = 200)
    @Column(name = "ward_name", length = 200)
    private String wardName;

    @Size(max = 100)
    @Column(name = "ward_type", length = 100)
    private String wardType;

    @ManyToOne
    @JsonIgnoreProperties(value = { "wards", "province", "customers", "shopOrders" }, allowSetters = true)
    private District district;

    @OneToMany(mappedBy = "ward")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "user", "shopOrders", "province", "district", "ward" }, allowSetters = true)
    private Set<Customer> customers = new HashSet<>();

    @OneToMany(mappedBy = "ward")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "orderDescs", "province", "district", "ward", "customer" }, allowSetters = true)
    private Set<ShopOrder> shopOrders = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Ward id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWardName() {
        return this.wardName;
    }

    public Ward wardName(String wardName) {
        this.setWardName(wardName);
        return this;
    }

    public void setWardName(String wardName) {
        this.wardName = wardName;
    }

    public String getWardType() {
        return this.wardType;
    }

    public Ward wardType(String wardType) {
        this.setWardType(wardType);
        return this;
    }

    public void setWardType(String wardType) {
        this.wardType = wardType;
    }

    public District getDistrict() {
        return this.district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public Ward district(District district) {
        this.setDistrict(district);
        return this;
    }

    public Set<Customer> getCustomers() {
        return this.customers;
    }

    public void setCustomers(Set<Customer> customers) {
        if (this.customers != null) {
            this.customers.forEach(i -> i.setWard(null));
        }
        if (customers != null) {
            customers.forEach(i -> i.setWard(this));
        }
        this.customers = customers;
    }

    public Ward customers(Set<Customer> customers) {
        this.setCustomers(customers);
        return this;
    }

    public Ward addCustomer(Customer customer) {
        this.customers.add(customer);
        customer.setWard(this);
        return this;
    }

    public Ward removeCustomer(Customer customer) {
        this.customers.remove(customer);
        customer.setWard(null);
        return this;
    }

    public Set<ShopOrder> getShopOrders() {
        return this.shopOrders;
    }

    public void setShopOrders(Set<ShopOrder> shopOrders) {
        if (this.shopOrders != null) {
            this.shopOrders.forEach(i -> i.setWard(null));
        }
        if (shopOrders != null) {
            shopOrders.forEach(i -> i.setWard(this));
        }
        this.shopOrders = shopOrders;
    }

    public Ward shopOrders(Set<ShopOrder> shopOrders) {
        this.setShopOrders(shopOrders);
        return this;
    }

    public Ward addShopOrder(ShopOrder shopOrder) {
        this.shopOrders.add(shopOrder);
        shopOrder.setWard(this);
        return this;
    }

    public Ward removeShopOrder(ShopOrder shopOrder) {
        this.shopOrders.remove(shopOrder);
        shopOrder.setWard(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Ward)) {
            return false;
        }
        return id != null && id.equals(((Ward) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Ward{" +
            "id=" + getId() +
            ", wardName='" + getWardName() + "'" +
            ", wardType='" + getWardType() + "'" +
            "}";
    }
}

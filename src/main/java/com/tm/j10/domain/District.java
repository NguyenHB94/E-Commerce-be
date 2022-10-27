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
 * A District.
 */
@Entity
@Table(name = "district")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class District implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Size(max = 200)
    @Column(name = "district_name", length = 200)
    private String districtName;

    @Size(max = 100)
    @Column(name = "district_type", length = 100)
    private String districtType;

    @OneToMany(mappedBy = "district")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "district", "customers", "shopOrders" }, allowSetters = true)
    private Set<Ward> wards = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = { "districts", "customers", "shopOrders" }, allowSetters = true)
    private Province province;

    @OneToMany(mappedBy = "district")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "user", "shopOrders", "province", "district", "ward" }, allowSetters = true)
    private Set<Customer> customers = new HashSet<>();

    @OneToMany(mappedBy = "district")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "orderDescs", "province", "district", "ward", "customer" }, allowSetters = true)
    private Set<ShopOrder> shopOrders = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public District id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDistrictName() {
        return this.districtName;
    }

    public District districtName(String districtName) {
        this.setDistrictName(districtName);
        return this;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getDistrictType() {
        return this.districtType;
    }

    public District districtType(String districtType) {
        this.setDistrictType(districtType);
        return this;
    }

    public void setDistrictType(String districtType) {
        this.districtType = districtType;
    }

    public Set<Ward> getWards() {
        return this.wards;
    }

    public void setWards(Set<Ward> wards) {
        if (this.wards != null) {
            this.wards.forEach(i -> i.setDistrict(null));
        }
        if (wards != null) {
            wards.forEach(i -> i.setDistrict(this));
        }
        this.wards = wards;
    }

    public District wards(Set<Ward> wards) {
        this.setWards(wards);
        return this;
    }

    public District addWard(Ward ward) {
        this.wards.add(ward);
        ward.setDistrict(this);
        return this;
    }

    public District removeWard(Ward ward) {
        this.wards.remove(ward);
        ward.setDistrict(null);
        return this;
    }

    public Province getProvince() {
        return this.province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public District province(Province province) {
        this.setProvince(province);
        return this;
    }

    public Set<Customer> getCustomers() {
        return this.customers;
    }

    public void setCustomers(Set<Customer> customers) {
        if (this.customers != null) {
            this.customers.forEach(i -> i.setDistrict(null));
        }
        if (customers != null) {
            customers.forEach(i -> i.setDistrict(this));
        }
        this.customers = customers;
    }

    public District customers(Set<Customer> customers) {
        this.setCustomers(customers);
        return this;
    }

    public District addCustomer(Customer customer) {
        this.customers.add(customer);
        customer.setDistrict(this);
        return this;
    }

    public District removeCustomer(Customer customer) {
        this.customers.remove(customer);
        customer.setDistrict(null);
        return this;
    }

    public Set<ShopOrder> getShopOrders() {
        return this.shopOrders;
    }

    public void setShopOrders(Set<ShopOrder> shopOrders) {
        if (this.shopOrders != null) {
            this.shopOrders.forEach(i -> i.setDistrict(null));
        }
        if (shopOrders != null) {
            shopOrders.forEach(i -> i.setDistrict(this));
        }
        this.shopOrders = shopOrders;
    }

    public District shopOrders(Set<ShopOrder> shopOrders) {
        this.setShopOrders(shopOrders);
        return this;
    }

    public District addShopOrder(ShopOrder shopOrder) {
        this.shopOrders.add(shopOrder);
        shopOrder.setDistrict(this);
        return this;
    }

    public District removeShopOrder(ShopOrder shopOrder) {
        this.shopOrders.remove(shopOrder);
        shopOrder.setDistrict(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof District)) {
            return false;
        }
        return id != null && id.equals(((District) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "District{" +
            "id=" + getId() +
            ", districtName='" + getDistrictName() + "'" +
            ", districtType='" + getDistrictType() + "'" +
            "}";
    }
}

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
 * A Province.
 */
@Entity
@Table(name = "province")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Province implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Size(max = 200)
    @Column(name = "province_name", length = 200)
    private String provinceName;

    @Size(max = 100)
    @Column(name = "province_type", length = 100)
    private String provinceType;

    @OneToMany(mappedBy = "province")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "wards", "province", "customers", "shopOrders" }, allowSetters = true)
    private Set<District> districts = new HashSet<>();

    @OneToMany(mappedBy = "province")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "user", "shopOrders", "province", "district", "ward" }, allowSetters = true)
    private Set<Customer> customers = new HashSet<>();

    @OneToMany(mappedBy = "province")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "orderDescs", "province", "district", "ward", "customer" }, allowSetters = true)
    private Set<ShopOrder> shopOrders = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Province id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProvinceName() {
        return this.provinceName;
    }

    public Province provinceName(String provinceName) {
        this.setProvinceName(provinceName);
        return this;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getProvinceType() {
        return this.provinceType;
    }

    public Province provinceType(String provinceType) {
        this.setProvinceType(provinceType);
        return this;
    }

    public void setProvinceType(String provinceType) {
        this.provinceType = provinceType;
    }

    public Set<District> getDistricts() {
        return this.districts;
    }

    public void setDistricts(Set<District> districts) {
        if (this.districts != null) {
            this.districts.forEach(i -> i.setProvince(null));
        }
        if (districts != null) {
            districts.forEach(i -> i.setProvince(this));
        }
        this.districts = districts;
    }

    public Province districts(Set<District> districts) {
        this.setDistricts(districts);
        return this;
    }

    public Province addDistrict(District district) {
        this.districts.add(district);
        district.setProvince(this);
        return this;
    }

    public Province removeDistrict(District district) {
        this.districts.remove(district);
        district.setProvince(null);
        return this;
    }

    public Set<Customer> getCustomers() {
        return this.customers;
    }

    public void setCustomers(Set<Customer> customers) {
        if (this.customers != null) {
            this.customers.forEach(i -> i.setProvince(null));
        }
        if (customers != null) {
            customers.forEach(i -> i.setProvince(this));
        }
        this.customers = customers;
    }

    public Province customers(Set<Customer> customers) {
        this.setCustomers(customers);
        return this;
    }

    public Province addCustomer(Customer customer) {
        this.customers.add(customer);
        customer.setProvince(this);
        return this;
    }

    public Province removeCustomer(Customer customer) {
        this.customers.remove(customer);
        customer.setProvince(null);
        return this;
    }

    public Set<ShopOrder> getShopOrders() {
        return this.shopOrders;
    }

    public void setShopOrders(Set<ShopOrder> shopOrders) {
        if (this.shopOrders != null) {
            this.shopOrders.forEach(i -> i.setProvince(null));
        }
        if (shopOrders != null) {
            shopOrders.forEach(i -> i.setProvince(this));
        }
        this.shopOrders = shopOrders;
    }

    public Province shopOrders(Set<ShopOrder> shopOrders) {
        this.setShopOrders(shopOrders);
        return this;
    }

    public Province addShopOrder(ShopOrder shopOrder) {
        this.shopOrders.add(shopOrder);
        shopOrder.setProvince(this);
        return this;
    }

    public Province removeShopOrder(ShopOrder shopOrder) {
        this.shopOrders.remove(shopOrder);
        shopOrder.setProvince(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Province)) {
            return false;
        }
        return id != null && id.equals(((Province) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Province{" +
            "id=" + getId() +
            ", provinceName='" + getProvinceName() + "'" +
            ", provinceType='" + getProvinceType() + "'" +
            "}";
    }
}

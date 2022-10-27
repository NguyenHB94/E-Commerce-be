package com.tm.j10.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Customer.
 */
@Entity
@Table(name = "customer")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Data
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Size(max = 50)
    @Column(name = "customer_code", length = 50)
    private String customerCode;

    @Size(max = 300)
    @Column(name = "customer_name", length = 300)
    private String customerName;

    @Size(max = 100)
    @Column(name = "tel", length = 100)
    private String tel;

    @Size(max = 300)
    @Column(name = "email", length = 300)
    private String email;

    @OneToOne
    @JoinColumn(unique = true)
    private User user;

    @OneToMany(mappedBy = "customer")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "orderDescs", "province", "district", "ward", "customer" }, allowSetters = true)
    private Set<ShopOrder> shopOrders = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = { "districts", "customers", "shopOrders" }, allowSetters = true)
    private Province province;

    @ManyToOne
    @JsonIgnoreProperties(value = { "wards", "province", "customers", "shopOrders" }, allowSetters = true)
    private District district;

    @ManyToOne
    @JsonIgnoreProperties(value = { "district", "customers", "shopOrders" }, allowSetters = true)
    private Ward ward;

    @ManyToMany(mappedBy = "customers")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = {"customers"}, allowSetters = true)
    private Set<Voucher> vouchers = new HashSet<>();

    public Long getId() {
        return this.id;
    }

    public Customer id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerCode() {
        return this.customerCode;
    }

    public Customer customerCode(String customerCode) {
        this.setCustomerCode(customerCode);
        return this;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public Customer customerName(String customerName) {
        this.setCustomerName(customerName);
        return this;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getTel() {
        return this.tel;
    }

    public Customer tel(String tel) {
        this.setTel(tel);
        return this;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return this.email;
    }

    public Customer email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Customer user(User user) {
        this.setUser(user);
        return this;
    }

    public Set<ShopOrder> getShopOrders() {
        return this.shopOrders;
    }

    public void setShopOrders(Set<ShopOrder> shopOrders) {
        if (this.shopOrders != null) {
            this.shopOrders.forEach(i -> i.setCustomer(null));
        }
        if (shopOrders != null) {
            shopOrders.forEach(i -> i.setCustomer(this));
        }
        this.shopOrders = shopOrders;
    }

    public Customer shopOrders(Set<ShopOrder> shopOrders) {
        this.setShopOrders(shopOrders);
        return this;
    }

    public Customer addShopOrder(ShopOrder shopOrder) {
        this.shopOrders.add(shopOrder);
        shopOrder.setCustomer(this);
        return this;
    }

    public Customer removeShopOrder(ShopOrder shopOrder) {
        this.shopOrders.remove(shopOrder);
        shopOrder.setCustomer(null);
        return this;
    }

    public Province getProvince() {
        return this.province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public Customer province(Province province) {
        this.setProvince(province);
        return this;
    }

    public District getDistrict() {
        return this.district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public Customer district(District district) {
        this.setDistrict(district);
        return this;
    }

    public Ward getWard() {
        return this.ward;
    }

    public void setWard(Ward ward) {
        this.ward = ward;
    }

    public Customer ward(Ward ward) {
        this.setWard(ward);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Customer)) {
            return false;
        }
        return id != null && id.equals(((Customer) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Customer{" +
            "id=" + getId() +
            ", customerCode='" + getCustomerCode() + "'" +
            ", customerName='" + getCustomerName() + "'" +
            ", tel='" + getTel() + "'" +
            ", email='" + getEmail() + "'" +
            "}";
    }
}

package com.tm.j10.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;

import com.tm.j10.domain.enumeration.OrderStatus;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ShopOrder.
 */
@Entity
@Table(name = "shop_order")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ShopOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status", length = 100)
    private OrderStatus orderStatus;

    @Size(max = 3000)
    @Column(name = "delivery_address", length = 3000)
    private String deliveryAddress;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "total_price", nullable = false)
    private Double totalPrice;

    @Size(max = 200)
    @Column(name = "created_by", length = 200)
    private String createdBy;

    @NotNull
    @Column(name = "created_date", nullable = false)
    private Long createdDate;

    @Size(max = 200)
    @Column(name = "modified_by", length = 200)
    private String modifiedBy;

    @Column(name = "modified_date")
    private Long modifiedDate;

    @OneToMany(mappedBy = "shopOrder")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "storage", "shopOrder" }, allowSetters = true)
    private Set<OrderDesc> orderDescs = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = { "districts", "customers", "shopOrders" }, allowSetters = true)
    private Province province;

    @ManyToOne
    @JsonIgnoreProperties(value = { "wards", "province", "customers", "shopOrders" }, allowSetters = true)
    private District district;

    @ManyToOne
    @JsonIgnoreProperties(value = { "district", "customers", "shopOrders" }, allowSetters = true)
    private Ward ward;

    @ManyToOne
    @JsonIgnoreProperties(value = { "user", "shopOrders", "province", "district", "ward" }, allowSetters = true)
    private Customer customer;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ShopOrder id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderStatus getOrderStatus() {
        return this.orderStatus;
    }

    public ShopOrder orderStatus(OrderStatus orderStatus) {
        this.setOrderStatus(orderStatus);
        return this;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getDeliveryAddress() {
        return this.deliveryAddress;
    }

    public ShopOrder deliveryAddress(String deliveryAddress) {
        this.setDeliveryAddress(deliveryAddress);
        return this;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public Double getTotalPrice() {
        return this.totalPrice;
    }

    public ShopOrder totalPrice(Double totalPrice) {
        this.setTotalPrice(totalPrice);
        return this;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public ShopOrder createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Long getCreatedDate() {
        return this.createdDate;
    }

    public ShopOrder createdDate(Long createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedBy() {
        return this.modifiedBy;
    }

    public ShopOrder modifiedBy(String modifiedBy) {
        this.setModifiedBy(modifiedBy);
        return this;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Long getModifiedDate() {
        return this.modifiedDate;
    }

    public ShopOrder modifiedDate(Long modifiedDate) {
        this.setModifiedDate(modifiedDate);
        return this;
    }

    public void setModifiedDate(Long modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Set<OrderDesc> getOrderDescs() {
        return this.orderDescs;
    }

    public void setOrderDescs(Set<OrderDesc> orderDescs) {
        if (this.orderDescs != null) {
            this.orderDescs.forEach(i -> i.setShopOrder(null));
        }
        if (orderDescs != null) {
            orderDescs.forEach(i -> i.setShopOrder(this));
        }
        this.orderDescs = orderDescs;
    }

    public ShopOrder orderDescs(Set<OrderDesc> orderDescs) {
        this.setOrderDescs(orderDescs);
        return this;
    }

    public ShopOrder addOrderDesc(OrderDesc orderDesc) {
        this.orderDescs.add(orderDesc);
        orderDesc.setShopOrder(this);
        return this;
    }

    public ShopOrder removeOrderDesc(OrderDesc orderDesc) {
        this.orderDescs.remove(orderDesc);
        orderDesc.setShopOrder(null);
        return this;
    }

    public Province getProvince() {
        return this.province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public ShopOrder province(Province province) {
        this.setProvince(province);
        return this;
    }

    public District getDistrict() {
        return this.district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public ShopOrder district(District district) {
        this.setDistrict(district);
        return this;
    }

    public Ward getWard() {
        return this.ward;
    }

    public void setWard(Ward ward) {
        this.ward = ward;
    }

    public ShopOrder ward(Ward ward) {
        this.setWard(ward);
        return this;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public ShopOrder customer(Customer customer) {
        this.setCustomer(customer);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ShopOrder)) {
            return false;
        }
        return id != null && id.equals(((ShopOrder) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ShopOrder{" +
            "id=" + getId() +
            ", orderStatus='" + getOrderStatus() + "'" +
            ", deliveryAddress='" + getDeliveryAddress() + "'" +
            ", totalPrice=" + getTotalPrice() +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate=" + getCreatedDate() +
            ", modifiedBy='" + getModifiedBy() + "'" +
            ", modifiedDate=" + getModifiedDate() +
            "}";
    }
}

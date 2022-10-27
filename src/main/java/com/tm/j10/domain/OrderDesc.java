package com.tm.j10.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A OrderDesc.
 */
@Entity
@Table(name = "order_desc")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class OrderDesc implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Min(value = 1L)
    @Column(name = "count", nullable = false)
    private Long count;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "order_price", nullable = false)
    private Double orderPrice;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "final_price", nullable = false)
    private Double finalPrice;

    @JsonIgnoreProperties(value = { "product", "productSize", "color", "store", "orderDesc" }, allowSetters = true)
    @OneToOne
    @JoinColumn(unique = true)
    private Storage storage;

    @ManyToOne
    @JsonIgnoreProperties(value = { "orderDescs", "province", "district", "ward", "customer" }, allowSetters = true)
    private ShopOrder shopOrder;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public OrderDesc id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCount() {
        return this.count;
    }

    public OrderDesc count(Long count) {
        this.setCount(count);
        return this;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Double getOrderPrice() {
        return this.orderPrice;
    }

    public OrderDesc orderPrice(Double orderPrice) {
        this.setOrderPrice(orderPrice);
        return this;
    }

    public void setOrderPrice(Double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Double getFinalPrice() {
        return this.finalPrice;
    }

    public OrderDesc finalPrice(Double finalPrice) {
        this.setFinalPrice(finalPrice);
        return this;
    }

    public void setFinalPrice(Double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public Storage getStorage() {
        return this.storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public OrderDesc storage(Storage storage) {
        this.setStorage(storage);
        return this;
    }

    public ShopOrder getShopOrder() {
        return this.shopOrder;
    }

    public void setShopOrder(ShopOrder shopOrder) {
        this.shopOrder = shopOrder;
    }

    public OrderDesc shopOrder(ShopOrder shopOrder) {
        this.setShopOrder(shopOrder);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrderDesc)) {
            return false;
        }
        return id != null && id.equals(((OrderDesc) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OrderDesc{" +
            "id=" + getId() +
            ", count=" + getCount() +
            ", orderPrice=" + getOrderPrice() +
            ", finalPrice=" + getFinalPrice() +
            "}";
    }
}

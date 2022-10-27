package com.tm.j10.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * A Storage.
 */
@Entity
@Table(name = "storage")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Storage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @DecimalMin(value = "1")
    @Column(name = "capacity")
    private Long capacity;

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

    @ManyToOne
    @JsonIgnoreProperties(value = { "category", "media", "tags", "storages" }, allowSetters = true)
    private Product product;

    @ManyToOne
    @JsonIgnoreProperties(value = { "storages" }, allowSetters = true)
    private ProductSize productSize;

    @ManyToOne
    @JsonIgnoreProperties(value = {"storages"}, allowSetters = true)
    private Color color;

    @ManyToOne
    @JsonIgnoreProperties(value = {"storages"}, allowSetters = true)
    private Store store;

    @JsonIgnoreProperties(value = {"storage", "shopOrder"}, allowSetters = true)
    @OneToOne(mappedBy = "storage")
    private OrderDesc orderDesc;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Storage id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCapacity() {
        return this.capacity;
    }

    public Storage capacity(Long capacity) {
        this.setCapacity(capacity);
        return this;
    }

    public void setCapacity(Long capacity) {
        this.capacity = capacity;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public Storage createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Long getCreatedDate() {
        return this.createdDate;
    }

    public Storage createdDate(Long createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedBy() {
        return this.modifiedBy;
    }

    public Storage modifiedBy(String modifiedBy) {
        this.setModifiedBy(modifiedBy);
        return this;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Long getModifiedDate() {
        return this.modifiedDate;
    }

    public Storage modifiedDate(Long modifiedDate) {
        this.setModifiedDate(modifiedDate);
        return this;
    }

    public void setModifiedDate(Long modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Storage product(Product product) {
        this.setProduct(product);
        return this;
    }

    public ProductSize getProductSize() {
        return this.productSize;
    }

    public void setProductSize(ProductSize productSize) {
        this.productSize = productSize;
    }

    public Storage productSize(ProductSize productSize) {
        this.setProductSize(productSize);
        return this;
    }

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Storage color(Color color) {
        this.setColor(color);
        return this;
    }

    public Store getStore() {
        return this.store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Storage store(Store store) {
        this.setStore(store);
        return this;
    }

    public OrderDesc getOrderDesc() {
        return this.orderDesc;
    }

    public void setOrderDesc(OrderDesc orderDesc) {
        if (this.orderDesc != null) {
            this.orderDesc.setStorage(null);
        }
        if (orderDesc != null) {
            orderDesc.setStorage(this);
        }
        this.orderDesc = orderDesc;
    }

    public Storage orderDesc(OrderDesc orderDesc) {
        this.setOrderDesc(orderDesc);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Storage)) {
            return false;
        }
        return id != null && id.equals(((Storage) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Storage{" +
            "id=" + getId() +
            ", capacity=" + getCapacity() +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate=" + getCreatedDate() +
            ", modifiedBy='" + getModifiedBy() + "'" +
            ", modifiedDate=" + getModifiedDate() +
            "}";
    }
}

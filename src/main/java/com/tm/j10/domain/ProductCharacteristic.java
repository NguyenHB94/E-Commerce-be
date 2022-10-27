package com.tm.j10.domain;

import com.tm.j10.domain.enumeration.ProductCharacteristicType;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ProductCharacteristic.
 */
@Entity
@Table(name = "product_characteristic")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ProductCharacteristic implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private ProductCharacteristicType type;

    @Size(max = 500)
    @Column(name = "name", length = 500)
    private String name;

    @Size(max = 500)
    @Column(name = "value", length = 500)
    private String value;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ProductCharacteristic id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductCharacteristicType getType() {
        return this.type;
    }

    public ProductCharacteristic type(ProductCharacteristicType type) {
        this.setType(type);
        return this;
    }

    public void setType(ProductCharacteristicType type) {
        this.type = type;
    }

    public String getName() {
        return this.name;
    }

    public ProductCharacteristic name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return this.value;
    }

    public ProductCharacteristic value(String value) {
        this.setValue(value);
        return this;
    }

    public void setValue(String value) {
        this.value = value;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProductCharacteristic)) {
            return false;
        }
        return id != null && id.equals(((ProductCharacteristic) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProductCharacteristic{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            ", name='" + getName() + "'" +
            ", value='" + getValue() + "'" +
            "}";
    }
}

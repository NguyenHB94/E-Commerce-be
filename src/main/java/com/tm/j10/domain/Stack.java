package com.tm.j10.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Stack.
 */
@Entity
@Table(name = "stack")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Stack implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "product", nullable = false)
    private Long product;

    @NotNull
    @Column(name = "stack_id", nullable = false)
    private Long stackId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Stack id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProduct() {
        return this.product;
    }

    public Stack product(Long product) {
        this.setProduct(product);
        return this;
    }

    public void setProduct(Long product) {
        this.product = product;
    }

    public Long getStackId() {
        return this.stackId;
    }

    public Stack stackId(Long stackId) {
        this.setStackId(stackId);
        return this;
    }

    public void setStackId(Long stackId) {
        this.stackId = stackId;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Stack)) {
            return false;
        }
        return id != null && id.equals(((Stack) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Stack{" +
            "id=" + getId() +
            ", product=" + getProduct() +
            ", stackId=" + getStackId() +
            "}";
    }
}

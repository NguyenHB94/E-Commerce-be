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
 * A Tag.
 */
@Entity
@Table(name = "tag")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Tag implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Size(max = 300)
    @Column(name = "tag", length = 300)
    private String tag;

    @Size(max = 500)
    @Column(name = "description", length = 500)
    private String description;

    @ManyToMany(mappedBy = "tags")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "category", "media", "tags", "storages" }, allowSetters = true)
    private Set<Product> products = new HashSet<>();

    @ManyToMany(mappedBy = "tags")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "tags" }, allowSetters = true)
    private Set<ShopNew> news = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Tag id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTag() {
        return this.tag;
    }

    public Tag tag(String tag) {
        this.setTag(tag);
        return this;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getDescription() {
        return this.description;
    }

    public Tag description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Product> getProducts() {
        return this.products;
    }

    public void setProducts(Set<Product> products) {
        if (this.products != null) {
            this.products.forEach(i -> i.removeTag(this));
        }
        if (products != null) {
            products.forEach(i -> i.addTag(this));
        }
        this.products = products;
    }

    public Tag products(Set<Product> products) {
        this.setProducts(products);
        return this;
    }

    public Tag addProduct(Product product) {
        this.products.add(product);
        product.getTags().add(this);
        return this;
    }

    public Tag removeProduct(Product product) {
        this.products.remove(product);
        product.getTags().remove(this);
        return this;
    }

    public Set<ShopNew> getNews() {
        return this.news;
    }

    public void setNews(Set<ShopNew> shopNews) {
        if (this.news != null) {
            this.news.forEach(i -> i.removeTag(this));
        }
        if (shopNews != null) {
            shopNews.forEach(i -> i.addTag(this));
        }
        this.news = shopNews;
    }

    public Tag news(Set<ShopNew> shopNews) {
        this.setNews(shopNews);
        return this;
    }

    public Tag addNew(ShopNew shopNew) {
        this.news.add(shopNew);
        shopNew.getTags().add(this);
        return this;
    }

    public Tag removeNew(ShopNew shopNew) {
        this.news.remove(shopNew);
        shopNew.getTags().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Tag)) {
            return false;
        }
        return id != null && id.equals(((Tag) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Tag{" +
            "id=" + getId() +
            ", tag='" + getTag() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}

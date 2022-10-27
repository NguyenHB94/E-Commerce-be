package com.tm.j10.domain;

import com.tm.j10.domain.enumeration.ConfigKey;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A AppConfig.
 */
@Entity
@Table(name = "app_config")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AppConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "config_key")
    private ConfigKey configKey;

    @Size(max = 500)
    @Column(name = "config_value", length = 500)
    private String configValue;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public AppConfig id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ConfigKey getConfigKey() {
        return this.configKey;
    }

    public AppConfig configKey(ConfigKey configKey) {
        this.setConfigKey(configKey);
        return this;
    }

    public void setConfigKey(ConfigKey configKey) {
        this.configKey = configKey;
    }

    public String getConfigValue() {
        return this.configValue;
    }

    public AppConfig configValue(String configValue) {
        this.setConfigValue(configValue);
        return this;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AppConfig)) {
            return false;
        }
        return id != null && id.equals(((AppConfig) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AppConfig{" +
            "id=" + getId() +
            ", configKey='" + getConfigKey() + "'" +
            ", configValue='" + getConfigValue() + "'" +
            "}";
    }
}

package com.tm.j10.config;

import java.time.Duration;
import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;
import org.hibernate.cache.jcache.ConfigSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.info.GitProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.*;
import tech.jhipster.config.JHipsterProperties;
import tech.jhipster.config.cache.PrefixedKeyGenerator;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private GitProperties gitProperties;
    private BuildProperties buildProperties;
    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache = jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration =
            Eh107Configuration.fromEhcacheCacheConfiguration(
                CacheConfigurationBuilder
                    .newCacheConfigurationBuilder(Object.class, Object.class, ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                    .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                    .build()
            );
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cacheManager) {
        return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            createCache(cm, com.tm.j10.repository.UserRepository.USERS_BY_LOGIN_CACHE);
            createCache(cm, com.tm.j10.repository.UserRepository.USERS_BY_EMAIL_CACHE);
            createCache(cm, com.tm.j10.domain.User.class.getName());
            createCache(cm, com.tm.j10.domain.Authority.class.getName());
            createCache(cm, com.tm.j10.domain.User.class.getName() + ".authorities");
            createCache(cm, com.tm.j10.domain.AppConfig.class.getName());
            createCache(cm, com.tm.j10.domain.Category.class.getName());
            createCache(cm, com.tm.j10.domain.Color.class.getName());
            createCache(cm, com.tm.j10.domain.Color.class.getName() + ".storages");
            createCache(cm, com.tm.j10.domain.ProductCharacteristic.class.getName());
            createCache(cm, com.tm.j10.domain.Customer.class.getName());
            createCache(cm, com.tm.j10.domain.Customer.class.getName() + ".shopOrders");
            createCache(cm, com.tm.j10.domain.Media.class.getName());
            createCache(cm, com.tm.j10.domain.Media.class.getName() + ".products");
            createCache(cm, com.tm.j10.domain.ShopNew.class.getName());
            createCache(cm, com.tm.j10.domain.ShopNew.class.getName() + ".tags");
            createCache(cm, com.tm.j10.domain.Product.class.getName());
            createCache(cm, com.tm.j10.domain.Product.class.getName() + ".media");
            createCache(cm, com.tm.j10.domain.Product.class.getName() + ".tags");
            createCache(cm, com.tm.j10.domain.Product.class.getName() + ".storages");
            createCache(cm, com.tm.j10.domain.ProductSize.class.getName());
            createCache(cm, com.tm.j10.domain.ProductSize.class.getName() + ".storages");
            createCache(cm, com.tm.j10.domain.ShopOrder.class.getName());
            createCache(cm, com.tm.j10.domain.ShopOrder.class.getName() + ".orderDescs");
            createCache(cm, com.tm.j10.domain.OrderDesc.class.getName());
            createCache(cm, com.tm.j10.domain.Storage.class.getName());
            createCache(cm, com.tm.j10.domain.Store.class.getName());
            createCache(cm, com.tm.j10.domain.Store.class.getName() + ".storages");
            createCache(cm, com.tm.j10.domain.Tag.class.getName());
            createCache(cm, com.tm.j10.domain.Tag.class.getName() + ".products");
            createCache(cm, com.tm.j10.domain.Tag.class.getName() + ".news");
            createCache(cm, com.tm.j10.domain.Province.class.getName());
            createCache(cm, com.tm.j10.domain.Province.class.getName() + ".districts");
            createCache(cm, com.tm.j10.domain.Province.class.getName() + ".customers");
            createCache(cm, com.tm.j10.domain.Province.class.getName() + ".shopOrders");
            createCache(cm, com.tm.j10.domain.District.class.getName());
            createCache(cm, com.tm.j10.domain.District.class.getName() + ".wards");
            createCache(cm, com.tm.j10.domain.District.class.getName() + ".customers");
            createCache(cm, com.tm.j10.domain.District.class.getName() + ".shopOrders");
            createCache(cm, com.tm.j10.domain.Ward.class.getName());
            createCache(cm, com.tm.j10.domain.Ward.class.getName() + ".customers");
            createCache(cm, com.tm.j10.domain.Ward.class.getName() + ".shopOrders");
            createCache(cm, com.tm.j10.domain.Stack.class.getName());
            // jhipster-needle-ehcache-add-entry
        };
    }

    private void createCache(javax.cache.CacheManager cm, String cacheName) {
        javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
        if (cache != null) {
            cache.clear();
        } else {
            cm.createCache(cacheName, jcacheConfiguration);
        }
    }

    @Autowired(required = false)
    public void setGitProperties(GitProperties gitProperties) {
        this.gitProperties = gitProperties;
    }

    @Autowired(required = false)
    public void setBuildProperties(BuildProperties buildProperties) {
        this.buildProperties = buildProperties;
    }

    @Bean
    public KeyGenerator keyGenerator() {
        return new PrefixedKeyGenerator(this.gitProperties, this.buildProperties);
    }
}

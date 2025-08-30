package ai.infrrd.postgres.multitenancy.config;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import javax.sql.DataSource;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.cfg.Environment;

@Configuration
@EnableJpaRepositories(basePackages = "ai.infrrd.postgres.multitenancy.repository")
public class HibernateConfig {

    private final DataSource dataSource;
    private final JpaProperties jpaProperties;
    private final JpaVendorAdapter jpaVendorAdapter;

    public HibernateConfig(DataSource dataSource, JpaProperties jpaProperties, JpaVendorAdapter jpaVendorAdapter) {
        this.dataSource = dataSource;
        this.jpaProperties = jpaProperties;
        this.jpaVendorAdapter = jpaVendorAdapter;
    }

    @Bean
    public MultiTenantConnectionProvider multiTenantConnectionProvider() {
        return new DataSourceBasedMultiTenantConnectionProviderImpl(dataSource);
    }

    @Bean
    public CurrentTenantIdentifierResolver currentTenantIdentifierResolver() {
        return new CurrentTenantIdentifierResolverImpl();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        Map<String, Object> hibernateProps = new HashMap<>();
        hibernateProps.put("hibernate.multiTenancy", "SCHEMA");  // use string here
        hibernateProps.put(Environment.MULTI_TENANT_CONNECTION_PROVIDER, multiTenantConnectionProvider());
        hibernateProps.put(Environment.MULTI_TENANT_IDENTIFIER_RESOLVER, currentTenantIdentifierResolver());

        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource);
        emf.setPackagesToScan("ai.infrrd.postgres.multitenancy.entity");
        emf.setJpaVendorAdapter(jpaVendorAdapter);
        emf.setJpaPropertyMap(hibernateProps);
        return emf;
    }
}


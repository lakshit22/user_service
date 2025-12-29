package com.callmychef.user_service.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Map;
import java.util.Objects;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.callmychef.user_service.chefdb.repository",
        entityManagerFactoryRef = "chefDbEntityManager",
        transactionManagerRef = "chefDbTransactionManager"
)
public class ChefDbConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.chef-db")
    public DataSource chefDbDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public EntityManagerFactoryBuilder entityManagerFactoryBuilderForChef() {
        return new EntityManagerFactoryBuilder(
                new HibernateJpaVendorAdapter(),
                dataSource -> Map.of(
                        "hibernate.hbm2ddl.auto", "update",
                        "hibernate.show_sql", "true"
                ),
                null
        );
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean chefDbEntityManager(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(chefDbDataSource())
                .packages("com.callmychef.user_service.chefdb.entity")
                .persistenceUnit("chefDb")
                .build();
    }

    @Bean
    public PlatformTransactionManager chefDbTransactionManager(@Qualifier("chefDbEntityManager") LocalContainerEntityManagerFactoryBean emf) {
        return new JpaTransactionManager(Objects.requireNonNull(emf.getObject()));
    }
}

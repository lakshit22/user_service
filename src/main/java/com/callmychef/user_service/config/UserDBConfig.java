package com.callmychef.user_service.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
        basePackages = "com.callmychef.user_service.userdb.repository",
        entityManagerFactoryRef = "userDbEntityManager",
        transactionManagerRef = "db1TransactionManager"
)
public class UserDBConfig {
    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.user-db")
    public DataSource userDbDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public EntityManagerFactoryBuilder entityManagerFactoryBuilderForUser() {
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
    @Primary
    public LocalContainerEntityManagerFactoryBean userDbEntityManager(EntityManagerFactoryBuilder builder){
        return builder
                .dataSource(userDbDataSource())
                .packages("com.callmychef.user_service.userdb.entity")
                .persistenceUnit("userDb")
                .build();
    }

    @Primary
    @Bean
    public PlatformTransactionManager db1TransactionManager(@Qualifier("userDbEntityManager") LocalContainerEntityManagerFactoryBean emf){
        return new JpaTransactionManager((Objects.requireNonNull(emf.getObject())));
    }
}

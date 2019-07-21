package me.leckie.jka.atomikos.configuration;

import java.util.Map;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Leckie
 * @version TransferDataSourceConfiguration.java, v0.1 2019/7/21 15:06
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "me.leckie.jka.atomikos.repository.transfer", entityManagerFactoryRef = "transferEntityManagerFactory", transactionManagerRef = "transferTransactionManager")
public class TransferDataSourceConfiguration {

  @Autowired
  @Qualifier("transferDataSource")
  private DataSource transferDataSource;

  @Autowired
  private JpaProperties jpaProperties;

  @Autowired
  private HibernateProperties hibernateProperties;

  private Map<String, Object> getVendorProperties() {
    Map<String, Object> properties = hibernateProperties
        .determineHibernateProperties(jpaProperties.getProperties(), new HibernateSettings());
    return properties;
  }

  @Bean(name = "transferEntityManager")
  public EntityManager transferEntityManager(EntityManagerFactoryBuilder builder) {
    return transferEntityManagerFactory(builder).getObject().createEntityManager();
  }

  @Bean(name = "transferEntityManagerFactory")
  public LocalContainerEntityManagerFactoryBean transferEntityManagerFactory(EntityManagerFactoryBuilder builder) {
    return builder
        .dataSource(transferDataSource)
        .properties(getVendorProperties())
        .packages("me.leckie.jka.atomikos.domain.dataobject.transfer") //设置实体类所在位置
        .persistenceUnit("transferPersistenceUnit")
        .build();
  }

  @Bean(name = "transferTransactionManager")
  public PlatformTransactionManager transferTransactionManager(EntityManagerFactoryBuilder builder) {
    return new JpaTransactionManager(transferEntityManagerFactory(builder).getObject());
  }
}

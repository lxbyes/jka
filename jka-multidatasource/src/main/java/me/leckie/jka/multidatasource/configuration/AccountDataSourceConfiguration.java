package me.leckie.jka.multidatasource.configuration;

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
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Leckie
 * @version AccountDataSourceConfiguration.java, v0.1 2019/7/21 15:06
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "me.leckie.jka.multidatasource.repository.account", entityManagerFactoryRef = "accountEntityManagerFactory", transactionManagerRef = "accountTransactionManager")
public class AccountDataSourceConfiguration {

  @Autowired
  @Qualifier("accountDataSource")
  private DataSource accountDataSource;

  @Autowired
  private JpaProperties jpaProperties;

  @Autowired
  private HibernateProperties hibernateProperties;

  private Map<String, Object> getVendorProperties() {
    Map<String, Object> properties = hibernateProperties
        .determineHibernateProperties(jpaProperties.getProperties(), new HibernateSettings());
    return properties;
  }

  @Primary
  @Bean(name = "accountEntityManager")
  public EntityManager accountEntityManager(EntityManagerFactoryBuilder builder) {
    return accountEntityManagerFactory(builder).getObject().createEntityManager();
  }

  @Primary
  @Bean(name = "accountEntityManagerFactory")
  public LocalContainerEntityManagerFactoryBean accountEntityManagerFactory(EntityManagerFactoryBuilder builder) {
    return builder
        .dataSource(accountDataSource)
        .properties(getVendorProperties())
        .packages("me.leckie.jka.multidatasource.domain.dataobject.account") //设置实体类所在位置
        .persistenceUnit("accountPersistenceUnit")
        .build();
  }

  @Primary
  @Bean(name = "accountTransactionManager")
  public PlatformTransactionManager accountTransactionManager(EntityManagerFactoryBuilder builder) {
    return new JpaTransactionManager(accountEntityManagerFactory(builder).getObject());
  }

}

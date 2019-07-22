package me.leckie.jka.atomikos.configuration;

import java.util.HashMap;
import javax.sql.DataSource;
import org.springframework.boot.orm.jpa.hibernate.SpringJtaPlatform;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.jta.JtaTransactionManager;

/**
 * @author Leckie
 * @version AccountDataSourceConfiguration.java, v0.1 2019/7/21 15:06
 */
@Configuration
@DependsOn("transactionManager")
@EnableJpaRepositories(basePackages = "me.leckie.jka.atomikos.repository.account", entityManagerFactoryRef = "accountEntityManagerFactory")
public class AccountDataSourceConfiguration {

  @Primary
  @Bean(name = "accountEntityManagerFactory")
  public LocalContainerEntityManagerFactoryBean accountEntityManagerFactory(JpaVendorAdapter jpaVendorAdapter,
      JtaTransactionManager transactionManager, DataSource accountDataSource) {
    HashMap<String, Object> properties = new HashMap<>();
    properties.put("hibernate.transaction.jta.platform", new SpringJtaPlatform(transactionManager));
    properties.put("javax.persistence.transactionType", "JTA");
    LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
    entityManagerFactoryBean.setJtaDataSource(accountDataSource);
    entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);
    entityManagerFactoryBean.setPackagesToScan("me.leckie.jka.atomikos.domain.dataobject.account");
    entityManagerFactoryBean.setPersistenceUnitName("accountPersistenceUnit");
    entityManagerFactoryBean.setJpaPropertyMap(properties);
    return entityManagerFactoryBean;
  }

}

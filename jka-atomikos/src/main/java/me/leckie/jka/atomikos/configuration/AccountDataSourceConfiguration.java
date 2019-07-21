package me.leckie.jka.atomikos.configuration;

import com.mysql.cj.jdbc.MysqlXADataSource;
import java.sql.SQLException;
import java.util.HashMap;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

/**
 * @author Leckie
 * @version AccountDataSourceConfiguration.java, v0.1 2019/7/21 15:06
 */
@Configuration
@DependsOn("transactionManager")
@EnableJpaRepositories(basePackages = "me.leckie.jka.atomikos.repository.account", entityManagerFactoryRef = "accountEntityManagerFactory")
public class AccountDataSourceConfiguration {

  @Autowired(required = false)
  private JpaVendorAdapter jpaVendorAdapter;

  @Primary
  @Bean("accountDataSourceProperties")
  @ConfigurationProperties(prefix = "spring.datasource.account")
  public DataSourceProperties accountDataSourceProperties() {
    return new DataSourceProperties();
  }

  @Primary
  @Bean(name = "accountDataSource")
  public DataSource accountDataSource() throws SQLException {
    MysqlXADataSource mysqlXADataSource = new MysqlXADataSource();
    mysqlXADataSource.setUrl(accountDataSourceProperties().getUrl());
    mysqlXADataSource.setPinGlobalTxToPhysicalConnection(true);
    mysqlXADataSource.setPassword(accountDataSourceProperties().getPassword());
    mysqlXADataSource.setUser(accountDataSourceProperties().getUsername());
    AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
    xaDataSource.setXaDataSource(mysqlXADataSource);
    xaDataSource.setUniqueResourceName("accountDataSource");
    xaDataSource.setBorrowConnectionTimeout(60);
    xaDataSource.setMaxPoolSize(10);
    return xaDataSource;
  }

//  @Primary
//  @Bean(name = "accountEntityManager")
//  public EntityManager accountEntityManager() throws SQLException {
//    return accountEntityManagerFactory().getObject().createEntityManager();
//  }

  @Primary
  @Bean(name = "accountEntityManagerFactory")
  public LocalContainerEntityManagerFactoryBean accountEntityManagerFactory()
      throws SQLException {
    HashMap<String, Object> properties = new HashMap<>();
    properties.put("hibernate.transaction.jta.platform", AtomikosJtaPlatform.class.getName());
    properties.put("javax.persistence.transactionType", "JTA");
    LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
    entityManagerFactoryBean.setJtaDataSource(accountDataSource());
    entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);
    entityManagerFactoryBean.setPackagesToScan("me.leckie.jka.atomikos.domain.dataobject.account");
    entityManagerFactoryBean.setPersistenceUnitName("accountPersistenceUnit");
    entityManagerFactoryBean.setJpaPropertyMap(properties);
    return entityManagerFactoryBean;
  }

}

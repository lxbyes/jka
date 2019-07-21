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
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

/**
 * @author Leckie
 * @version TransferDataSourceConfiguration.java, v0.1 2019/7/21 15:06
 */
@Configuration
@DependsOn("transactionManager")
@EnableJpaRepositories(basePackages = "me.leckie.jka.atomikos.repository.transfer", entityManagerFactoryRef = "transferEntityManagerFactory")
public class TransferDataSourceConfiguration {

  @Autowired(required = false)
  private JpaVendorAdapter jpaVendorAdapter;

  @Bean("transferDataSourceProperties")
  @ConfigurationProperties(prefix = "spring.datasource.transfer")
  public DataSourceProperties transferDataSourceProperties() {
    return new DataSourceProperties();
  }

  @Bean(name = "transferDataSource")
  public DataSource transferDataSource() throws SQLException {
    MysqlXADataSource mysqlXADataSource = new MysqlXADataSource();
    mysqlXADataSource.setUrl(transferDataSourceProperties().getUrl());
    mysqlXADataSource.setPinGlobalTxToPhysicalConnection(true);
    mysqlXADataSource.setPassword(transferDataSourceProperties().getPassword());
    mysqlXADataSource.setUser(transferDataSourceProperties().getUsername());
    AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
    xaDataSource.setXaDataSource(mysqlXADataSource);
    xaDataSource.setUniqueResourceName("transferDataSource");
    xaDataSource.setBorrowConnectionTimeout(60);
    xaDataSource.setMaxPoolSize(10);
    return xaDataSource;
  }

//  @Bean(name = "transferEntityManager")
//  public EntityManager transferEntityManager() throws SQLException {
//    return transferEntityManagerFactory().getObject().createEntityManager();
//  }

  @Bean(name = "transferEntityManagerFactory")
  public LocalContainerEntityManagerFactoryBean transferEntityManagerFactory()
      throws SQLException {
    HashMap<String, Object> properties = new HashMap<>();
    properties.put("hibernate.transaction.jta.platform", AtomikosJtaPlatform.class.getName());
    properties.put("javax.persistence.transactionType", "JTA");
    LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
    entityManagerFactoryBean.setJtaDataSource(transferDataSource());
    entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);
    entityManagerFactoryBean.setPackagesToScan("me.leckie.jka.atomikos.domain.dataobject.transfer");//设置实体类所在位置
    entityManagerFactoryBean.setPersistenceUnitName("transferPersistenceUnit");
    entityManagerFactoryBean.setJpaPropertyMap(properties);
    return entityManagerFactoryBean;
  }

}

package me.leckie.jka.bytetccconsumer.configuration;

import java.util.HashMap;
import javax.sql.DataSource;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.bytesoft.bytetcc.supports.springboot.config.SpringBootSecondaryConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

/**
 * @author Leckie
 * @version ByteTCCConfiguration.java, v0.1 2019/7/21 12:53
 */
@Configuration
@Import(SpringBootSecondaryConfiguration.class)
public class ByteTCCConfiguration {

  // @Bean
  public CuratorFramework curatorFramework() throws InterruptedException {
    CuratorFramework curatorFramework = CuratorFrameworkFactory.builder()
        .connectString("127.0.0.1:2181")
        .sessionTimeoutMs(1000 * 3).retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();
    curatorFramework.start();
    curatorFramework.blockUntilConnected();
    return curatorFramework;
  }

  @Bean("entityManagerFactory")
  public LocalContainerEntityManagerFactoryBean entityManagerFactory(JpaVendorAdapter jpaVendorAdapter,
      @Autowired @Qualifier("dataSource") DataSource dataSource) {
    HashMap<String, Object> properties = new HashMap<>();
    properties.put("javax.persistence.transactionType", "JTA");
    properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
    properties.put("hibernate.transaction.coordinator_class", "jta");
    properties
        .put("hibernate.transaction.jta.platform", "org.bytesoft.bytetcc.supports.jpa.hibernate.HibernateJtaPlatform");
    LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
    entityManagerFactoryBean.setJtaDataSource(dataSource);
    entityManagerFactoryBean.setPackagesToScan("me.leckie.jka.bytetccconsumer.domain.dataobject");
    entityManagerFactoryBean.setPersistenceUnitName("bytetccproviderPersistenceUnit");
    entityManagerFactoryBean.setJpaPropertyMap(properties);
    entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);
    return entityManagerFactoryBean;
  }
}

package me.leckie.jka.atomikos.configuration;

import java.util.HashMap;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.hibernate.SpringJtaPlatform;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.jta.JtaTransactionManager;

/**
 * @author Leckie
 * @version TransferDataSourceConfiguration.java, v0.1 2019/7/21 15:06
 */
@Configuration
@DependsOn("transactionManager")
@EnableJpaRepositories(basePackages = "me.leckie.jka.atomikos.repository.transfer", entityManagerFactoryRef = "transferEntityManagerFactory")
public class TransferDataSourceConfiguration {

  @Bean(name = "transferEntityManagerFactory")
  public LocalContainerEntityManagerFactoryBean transferEntityManagerFactory(JpaVendorAdapter jpaVendorAdapter,
      JtaTransactionManager transactionManager, @Qualifier("transferDataSource") DataSource transferDataSource) {
    HashMap<String, Object> properties = new HashMap<>();
    properties.put("hibernate.transaction.jta.platform", new SpringJtaPlatform(transactionManager));
    properties.put("javax.persistence.transactionType", "JTA");
    LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
    entityManagerFactoryBean.setJtaDataSource(transferDataSource);
    entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);
    entityManagerFactoryBean.setPackagesToScan("me.leckie.jka.atomikos.domain.dataobject.transfer");//设置实体类所在位置
    entityManagerFactoryBean.setPersistenceUnitName("transferPersistenceUnit");
    entityManagerFactoryBean.setJpaPropertyMap(properties);
    return entityManagerFactoryBean;
  }

}

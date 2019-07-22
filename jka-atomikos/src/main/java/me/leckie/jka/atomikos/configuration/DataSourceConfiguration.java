package me.leckie.jka.atomikos.configuration;

import com.mysql.cj.jdbc.MysqlXADataSource;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author Leckie
 * @version DataSourceConfiguration.java, v0.1 2019/7/22 11:33
 */
@Configuration
public class DataSourceConfiguration {

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

}

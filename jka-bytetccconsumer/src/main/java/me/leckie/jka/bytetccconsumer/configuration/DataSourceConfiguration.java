package me.leckie.jka.bytetccconsumer.configuration;

import javax.sql.DataSource;
import org.bytesoft.bytejta.supports.jdbc.LocalXADataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author Leckie
 * @version DataSourceConfiguration.java, v0.1 2019/7/22 13:51
 */
@Configuration
public class DataSourceConfiguration {

  @Primary
  @Bean
  @ConfigurationProperties(prefix = "spring.datasource")
  public DataSourceProperties dataSourceProperties() {
    return new DataSourceProperties();
  }

  @Primary
  @Bean(name = "dataSource")
  public DataSource dataSource() {
    DataSource ds = dataSourceProperties().initializeDataSourceBuilder().build();
    LocalXADataSource dataSource = new LocalXADataSource();
    dataSource.setDataSource(ds);
    return dataSource;
  }
}

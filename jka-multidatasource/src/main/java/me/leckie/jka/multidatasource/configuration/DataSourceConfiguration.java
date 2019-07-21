package me.leckie.jka.multidatasource.configuration;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author Leckie
 * @version DataSourceConfiguration.java, v0.1 2019/7/21 15:07
 */
@Configuration
public class DataSourceConfiguration {

  @Bean
  @Primary
  @Qualifier("accountDataSource")
  @ConfigurationProperties(prefix = "spring.datasource.account")
  public DataSource accountDataSource() {
    return DataSourceBuilder.create().build();
  }

  @Bean
  @Qualifier("transferDataSource")
  @ConfigurationProperties(prefix = "spring.datasource.transfer")
  public DataSource transferDataSource() {
    return DataSourceBuilder.create().build();
  }
}

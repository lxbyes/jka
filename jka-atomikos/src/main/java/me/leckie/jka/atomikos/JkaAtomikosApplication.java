package me.leckie.jka.atomikos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Leckie
 * @version JkaAtomikosApplication.java, v0.1 2019/7/21 15:02
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class,
    DataSourceTransactionManagerAutoConfiguration.class})
@EnableTransactionManagement
public class JkaAtomikosApplication {

  public static void main(String[] args) {
    SpringApplication.run(JkaAtomikosApplication.class, args);
  }

}

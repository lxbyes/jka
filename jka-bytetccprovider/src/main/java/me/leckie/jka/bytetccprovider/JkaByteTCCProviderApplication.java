package me.leckie.jka.bytetccprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author Leckie
 * @version JkaByteTCCProviderApplication.java, v0.1 2019/7/20 14:23
 */
@SpringBootApplication
@EnableJpaRepositories(transactionManagerRef = "jtaTransactionManager")
public class JkaByteTCCProviderApplication {

  public static void main(String[] args) {
    SpringApplication.run(JkaByteTCCProviderApplication.class, args);
  }

}

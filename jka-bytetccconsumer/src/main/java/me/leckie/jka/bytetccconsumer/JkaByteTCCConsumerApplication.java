package me.leckie.jka.bytetccconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author Leckie
 * @version JkaByteTCCConsumerApplication.java, v0.1 2019/7/20 14:22
 */
@SpringBootApplication(exclude = MongoAutoConfiguration.class)
@EnableJpaRepositories(transactionManagerRef = "jtaTransactionManager")
public class JkaByteTCCConsumerApplication {

  public static void main(String[] args) {
    SpringApplication.run(JkaByteTCCConsumerApplication.class, args);
  }

}

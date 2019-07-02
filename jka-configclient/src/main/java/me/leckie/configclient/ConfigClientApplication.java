package me.leckie.configclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author Leckie
 * @version ConfigClientApplication: ConfigClientApplication.java, v0.1 2019/6/26 16:50 john Exp $$
 */
@SpringBootApplication
public class ConfigClientApplication {

  public static void main(String[] args) {
    try {
      ConfigurableApplicationContext applicationContext = SpringApplication.run(ConfigClientApplication.class, args);
      applicationContext.getEnvironment().getPropertySources().forEach(propertySource -> {
        System.out.println("------------------------------");
        System.out.println("name: " + propertySource.getName() + ", class: " + propertySource.getSource().getClass());
        System.out.println(propertySource.getSource());
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

package me.leckie.configclient;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author Leckie
 * @version ConfigClientApplication: ConfigClientApplication.java, v0.1 2019/6/26 16:50 john Exp $$
 */
@SpringBootApplication
public class ConfigClientApplication {

  @Value("${stu.gr.name: NOT_FOUND}")
  private String name;

  @Value("${foo: NOT_FOUND}")
  private String foo;

  @Value("${foo.db: NOT_FOUND}")
  private String fooDb;

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

  @PostConstruct
  private void postConstruct() {
    System.out.println("stu.gr.name: " + name);
    System.out.println("foo: " + foo);
    System.out.println("foo.db: " + fooDb);
  }
}

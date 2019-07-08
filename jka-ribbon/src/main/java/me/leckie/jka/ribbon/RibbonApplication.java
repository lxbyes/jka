package me.leckie.jka.ribbon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author Leckie
 * @version RibbonApplication: RibbonApplication.java, v0.1 2019/7/8 14:33 john Exp $$
 */
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
public class RibbonApplication {

  public static void main(String[] args) {
    SpringApplication.run(RibbonApplication.class, args);
  }

}

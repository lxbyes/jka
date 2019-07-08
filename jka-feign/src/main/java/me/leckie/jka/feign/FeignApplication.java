package me.leckie.jka.feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Leckie
 * @version FeignApplication: FeignApplication.java, v0.1 2019/7/8 15:13 john Exp $$
 */
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients
public class FeignApplication {

  public static void main(String[] args) {
    SpringApplication.run(FeignApplication.class, args);
  }
}

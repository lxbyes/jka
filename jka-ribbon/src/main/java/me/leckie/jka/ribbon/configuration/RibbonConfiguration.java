package me.leckie.jka.ribbon.configuration;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author Leckie
 * @version RibbonConfiguration: RibbonConfiguration.java, v0.1 2019/7/8 14:40 john Exp $$
 */
@Configuration
public class RibbonConfiguration {

  @Bean
  @LoadBalanced
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

}

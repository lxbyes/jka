package me.leckie.jka.ribbon.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author Leckie
 * @version HelloService: HelloService.java, v0.1 2019/7/8 14:43 john Exp $$
 */
@Service
public class HelloService {

  @Autowired
  private RestTemplate restTemplate;

  @HystrixCommand(fallbackMethod = "hiError")
  public String valueOfFieldName(String fieldName) {
    return restTemplate.getForObject("http://JKA-CLIENT/value/" + fieldName, String.class);
  }

  public String hiError(String fieldName) {
    return "Sorry, " + fieldName + " service not available.";
  }

}

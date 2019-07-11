package me.leckie.jka.ribbon.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import java.util.HashMap;
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
  public Object valueOfFieldName(String fieldName) {
    String fieldNameValue = restTemplate.getForObject("http://JKA-CLIENT/value/" + fieldName, String.class);
    String port = restTemplate.getForObject("http://JKA-CLIENT/value/port", String.class);
    return new HashMap<String, Object>() {{
      put(fieldName, fieldNameValue);
      put("port", port);
    }};
  }

  public Object hiError(String fieldName) {
    return "Sorry, " + fieldName + " service not available.";
  }

}

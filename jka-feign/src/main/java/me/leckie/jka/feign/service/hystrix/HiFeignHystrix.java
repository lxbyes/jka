package me.leckie.jka.feign.service.hystrix;

import me.leckie.jka.feign.service.HiFeignService;
import org.springframework.stereotype.Component;

/**
 * @author Leckie
 * @version HiFeignHystrix: HiFeignHystrix.java, v0.1 2019/7/8 16:58 john Exp $$
 */
@Component
public class HiFeignHystrix implements HiFeignService {

  @Override
  public String valueOfFieldName(String fieldName) {
    return "Sorry, " + fieldName + " service not available";
  }
}

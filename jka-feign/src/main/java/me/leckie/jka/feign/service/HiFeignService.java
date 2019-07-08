package me.leckie.jka.feign.service;

import me.leckie.jka.feign.service.hystrix.HiFeignHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Leckie
 * @version HiFeignService: HiFeignService.java, v0.1 2019/7/8 15:55 john Exp $$
 */
@FeignClient(value = "jka-client", fallback = HiFeignHystrix.class)
public interface HiFeignService {

  @GetMapping(value = "value/{fieldName}")
  String valueOfFieldName(@PathVariable String fieldName);

}

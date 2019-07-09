package me.leckie.jka.feign.web.controller;

import me.leckie.jka.feign.service.HiFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Leckie
 * @version HiFeignController: HiFeignController.java, v0.1 2019/7/8 16:12 john Exp $$
 */
@RestController
@RequestMapping("/hifeign")
public class HiFeignController {

  @Autowired
  private HiFeignService hiFeignService;

  @GetMapping("/port")
  public String getPort() {
    return hiFeignService.valueOfFieldName("port");
  }

  @GetMapping("{fieldName}")
  public String getValueOfField(@PathVariable String fieldName) {
    return hiFeignService.valueOfFieldName(fieldName);
  }

}

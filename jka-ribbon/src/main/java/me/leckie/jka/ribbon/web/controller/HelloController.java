package me.leckie.jka.ribbon.web.controller;

import me.leckie.jka.ribbon.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Leckie
 * @version HelloController: HelloController.java, v0.1 2019/7/8 14:56 john Exp $$
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

  @Autowired
  private HelloService helloService;

  @GetMapping("{fieldName}")
  public String helloRibbon(@PathVariable String fieldName) {
    return helloService.valueOfFieldName(fieldName);
  }

}

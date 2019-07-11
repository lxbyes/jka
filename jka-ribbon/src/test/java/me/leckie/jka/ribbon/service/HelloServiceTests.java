package me.leckie.jka.ribbon.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Leckie
 * @version : HelloServiceTests.java, v0.1 2019/7/8 14:48 john Exp $$
 */
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@RunWith(SpringRunner.class)
public class HelloServiceTests {

  @Autowired
  private HelloService helloService;

  @Test
  public void valueOfFieldName() {
    Object fooValue = helloService.valueOfFieldName("foo");
    System.out.println(fooValue);
  }
}
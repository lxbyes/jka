package me.leckie.jka.feign.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Leckie
 * @version : HiFeignServiceTests.java, v0.1 2019/7/8 16:02 john Exp $$
 */
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@RunWith(SpringRunner.class)
public class HiFeignServiceTests {

  @Autowired
  private HiFeignService hiFeignService;

  @Test
  public void valueOfFieldName() {
    System.out.println(hiFeignService.valueOfFieldName("port"));
  }
}
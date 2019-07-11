package me.leckie.jka.client.web.controller;

import java.lang.reflect.Field;
import java.util.Enumeration;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Leckie
 * @version ConfigController: ConfigController.java, v0.1 2019/7/2 17:28 john Exp $$
 */
@RestController
@RequestMapping
@RefreshScope
public class ConfigController {

  private Logger logger = LoggerFactory.getLogger(ConfigController.class);

  private static final String NOT_FOUND = "NOT_FOUND";

  @Value("${stu.gr.name: NOT_FOUND}")
  private String name;

  @Value("${foo: NOT_FOUND}")
  private String foo;

  @Value("${foo.db: NOT_FOUND}")
  private String fooDb;

  @Value("${server.port: NOTFOUND}")
  private String port;

  @GetMapping("value/{fieldName}")
  public String valueOfField(@PathVariable String fieldName, HttpServletRequest request) {

    logger.info("------------------");
    Enumeration<String> headerNames = request.getHeaderNames();
    while (headerNames.hasMoreElements()) {
      String headerName = headerNames.nextElement();
      logger.info("headerName={}, headerValue={}", headerName, request.getHeader(headerName));
    }

    try {
      Field field = this.getClass().getDeclaredField(fieldName);
      field.setAccessible(true);
      return String.valueOf(field.get(this));
    } catch (NoSuchFieldException | IllegalAccessException e) {
      e.printStackTrace();
    }
    return NOT_FOUND;
  }

  @PostConstruct
  private void postConstruct() {
    logger.info("stu.gr.name: " + name);
    logger.info("foo: " + foo);
    logger.info("foo.db: " + fooDb);
  }
}

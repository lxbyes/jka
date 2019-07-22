package me.leckie.jka.bytetccconsumer.repository;

import me.leckie.jka.bytetccconsumer.domain.dataobject.Order;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Leckie
 * @version OrderRepositoryTests.java, v0.1 2019/7/20 15:13
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class OrderRepositoryTests {

  @Autowired
  private OrderRepository orderRepository;

  @Test
  public void testSave() {
    Order order = new Order();
    order.setAccountId(1L);
    order.setAmount(1000D);
    orderRepository.save(order);
    Assert.assertNotNull(order.getId());
    Assert.assertNotNull(order.getCreatedTime());
  }

}
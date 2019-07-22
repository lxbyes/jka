package me.leckie.jka.bytetccconsumer.repository;

import me.leckie.jka.bytetccconsumer.domain.dataobject.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Leckie
 * @version OrderRepository.java, v0.1 2019/7/20 15:13
 */
public interface OrderRepository extends JpaRepository<Order, Long> {

}

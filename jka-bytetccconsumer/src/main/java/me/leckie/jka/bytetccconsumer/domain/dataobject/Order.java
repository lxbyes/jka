package me.leckie.jka.bytetccconsumer.domain.dataobject;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

/**
 * @author Leckie
 * @version Order.java, v0.1 2019/7/20 15:10
 */
@Entity
@Table(name = "tbl_order")
@Data
public class Order implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Long accountId;

  private Double amount;

  @Column(insertable = false, updatable = false)
  @CreationTimestamp
  private LocalDateTime createdTime;
}

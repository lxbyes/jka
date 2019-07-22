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
 * @version Transfer.java, v0.1 2019/7/21 11:40
 */
@Entity
@Table(name = "transfer")
@Data
public class Transfer implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Long source;

  private Long target;

  private Double amount;

  @Column(updatable = false, insertable = false)
  @CreationTimestamp
  private LocalDateTime transferTime;

}

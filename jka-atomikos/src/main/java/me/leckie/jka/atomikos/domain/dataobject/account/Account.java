package me.leckie.jka.atomikos.domain.dataobject.account;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 * @author Leckie
 * @version Account.java, v0.1 2019/7/20 14:52
 */
@Entity
@Table(name = "account")
@Data
public class Account implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private Double amount;

  private Double frozen;

}

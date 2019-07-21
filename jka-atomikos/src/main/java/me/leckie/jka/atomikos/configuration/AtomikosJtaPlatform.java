package me.leckie.jka.atomikos.configuration;

import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;
import org.hibernate.engine.transaction.jta.platform.internal.AbstractJtaPlatform;

/**
 * @author Leckie
 * @version AtomikosJtaPlatform.java, v0.1 2019/7/22 3:31
 */
public class AtomikosJtaPlatform extends AbstractJtaPlatform {

  static TransactionManager transactionManager;

  static UserTransaction transaction;

  @Override
  protected TransactionManager locateTransactionManager() {
    return transactionManager;
  }

  @Override
  protected UserTransaction locateUserTransaction() {
    return transaction;
  }
}

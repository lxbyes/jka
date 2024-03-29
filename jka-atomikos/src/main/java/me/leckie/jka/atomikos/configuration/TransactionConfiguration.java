package me.leckie.jka.atomikos.configuration;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.jta.JtaTransactionManager;

/**
 * @author Leckie
 * @version TransactionConfiguration.java, v0.1 2019/7/22 3:30
 */
// @Configuration
@Deprecated
public class TransactionConfiguration {

  @Bean
  public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
    return new PropertySourcesPlaceholderConfigurer();
  }


  //设置JPA特性
  // @Bean
  public JpaVendorAdapter jpaVendorAdapter() {
    HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
    //显示sql
    hibernateJpaVendorAdapter.setShowSql(true);
    //自动生成/更新表
    // hibernateJpaVendorAdapter.setGenerateDdl(true);
    //设置数据库类型
    hibernateJpaVendorAdapter.setDatabase(Database.MYSQL);
    return hibernateJpaVendorAdapter;
  }

  @Bean(name = "userTransaction")
  public UserTransaction userTransaction() throws Throwable {
    UserTransactionImp userTransactionImp = new UserTransactionImp();
    userTransactionImp.setTransactionTimeout(10000);
    return userTransactionImp;
  }

  @Bean(name = "atomikosTransactionManager")
  public TransactionManager atomikosTransactionManager() {
    UserTransactionManager userTransactionManager = new UserTransactionManager();
    userTransactionManager.setForceShutdown(false);
    AtomikosJtaPlatform.transactionManager = userTransactionManager;
    return userTransactionManager;
  }

  @Bean(name = "transactionManager")
  @DependsOn({"userTransaction", "atomikosTransactionManager"})
  public PlatformTransactionManager transactionManager() throws Throwable {
    UserTransaction userTransaction = userTransaction();
    AtomikosJtaPlatform.transaction = userTransaction;
    TransactionManager atomikosTransactionManager = atomikosTransactionManager();
    return new JtaTransactionManager(userTransaction, atomikosTransactionManager);
  }

}

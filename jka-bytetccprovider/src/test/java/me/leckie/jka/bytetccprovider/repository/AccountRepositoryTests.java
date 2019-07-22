package me.leckie.jka.bytetccprovider.repository;

import me.leckie.jka.bytetccprovider.domain.dataobject.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Leckie
 * @version AccountRepositoryTests.java, v0.1 2019/7/20 14:58
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@RunWith(SpringRunner.class)
public class AccountRepositoryTests {

  @Autowired
  private AccountRepository accountRepository;

  @Test
  public void testSave() {
    Account account = new Account();
    account.setName("Leckie");
    accountRepository.save(account);
  }

}
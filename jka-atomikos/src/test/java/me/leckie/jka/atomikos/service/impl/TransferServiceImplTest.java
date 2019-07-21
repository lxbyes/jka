package me.leckie.jka.atomikos.service.impl;

import me.leckie.jka.atomikos.domain.dataobject.account.Account;
import me.leckie.jka.atomikos.domain.dataobject.transfer.Transfer;
import me.leckie.jka.atomikos.service.TransferService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Leckie
 * @version TransferServiceImplTest.java, v0.1 2019/7/21 15:38
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class TransferServiceImplTest {

  @Autowired
  private TransferService transferService;

  @Test
  public void testTransfer() {
    transferService.transfer(1L, 2L, 600D);
  }

  @Test
  public void testAddTransfer() {
    Transfer transfer = new Transfer();
    transferService.addTransfer(transfer);
  }

  @Test
  public void testAddAccount() {
    Account account = new Account();
    account.setAmount(0.0d);
    account.setFrozen(0.0d);
    account.setName("张三");
    transferService.addAccount(account);
    Assert.assertNotNull(account.getId());
  }
}
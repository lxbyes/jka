package me.leckie.jka.bytetccprovider.web.controller;

import java.util.Optional;
import me.leckie.jka.bytetccprovider.domain.dataobject.Account;
import me.leckie.jka.bytetccprovider.repository.AccountRepository;
import me.leckie.jka.bytetccprovider.service.AccountService;
import org.bytesoft.compensable.Compensable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Leckie
 * @version AccountController.java, v0.1 2019/7/21 9:58
 */
@Compensable(interfaceClass = AccountService.class, cancellableKey = "accountServiceCancel", confirmableKey = "accountServiceConfirm")
@RestController
public class AccountController {

  private Logger logger = LoggerFactory.getLogger(AccountController.class);

  @Autowired
  private AccountRepository accountRepository;

  @PostMapping("/increase/{accountId}/{amount}")
  @Transactional
  public void increaseAmount(@PathVariable Long accountId, @PathVariable double amount) {
    logger.info("increase: accountId={}, amount={}", accountId, amount);
    Optional<Account> accountOptional = accountRepository.findById(accountId);
    Assert.isTrue(accountOptional.isPresent(), "id为" + accountId + "的账号不存在");
    Account account = accountOptional.get();
    account.setFrozen(account.getFrozen() + amount);
    accountRepository.save(account);
  }

  @PostMapping("/decrease/{accountId}/{amount}")
  @Transactional
  public void decreaseAmount(@PathVariable Long accountId, @PathVariable double amount) {
    logger.info("decrease: accountId={}, amount={}", accountId, amount);
    Optional<Account> accountOptional = accountRepository.findById(accountId);
    Assert.isTrue(accountOptional.isPresent(), "id为" + accountId + "的账号不存在");
    Account account = accountOptional.get();
    Assert.isTrue(account.getAmount() > amount, "余额不足");
    account.setFrozen(account.getFrozen() - amount);
    accountRepository.save(account);
  }
}

package me.leckie.jka.bytetccprovider.service.impl;

import me.leckie.jka.bytetccprovider.domain.dataobject.Account;
import me.leckie.jka.bytetccprovider.repository.AccountRepository;
import me.leckie.jka.bytetccprovider.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Leckie
 * @version AccountServiceCancel.java, v0.1 2019/7/21 10:18
 */
@Service("accountServiceCancel")
public class AccountServiceCancel implements AccountService {

  private Logger logger = LoggerFactory.getLogger(AccountServiceCancel.class);

  @Autowired
  private AccountRepository accountRepository;

  @Override
  @Transactional
  public void increaseAmount(Long accountId, double amount) {
    logger.info("increase cancel: accountId={}, amount={}", accountId, amount);
    Account account = accountRepository.findById(accountId).get();
    account.setFrozen(account.getFrozen() - amount);
    accountRepository.save(account);
  }

  @Override
  @Transactional
  public void decreaseAmount(Long accountId, double amount) {
    logger.info("decrease cancel: accountId={}, amount={}", accountId, amount);
    Account account = accountRepository.findById(accountId).get();
    account.setFrozen(account.getFrozen() + amount);
    accountRepository.save(account);
  }
}

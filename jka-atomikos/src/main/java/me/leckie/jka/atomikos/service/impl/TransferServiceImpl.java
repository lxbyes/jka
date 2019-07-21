package me.leckie.jka.atomikos.service.impl;

import java.util.Optional;
import me.leckie.jka.atomikos.domain.dataobject.account.Account;
import me.leckie.jka.atomikos.domain.dataobject.transfer.Transfer;
import me.leckie.jka.atomikos.repository.account.AccountRepository;
import me.leckie.jka.atomikos.repository.transfer.TransferRepository;
import me.leckie.jka.atomikos.service.TransferService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * @author Leckie
 * @version TransferServiceImpl.java, v0.1 2019/7/21 15:30
 */
@Service
public class TransferServiceImpl implements TransferService {

  private Logger logger = LoggerFactory.getLogger(TransferServiceImpl.class);

  @Autowired
  private TransferRepository transferRepository;

  @Autowired
  private AccountRepository accountRepository;

  @Transactional
  @Override
  public void transfer(Long source, Long target, Double amount) {
    logger.info("transfer -> source={}, target={}, amount={}", source, target, amount);
    Optional<Account> sourceAccountOptional = accountRepository.findById(source);
    Optional<Account> targetAccountOptional = accountRepository.findById(target);
    Assert.isTrue(sourceAccountOptional.isPresent(), "转出账户有误");
    Assert.isTrue(targetAccountOptional.isPresent(), "转入账户有误");
    Account sourceAccount = sourceAccountOptional.get();
    Account targetAccount = targetAccountOptional.get();
    Assert.isTrue(sourceAccount.getAmount() >= amount, "余额不足");
    sourceAccount.setAmount(sourceAccount.getAmount() - amount);
    targetAccount.setAmount(targetAccount.getAmount() + amount);
    Transfer transfer = new Transfer();
    transfer.setAmount(amount);
    transfer.setSource(source);
    transfer.setTarget(target);
    transferRepository.save(transfer);
    accountRepository.save(sourceAccount);
    accountRepository.save(targetAccount);
  }

  @Override
  @Transactional(transactionManager = "transferTransactionManager")
  public void addTransfer(Transfer transfer) {
    transferRepository.save(transfer);
  }

  @Override
  @Transactional
  public void addAccount(Account account) {
    accountRepository.save(account);
  }
}

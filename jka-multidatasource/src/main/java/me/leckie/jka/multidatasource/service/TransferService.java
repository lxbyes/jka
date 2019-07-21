package me.leckie.jka.multidatasource.service;

import me.leckie.jka.multidatasource.domain.dataobject.account.Account;
import me.leckie.jka.multidatasource.domain.dataobject.transfer.Transfer;

/**
 * @author Leckie
 * @version TransferService.java, v0.1 2019/7/21 15:30
 */
public interface TransferService {

  void transfer(Long source, Long target, Double amount);

  void addTransfer(Transfer transfer);

  void addAccount(Account account);
}

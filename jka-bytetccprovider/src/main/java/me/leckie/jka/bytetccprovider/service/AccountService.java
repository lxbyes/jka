package me.leckie.jka.bytetccprovider.service;

/**
 * @author Leckie
 * @version AccountService.java, v0.1 2019/7/21 10:01
 */
public interface AccountService {

  void increaseAmount(Long accountId, double amount);

  void decreaseAmount(Long accountId, double amount);

}

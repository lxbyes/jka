package me.leckie.jka.bytetccconsumer.service;

/**
 * @author Leckie
 * @version TransferService.java, v0.1 2019/7/21 11:48
 */
public interface TransferService {

  void transfer(Long source, Long target, double amount);

}

package me.leckie.jka.bytetccconsumer.service.impl;

import me.leckie.jka.bytetccconsumer.service.TransferService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Leckie
 * @version TransferService.java, v0.1 2019/7/21 11:48
 */
@Service("transferServiceCancel")
public class TransferServiceCancel implements TransferService {

  private Logger logger = LoggerFactory.getLogger(TransferServiceCancel.class);

  @Transactional
  @Override
  public void transfer(Long source, Long target, double amount) {
    logger.info("transfer cancel: source={}, target={}, amount={}", source, target, amount);
  }

}

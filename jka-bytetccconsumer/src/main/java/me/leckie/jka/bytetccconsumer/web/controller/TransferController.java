package me.leckie.jka.bytetccconsumer.web.controller;

import me.leckie.jka.bytetccconsumer.domain.dataobject.Transfer;
import me.leckie.jka.bytetccconsumer.repository.TransferRepository;
import me.leckie.jka.bytetccconsumer.service.TransferService;
import org.bytesoft.compensable.Compensable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author Leckie
 * @version TransferController.java, v0.1 2019/7/21 11:26
 */
@Compensable(interfaceClass = TransferService.class, cancellableKey = "transferServiceCancel")
@RestController
public class TransferController implements TransferService {

  private Logger logger = LoggerFactory.getLogger(TransferController.class);

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private TransferRepository transferRepository;

  @PostMapping(value = "/transfer/{source}/{target}/{amount}")
  @Transactional
  public void transfer(@PathVariable Long source, @PathVariable Long target, @PathVariable double amount) {
    restTemplate.postForEntity(String.format("http://localhost:8201/decrease/%s/%s", source, amount), null, void.class);
    restTemplate.postForEntity(String.format("http://localhost:8201/increase/%s/%s", target, amount), null, void.class);
    Transfer transfer = new Transfer();
    transfer.setAmount(amount);
    transfer.setSource(source);
    transfer.setTarget(target);
    transferRepository.save(transfer);
    logger.info("transfer: source={}, target={}, amount={}", source, target, amount);
  }

}

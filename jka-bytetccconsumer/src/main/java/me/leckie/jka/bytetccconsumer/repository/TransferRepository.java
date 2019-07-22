package me.leckie.jka.bytetccconsumer.repository;

import me.leckie.jka.bytetccconsumer.domain.dataobject.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Leckie
 * @version TransferRepository.java, v0.1 2019/7/21 11:42
 */
public interface TransferRepository extends JpaRepository<Transfer, Long> {

}

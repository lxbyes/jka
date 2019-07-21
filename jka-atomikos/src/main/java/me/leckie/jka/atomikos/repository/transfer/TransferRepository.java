package me.leckie.jka.atomikos.repository.transfer;

import me.leckie.jka.atomikos.domain.dataobject.transfer.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Leckie
 * @version TransferRepository.java, v0.1 2019/7/21 15:29
 */
public interface TransferRepository extends JpaRepository<Transfer, Long> {

}

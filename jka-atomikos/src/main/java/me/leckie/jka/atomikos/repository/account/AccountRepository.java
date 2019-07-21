package me.leckie.jka.atomikos.repository.account;

import me.leckie.jka.atomikos.domain.dataobject.account.Account;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Leckie
 * @version AccountRepository.java, v0.1 2019/7/21 15:28
 */
public interface AccountRepository extends CrudRepository<Account, Long> {

}

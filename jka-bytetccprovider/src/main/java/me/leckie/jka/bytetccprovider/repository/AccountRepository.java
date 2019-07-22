package me.leckie.jka.bytetccprovider.repository;

import me.leckie.jka.bytetccprovider.domain.dataobject.Account;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Leckie
 * @version AccountRepository.java, v0.1 2019/7/20 14:57
 */
public interface AccountRepository extends JpaRepository<Account, Long> {

}

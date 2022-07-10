package com.palm.practical.adapters.out.persistence.repositories;

import com.palm.practical.adapters.out.persistence.entities.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

  @Lock(LockModeType.PESSIMISTIC_WRITE)
  Optional<AccountEntity> findById(Long id);
}

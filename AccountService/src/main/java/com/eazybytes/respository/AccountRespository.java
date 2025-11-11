package com.eazybytes.respository;

import com.eazybytes.Entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRespository extends JpaRepository<Accounts, Long>
{
    Optional<Accounts> findByCustomerId(Long customerId);
}

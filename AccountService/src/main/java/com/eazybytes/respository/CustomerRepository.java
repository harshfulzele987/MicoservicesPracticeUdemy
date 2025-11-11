package com.eazybytes.respository;

import com.eazybytes.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.JpaRepositoryNameSpaceHandler;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer , Long> {

    Optional<Customer> findByMobileNumber(String mobileNumber);
}

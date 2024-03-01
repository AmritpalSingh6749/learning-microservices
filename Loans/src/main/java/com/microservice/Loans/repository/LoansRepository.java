package com.microservice.Loans.repository;

import com.microservice.Loans.entity.Loans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoansRepository extends JpaRepository<Loans, Long> {
    Optional<List<Loans>> findAllByMobileNumber(String mobileNumber);

    Optional<Loans> findByLoanNumber(String loanNumber);
}

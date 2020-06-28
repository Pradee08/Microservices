package com.centrica.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.centrica.model.AccountDatabase;

public interface AccountRepository extends JpaRepository<AccountDatabase, String> {
	Optional<AccountDatabase> findById(String id);
	Optional<List<AccountDatabase>> findByCustomerId(int customerId);
}

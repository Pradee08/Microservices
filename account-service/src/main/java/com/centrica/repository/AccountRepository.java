package com.centrica.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.centrica.model.AccountDatabase;

public interface AccountRepository extends JpaRepository<AccountDatabase, String> {
	Optional<AccountDatabase> findById(String id);
}
package com.centrica.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.centrica.model.CustomerDatabase;

public interface CustomerRepository extends JpaRepository<CustomerDatabase, Integer> {
	Optional<List<CustomerDatabase>> findByUcrn(String ucrn);
	Optional<CustomerDatabase> findById(int id);
}

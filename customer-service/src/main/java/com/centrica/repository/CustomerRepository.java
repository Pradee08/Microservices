package com.centrica.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.centrica.model.CustomerDatabase;

public interface CustomerRepository extends JpaRepository<CustomerDatabase, Integer> {
	List<CustomerDatabase> findByUcrn(String ucrn);
	CustomerDatabase findById(int id);
	
	
}

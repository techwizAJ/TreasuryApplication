package com.pankhudi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pankhudi.model.Customers;


@Repository
public interface CustomerRepository extends JpaRepository<Customers, Integer>{

	@Query("select s from Customers s where s.name = :name")
	Optional<Customers> findByName(@Param("name") String name);
	
	@Query("select s from Customers s where s.region = :region")
	List<Customers> findPkIdBasedOnRegion(@Param("region") String region);

}

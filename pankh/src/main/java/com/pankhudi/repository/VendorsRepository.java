package com.pankhudi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pankhudi.model.Vendors;

@Repository
public interface VendorsRepository extends JpaRepository<Vendors, Integer>{

	@Query("select s from Vendors s where s.shortName = :shortName")
	Optional<Vendors> findByName(@Param("shortName") String shortName);
	
	@Query("select s from Vendors s where s.region = :region")
	List<Vendors> findPkIdBasedOnRegion(@Param("region") String region);
}

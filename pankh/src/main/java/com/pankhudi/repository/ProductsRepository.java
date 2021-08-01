package com.pankhudi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pankhudi.model.Products;

@Repository
public interface ProductsRepository  extends JpaRepository<Products, Integer>{

	@Query("select s from Products s where s.shortName = :shortName")
	Optional<Products> findByName(@Param("shortName") String shortName);
}

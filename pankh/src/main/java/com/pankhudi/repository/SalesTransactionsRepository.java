package com.pankhudi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pankhudi.model.SalesTransactions;

@Repository
public interface SalesTransactionsRepository extends JpaRepository<SalesTransactions, Integer>{

}

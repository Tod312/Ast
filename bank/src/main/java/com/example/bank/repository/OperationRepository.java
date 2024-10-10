package com.example.bank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.bank.model.Operation;

public interface OperationRepository extends CrudRepository<Operation, Long>{

	@Query("select o from Operation o where o.payer.id = :depositeId or o.recipient.id = :depositeId")
	List<Operation> findByDepositeId(@Param("depositeId") Long depositeId);
}

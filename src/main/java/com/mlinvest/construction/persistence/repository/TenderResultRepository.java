package com.mlinvest.construction.persistence.repository;

import com.mlinvest.construction.persistence.model.TenderResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TenderResultRepository extends JpaRepository<TenderResult, Long> {
}

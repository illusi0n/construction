package com.mlinvest.construction.persistence.repository;

import com.mlinvest.construction.persistence.model.Tender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TenderRepository extends JpaRepository<Tender, Long> {
}

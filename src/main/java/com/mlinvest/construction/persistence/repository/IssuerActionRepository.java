package com.mlinvest.construction.persistence.repository;

import com.mlinvest.construction.persistence.model.IssuerAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssuerActionRepository extends JpaRepository<IssuerAction, Long> {
}

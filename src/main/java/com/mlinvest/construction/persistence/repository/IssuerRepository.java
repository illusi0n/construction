package com.mlinvest.construction.persistence.repository;

import com.mlinvest.construction.persistence.model.Issuer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssuerRepository extends JpaRepository<Issuer, Long> {
}

package com.mlinvest.construction.persistence.repository;

import com.mlinvest.construction.persistence.model.ActionStatus;
import com.mlinvest.construction.persistence.model.IssuerAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IssuerActionRepository extends JpaRepository<IssuerAction, Long> {
    Optional<IssuerAction> findFirstByStatusOrderByCreatedAtAsc(ActionStatus actionStatus);
}

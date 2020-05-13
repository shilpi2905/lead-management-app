package org.codejudge.sb.h2.repository;

import org.codejudge.sb.h2.entity.LeadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeadRepository extends JpaRepository<LeadEntity, Integer>  {

}

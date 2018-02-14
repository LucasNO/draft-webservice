package com.draft.back.javentus.repository;

import com.draft.back.javentus.model.HistoricoConfrontos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author lucas
 */
@Repository
public interface HistoricoConfrontosRepository extends JpaRepository<HistoricoConfrontos, Integer>{
    
}

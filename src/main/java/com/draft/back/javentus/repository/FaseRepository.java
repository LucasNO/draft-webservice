package com.draft.back.javentus.repository;

import com.draft.back.javentus.model.Fase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author lucas
 */
@Repository
public interface FaseRepository extends JpaRepository<Fase, Integer>{
    
    @Query("select fase from Fase fase where fase.descricao = :tipo")
    public Fase carregarFaseDescricao(@Param("tipo") String u);
}

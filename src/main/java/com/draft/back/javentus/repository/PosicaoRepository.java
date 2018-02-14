package com.draft.back.javentus.repository;

import com.draft.back.javentus.model.Posicao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author lucas
 */
@Repository
public interface PosicaoRepository extends JpaRepository<Posicao, Integer>{
    
}

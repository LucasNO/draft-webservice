package com.draft.back.javentus.repository;

import com.draft.back.javentus.model.Jogador;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author lucas
 */
@Repository
public interface JogadorRepository extends JpaRepository<Jogador, Integer>{
    
     @Query("select jog from Jogador jog where jog.tim is null")
    public List<Jogador> carregarFreeAgents();
    
}

package com.draft.back.javentus.repository;

import com.draft.back.javentus.model.Torneio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author lucas
 */
@Repository
public interface TorneioRepository extends JpaRepository<Torneio, Integer>{
    
    @Query("select tor from Torneio tor where tor.ativo = 1")
    public Torneio carregarTorneioAtivo();
}

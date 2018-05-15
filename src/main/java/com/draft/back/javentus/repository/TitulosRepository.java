package com.draft.back.javentus.repository;

import com.draft.back.javentus.dto.TabelaTitulos;
import com.draft.back.javentus.model.Titulos;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author lucas
 */
@Repository
public interface TitulosRepository extends JpaRepository<Titulos, Integer>{
        
    @Query("select new com.draft.back.javentus.dto.TabelaTitulos(t.usuario.nome, t.posicao, COUNT(t)) FROM Titulos t GROUP BY t.usuario.nome, t.posicao")
    public List<TabelaTitulos> carregarTitulos();
}

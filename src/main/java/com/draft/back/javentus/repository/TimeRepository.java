package com.draft.back.javentus.repository;

import com.draft.back.javentus.model.Time;
import com.draft.back.javentus.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TimeRepository extends JpaRepository<Time, Integer>{
    
    @Query("select tim from Time tim where tim.usr = :usuario")
    public Time carregarTimeUsuario(@Param("usuario") Usuario u);

    @Query("select tim from Time tim where tim.usr <> :usuario")
    public List<Time> carregarTimesAdversarios(@Param("usuario") Usuario u);
        
    
}

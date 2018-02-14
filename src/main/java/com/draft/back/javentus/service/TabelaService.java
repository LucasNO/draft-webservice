package com.draft.back.javentus.service;

import com.draft.back.javentus.model.Tabela;
import com.draft.back.javentus.repository.TabelaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lucas
 */
@Service
public class TabelaService {
    
    @Autowired
    private TabelaRepository tabelaRepository;
    
    public List<Tabela> findAll(){
        return tabelaRepository.findAll();
    }
    
    public Tabela findOne(Integer id){
        return tabelaRepository.findOne(id);
    }
    
    public Tabela save(Tabela tabela){
        return tabelaRepository.save(tabela);
    }
    
    public Tabela update(Tabela tabela){
        return tabelaRepository.saveAndFlush(tabela);
    }
    
    public void delete(Integer id) {
        tabelaRepository.delete(id);
    }
}

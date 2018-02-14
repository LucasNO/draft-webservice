package com.draft.back.javentus.service;

import com.draft.back.javentus.model.Transferencia;
import com.draft.back.javentus.repository.TransferenciaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lucas
 */
@Service
public class TransferenciaService {
    
    @Autowired
    private TransferenciaRepository tabelaRepository;
    
    public List<Transferencia> findAll(){
        return tabelaRepository.findAll();
    }
    
    public Transferencia findOne(Integer id){
        return tabelaRepository.findOne(id);
    }
    
    public Transferencia save(Transferencia transferencia){
        return tabelaRepository.save(transferencia);
    }
    
    public Transferencia update(Transferencia transferencia){
        return tabelaRepository.saveAndFlush(transferencia);
    }
    
    public void delete(Integer id) {
        tabelaRepository.delete(id);
    }
}

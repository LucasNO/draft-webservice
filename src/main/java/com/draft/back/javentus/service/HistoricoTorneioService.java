package com.draft.back.javentus.service;

import com.draft.back.javentus.model.HistoricoTorneio;
import com.draft.back.javentus.repository.HistoricoTorneioRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lucas
 */
@Service
public class HistoricoTorneioService {
    
    @Autowired
    private HistoricoTorneioRepository historicoTorneioRepository;
    
    public List<HistoricoTorneio> findAll(){
        return historicoTorneioRepository.findAll();
    }
    
    public HistoricoTorneio findOne(Integer id){
        return historicoTorneioRepository.findOne(id);
    }
    
    public HistoricoTorneio save(HistoricoTorneio historicoTorneio){
        return historicoTorneioRepository.save(historicoTorneio);
    }
    
    public HistoricoTorneio update(HistoricoTorneio historicoTorneio){
        return historicoTorneioRepository.saveAndFlush(historicoTorneio);
    }
    
    public void delete(Integer id) {
        historicoTorneioRepository.delete(id);
    }
}

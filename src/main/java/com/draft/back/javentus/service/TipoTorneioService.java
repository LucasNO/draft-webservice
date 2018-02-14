package com.draft.back.javentus.service;

import com.draft.back.javentus.model.TipoTorneio;
import com.draft.back.javentus.repository.TipoTorneioRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lucas
 */
@Service
public class TipoTorneioService {
    
    @Autowired
    private TipoTorneioRepository tipoTorneioRepository;
    
    public List<TipoTorneio> findAll(){
        return tipoTorneioRepository.findAll();
    }
    
    public TipoTorneio findOne(Integer id){
        return tipoTorneioRepository.findOne(id);
    }
    
    public TipoTorneio save(TipoTorneio torneio){
        return tipoTorneioRepository.save(torneio);
    }
    
    public TipoTorneio update(TipoTorneio torneio){
        return tipoTorneioRepository.saveAndFlush(torneio);
    }
    
    public void delete(Integer id) {
        tipoTorneioRepository.delete(id);
    }
}

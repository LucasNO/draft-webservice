package com.draft.back.javentus.service;

import com.draft.back.javentus.model.Confronto;
import com.draft.back.javentus.repository.ConfrontoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lucas
 */
@Service
public class ConfrontoService {
    
    @Autowired
    private ConfrontoRepository confrontoRepository;
    
    public List<Confronto> findAll(){
        return confrontoRepository.findAll();
    }
    
    public Confronto findOne(Integer id){
        return confrontoRepository.findOne(id);
    }
    
    public Confronto save(Confronto posicao){
        return confrontoRepository.save(posicao);
    }
    
    public Confronto update(Confronto posicao){
        return confrontoRepository.saveAndFlush(posicao);
    }
    
    public void delete(Integer id) {
        confrontoRepository.delete(id);
    }
}

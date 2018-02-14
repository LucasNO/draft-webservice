package com.draft.back.javentus.service;

import com.draft.back.javentus.model.Fase;
import com.draft.back.javentus.repository.FaseRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lucas
 */
@Service
public class FaseService {
    
    @Autowired
    private FaseRepository faseRepository;
    
    public List<Fase> findAll(){
        return faseRepository.findAll();
    }
    
    public Fase findOne(Integer id){
        return faseRepository.findOne(id);
    }
    
    public Fase findByDescricao(String descricao){
        return faseRepository.carregarFaseDescricao(descricao);
    }
    
    public Fase save(Fase posicao){
        return faseRepository.save(posicao);
    }
    
    public Fase update(Fase posicao){
        return faseRepository.saveAndFlush(posicao);
    }
    
    public void delete(Integer id) {
        faseRepository.delete(id);
    }
}

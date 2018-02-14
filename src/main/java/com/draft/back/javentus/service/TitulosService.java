package com.draft.back.javentus.service;

import com.draft.back.javentus.model.Titulos;
import com.draft.back.javentus.repository.TitulosRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lucas
 */
@Service
public class TitulosService {
    
    @Autowired
    private TitulosRepository titulosRepository;
    
    public List<Titulos> findAll(){
        return titulosRepository.findAll();
    }
    
    public List<Titulos> listarTitulos(){
        return titulosRepository.findByOrderByPrimeiroDescSegundoDescTerceiroDescQuartoDescQuintoDesc();
    }
    
    public Titulos findOne(Integer id){
        return titulosRepository.findOne(id);
    }
    
    public Titulos save(Titulos titulos){
        return titulosRepository.save(titulos);
    }
    
    public Titulos update(Titulos titulos){
        return titulosRepository.saveAndFlush(titulos);
    }
    
    public void delete(Integer id) {
        titulosRepository.delete(id);
    }
}

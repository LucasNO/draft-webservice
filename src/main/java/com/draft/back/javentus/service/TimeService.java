package com.draft.back.javentus.service;

import com.draft.back.javentus.model.Time;
import com.draft.back.javentus.model.Usuario;
import com.draft.back.javentus.repository.TimeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lucas
 */
@Service
public class TimeService {

    @Autowired
    private TimeRepository timeRepository;

    public Time carregarTimeUsuario(Usuario u){
        return timeRepository.carregarTimeUsuario(u);
    }
    
    public List<Time> findAll(){
        return timeRepository.findAll();
    }

    public List<Time> findAllAdversarios(Usuario u){
        return timeRepository.carregarTimesAdversarios(u);
    }
    
    public Time findOne(Integer id){
        return timeRepository.findOne(id);
    }

    public Time save(Time time) {
        return timeRepository.save(time);
    }

    public Time update(Time time) {
        return timeRepository.saveAndFlush(time);
    }

    public void delete(Integer id) {
        timeRepository.delete(id);
    }

    public boolean verificarTimeNull(Time time) {
        if (time != null && time.getId() != null) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    public boolean verificarTimeJaCadastrado(Integer id) {
        if (id != null) {
            Time time = findOne(id);
            return !verificarTimeNull(time);
        }else{
            return Boolean.FALSE;
        }
    }
}

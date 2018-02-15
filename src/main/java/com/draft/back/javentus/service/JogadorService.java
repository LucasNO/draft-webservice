package com.draft.back.javentus.service;

import com.draft.back.javentus.model.Jogador;
import com.draft.back.javentus.model.Time;
import com.draft.back.javentus.repository.JogadorRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lucas
 */
@Service
public class JogadorService {
    
    @Autowired
    private JogadorRepository jogadorRepository;
    
    public List<Jogador> findAll(){
        return jogadorRepository.findAll();
    }
    
    public Jogador findOne(Integer id){
        return jogadorRepository.findOne(id);
    }
    
    public Jogador save(Jogador jogador) {
        return jogadorRepository.save(jogador);
    }

    public Jogador update(Jogador jogador) {
        return jogadorRepository.saveAndFlush(jogador);
    }
    
    public void delete(Integer id) {
        jogadorRepository.delete(id);
    }
    
    public List<Jogador> carregarFreeAgents(){
        return jogadorRepository.carregarFreeAgents();
    }
    
    public void contratarJogador(Time t, Integer idJogador){
        Jogador j = findOne(idJogador);
        j.setTim(t);
        save(j);
    }
    
    public void dispensarJogador(Integer idJogador){
        Jogador j = findOne(idJogador);
        j.setTim(null);
        save(j);
    }

    public boolean verificarJogadorNull(Jogador jogador) {
        if (jogador != null && jogador.getId() != null) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    public boolean verificarJogadorJaCadastrada(Integer id) {
        if (id != null) {
            Jogador jogador = findOne(id);
            return !verificarJogadorNull(jogador);
        }else{
            return Boolean.FALSE;
        }
    }
}

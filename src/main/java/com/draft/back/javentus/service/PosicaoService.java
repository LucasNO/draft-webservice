package com.draft.back.javentus.service;

import com.draft.back.javentus.model.Posicao;
import com.draft.back.javentus.repository.PosicaoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lucas
 */
@Service
public class PosicaoService {

    @Autowired
    private PosicaoRepository posicaoRepository;

    public List<Posicao> findAll() {
        return posicaoRepository.findAll();
    }

    public Posicao findOne(Integer id) {
        return posicaoRepository.findOne(id);
    }

    public Posicao save(Posicao posicao) {
        return posicaoRepository.save(posicao);
    }

    public Posicao update(Posicao posicao) {
        return posicaoRepository.saveAndFlush(posicao);
    }

    public void delete(Integer id) {
        posicaoRepository.delete(id);
    }

    public boolean verificarPosicaoNull(Posicao posicao) {
        if (posicao != null && posicao.getId() != null) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    public boolean posicaoJaCadastrada(Integer id) {
        if (id != null) {
            Posicao posicao = findOne(id);
            return !verificarPosicaoNull(posicao);
        }else{
            return Boolean.FALSE;
        }
    }
}

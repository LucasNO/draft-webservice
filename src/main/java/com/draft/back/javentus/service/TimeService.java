package com.draft.back.javentus.service;

import com.draft.back.javentus.dto.JogadorDto;
import com.draft.back.javentus.dto.TimeDto;
import com.draft.back.javentus.model.Jogador;
import com.draft.back.javentus.model.Time;
import com.draft.back.javentus.model.Usuario;
import com.draft.back.javentus.repository.TimeRepository;
import java.util.ArrayList;
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
    @Autowired

    private JogadorService jogadorService;

    public Time carregarTimeUsuario(Usuario u) {
        return timeRepository.carregarTimeUsuario(u);
    }

    public List<Time> findAll() {
        return timeRepository.findAll();
    }

    public List<Time> findAllAdversarios(Usuario u) {
        return timeRepository.carregarTimesAdversarios(u);
    }

    public Time findOne(Integer id) {
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
        } else {
            return Boolean.FALSE;
        }
    }

    public List<TimeDto> preencheDtoTimes(List<Time> times) {
        List<TimeDto> list = new ArrayList<>();
        for (Time t : times) {
            TimeDto dto = TimeDto.builder().idTime(t.getId()).nomeTime(t.getNome()).build();
            dto.setJogadores(jogadorService.preencheDtoJogador(t));
            list.add(dto);
        }
        return list;
    }
}

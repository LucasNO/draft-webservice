package com.draft.back.javentus.controller;

import com.draft.back.javentus.dto.JogadorDto;
import com.draft.back.javentus.dto.TimeDto;
import com.draft.back.javentus.model.Time;
import com.draft.back.javentus.model.Usuario;
import com.draft.back.javentus.service.JogadorService;
import com.draft.back.javentus.service.TimeService;
import com.draft.back.javentus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/draft")
public class TimeController {

    @Autowired
    private TimeService timeService;
    
    @Autowired
    private JogadorService jogadorService;

    @Autowired
    private UserService userService;

    @GetMapping("/times")
    public ResponseEntity<List<Time>> getTimes() {
        List<Time> times = timeService.findAll();
        if (times.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(times);
    }


    @GetMapping("/times-adversarios/{nome}")
    public ResponseEntity<List<TimeDto>> getTimesAdversarios(@PathVariable("nome") String nome) {
        Usuario usuario = userService.findUserByNome(nome);
        List<Time> times = timeService.findAllAdversarios(usuario);
        if (times.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(timeService.preencheDtoTimes(times));
    }

    @GetMapping("/time/{nome}")
    public ResponseEntity<List<JogadorDto>> getTimeById(@PathVariable("nome") String nome){

        Usuario u = userService.findUserByNome(nome);
        
        Time time = timeService.carregarTimeUsuario(u);

        if (!timeService.verificarTimeNull(time)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(jogadorService.preencheDtoJogador(time.getJogadorList()));
    }

    @Transactional
    @PostMapping(path="/time")
    public ResponseEntity<Void> addTime(@RequestBody Time time, UriComponentsBuilder ucBuilder) {
        if (timeService.verificarTimeJaCadastrado(time.getId())) {
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(ucBuilder.path("/draft/time/{id}").buildAndExpand(time.getId()).toUri());
            return ResponseEntity.status(HttpStatus.CONFLICT).headers(headers).build();
        } else {
            timeService.save(time);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(ucBuilder.path("/draft/time/{id}").buildAndExpand(time.getId()).toUri());
            return ResponseEntity.status(HttpStatus.CREATED).headers(headers).build();
        }
    }

    @Transactional
    @DeleteMapping(path="/time/{id}")
    public ResponseEntity<Time> deleteTime(@PathVariable("id") Integer id) {

        Time time = timeService.findOne(id);

        if (!timeService.verificarTimeNull(time)) {
            return ResponseEntity.noContent().build();
        }
        timeService.delete(time.getId());
        return ResponseEntity.ok(time);
    }

    @Transactional
    @PutMapping(path="/time")
    public ResponseEntity<Void> updateTime(@RequestBody Time time, UriComponentsBuilder ucBuilder) {
        if (!timeService.verificarTimeNull(time)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            timeService.update(time);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(ucBuilder.path("/draft/time/{id}").buildAndExpand(time.getId()).toUri());
            return ResponseEntity.status(HttpStatus.OK).headers(headers).build();
        }
    }
}

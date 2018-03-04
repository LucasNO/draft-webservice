package com.draft.back.javentus.controller;

import com.draft.back.javentus.model.Time;
import com.draft.back.javentus.model.Usuario;
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
    private UserService userService;

    @GetMapping("/times")
    public ResponseEntity<List<Time>> getTimes() {
        List<Time> times = timeService.findAll();
        if (times.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(times);
    }


    @GetMapping("/times-adversarios/{idUsuario}")
    public ResponseEntity<List<Time>> getTimesAdversarios(@PathVariable("idUsuario") Integer idUsuario) {
        Usuario usuario = userService.findOne(idUsuario);
        List<Time> times = timeService.findAllAdversarios(usuario);
        if (times.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(times);
    }

    @GetMapping("/time/{id}")
    public ResponseEntity<Time> getTimeById(@PathVariable("id") Integer id) {

        Time time = timeService.findOne(id);

        if (!timeService.verificarTimeNull(time)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(time);
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

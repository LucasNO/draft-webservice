package com.draft.back.javentus.controller;

import com.draft.back.javentus.model.Jogador;
import com.draft.back.javentus.model.Time;
import com.draft.back.javentus.model.Usuario;
import com.draft.back.javentus.service.JogadorService;
import java.util.List;
import javax.transaction.Transactional;

import com.draft.back.javentus.service.TimeService;
import com.draft.back.javentus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author lucas
 */
@CrossOrigin
@RestController
@RequestMapping("/draft")
public class JogadorController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private JogadorService jogadorService;

    @Autowired
    private TimeService timeService;

    @GetMapping("/jogadores")
    public ResponseEntity<List<Jogador>> getJogadores() {
        List<Jogador> jogadores = jogadorService.findAll();
        if (jogadores.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(jogadores);
    }

    @GetMapping("/free-agents")
    public ResponseEntity<List<Jogador>> getFreeAgents() {
        List<Jogador> jogadores = jogadorService.carregarFreeAgents();
        if (jogadores.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(jogadores);
    }
    
    @GetMapping("/jogador/{id}")
    public ResponseEntity<Jogador> getJogadorById(@PathVariable("id") Integer id) {

        Jogador jogador = jogadorService.findOne(id);

        if (!jogadorService.verificarJogadorNull(jogador)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(jogador);
    }
    
    @Transactional
    @PostMapping(path="/jogador")
    public ResponseEntity<Void> addJogador(@RequestBody Jogador jogador, UriComponentsBuilder ucBuilder) {
        if (jogadorService.verificarJogadorJaCadastrada(jogador.getId())) {
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(ucBuilder.path("/draft/jogador/{id}").buildAndExpand(jogador.getId()).toUri());
            return ResponseEntity.status(HttpStatus.CONFLICT).headers(headers).build();
        } else {
            jogadorService.save(jogador);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(ucBuilder.path("/draft/jogador/{id}").buildAndExpand(jogador.getId()).toUri());
            return ResponseEntity.status(HttpStatus.CREATED).headers(headers).build();
        }
    }
    
    @Transactional
    @DeleteMapping(path="/jogador/{id}")
    public ResponseEntity<Jogador> deleteJogador(@PathVariable("id") Integer id) {

        Jogador jogador = jogadorService.findOne(id);

        if (!jogadorService.verificarJogadorNull(jogador)) {
            return ResponseEntity.noContent().build();
        }
        jogadorService.delete(jogador.getId());
        return ResponseEntity.ok(jogador);
    }
    
    @Transactional
    @PutMapping(path="/jogador")
    public ResponseEntity<Void> updateJogador(@RequestBody Jogador jogador, UriComponentsBuilder ucBuilder) {
        if (!jogadorService.verificarJogadorNull(jogador)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            jogadorService.update(jogador);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(ucBuilder.path("/draft/jogador/{id}").buildAndExpand(jogador.getId()).toUri());
            return ResponseEntity.status(HttpStatus.OK).headers(headers).build();
        }
    }

    @Transactional
    @PostMapping(path="/contratar-jogador/{idJogador}/{nome}")
    public ResponseEntity<Jogador> contratarJogador(@PathVariable("idJogador") Integer idJogador, @PathVariable("nome") String nome) {

        Jogador jogador = jogadorService.findOne(idJogador);

        if (!jogadorService.verificarJogadorNull(jogador)) {
            return ResponseEntity.noContent().build();
        }
        
        Usuario usuario  = userService.findUserByNome(nome);
        
        Time time = timeService.carregarTimeUsuario(usuario);
        if (!timeService.verificarTimeNull(time)) {
            return ResponseEntity.noContent().build();
        }

        jogadorService.contratarJogador(time, jogador);
        return ResponseEntity.ok(jogador);
    }

    @Transactional
    @PostMapping(path="/dispensar-jogador/{idJogador}")
    public ResponseEntity<Jogador> dispensarJogador(@PathVariable("idJogador") Integer idJogador) {

        Jogador jogador = jogadorService.findOne(idJogador);

        if (!jogadorService.verificarJogadorNull(jogador)) {
            return ResponseEntity.noContent().build();
        }


        jogadorService.dispensarJogador(jogador);
        return ResponseEntity.ok(jogador);
    }
}

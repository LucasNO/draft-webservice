package com.draft.back.javentus.controller;

import com.draft.back.javentus.model.Posicao;
import com.draft.back.javentus.service.PosicaoService;
import java.util.List;
import javax.transaction.Transactional;
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

public class PosicaoController {

    @Autowired
    private PosicaoService posicaoService;

    @GetMapping("/posicoes")
    public ResponseEntity<List<Posicao>> getPosicoes() {
        List<Posicao> posicoes = posicaoService.findAll();
        if (posicoes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(posicoes);
    }

    @GetMapping("/posicao/{id}")
    public ResponseEntity<Posicao> getPosicaoById(@PathVariable("id") Integer id) {

        Posicao posicao = posicaoService.findOne(id);

        if (!posicaoService.verificarPosicaoNull(posicao)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(posicao);
    }

    @Transactional
    @PostMapping(path = "/posicao")
    public ResponseEntity<Void> addPosicao(@RequestBody Posicao posicao, UriComponentsBuilder ucBuilder) {
        System.out.println("Chegou");
        if (posicaoService.posicaoJaCadastrada(posicao.getDescricao())) {
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(ucBuilder.path("/draft/posicao/{id}").buildAndExpand(posicao.getId()).toUri());
            return ResponseEntity.status(HttpStatus.CONFLICT).headers(headers).build();
        } else {
            posicaoService.save(posicao);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(ucBuilder.path("/draft/posicao/{id}").buildAndExpand(posicao.getId()).toUri());
            return ResponseEntity.status(HttpStatus.CREATED).headers(headers).build();
        }
    }

    @Transactional
    @DeleteMapping(path = "/posicao/{id}")
    public ResponseEntity<Posicao> deletePosicao(@PathVariable("id") Integer id) {

        Posicao posicao = posicaoService.findOne(id);

        if (!posicaoService.verificarPosicaoNull(posicao)) {
            return ResponseEntity.noContent().build();
        }
        posicaoService.delete(posicao.getId());
        return ResponseEntity.ok(posicao);
    }

    @Transactional
    @PutMapping(path = "/posicao")
    public ResponseEntity<Void> updatePosicao(@RequestBody Posicao posicao, UriComponentsBuilder ucBuilder) {
        if (!posicaoService.verificarPosicaoNull(posicao)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            posicaoService.update(posicao);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(ucBuilder.path("/draft/posicao/{id}").buildAndExpand(posicao.getId()).toUri());
            return ResponseEntity.status(HttpStatus.OK).headers(headers).build();
        }
    }
}

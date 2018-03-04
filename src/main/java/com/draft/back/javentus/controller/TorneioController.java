package com.draft.back.javentus.controller;

import com.draft.back.javentus.model.TipoTorneio;
import com.draft.back.javentus.model.Titulos;
import com.draft.back.javentus.model.Torneio;
import com.draft.back.javentus.service.TipoTorneioService;
import com.draft.back.javentus.service.TitulosService;
import com.draft.back.javentus.service.TorneioService;
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
public class TorneioController {

    @Autowired
    private TitulosService titulosService;

    @Autowired
    private TipoTorneioService tipoTorneioService;

    @Autowired
    private TorneioService torneioService;

    @GetMapping("/titulos")
    public ResponseEntity<List<Titulos>> getTimes() {
        List<Titulos> titulos = titulosService.listarTitulos();
        if (titulos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(titulos);
    }


    @GetMapping("/torneio-ativo")
    public ResponseEntity<Torneio> getTorneioAtivo() {
        Torneio torneioAtivo= torneioService.carregarTorneioAtivo();
        if (torneioAtivo != null && torneioAtivo.getId() != null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(torneioAtivo);
    }

    @GetMapping("/tipos-torneio")
    public ResponseEntity<List<TipoTorneio>> getTipoTorneio() {
        List<TipoTorneio> tiposTorneio= tipoTorneioService.findAll();
        if (tiposTorneio.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tiposTorneio);
    }
    @Transactional
    @PostMapping(path = "/torneio")
    public ResponseEntity<Void> addTorneio(@RequestBody Torneio torneio, UriComponentsBuilder ucBuilder) {
        if (torneio.getId() != null) {
            torneioService.update(torneio);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(ucBuilder.path("/draft/torneio-ativo").buildAndExpand().toUri());
            return ResponseEntity.status(HttpStatus.OK).headers(headers).build();
        } else {
            torneioService.gerarConfrontosTorneio(torneio.getTto());
            torneio.setAtivo(Boolean.TRUE);
            torneioService.save(torneio);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(ucBuilder.path("/draft/posicao/{id}").buildAndExpand(torneio.getId()).toUri());
            return ResponseEntity.status(HttpStatus.CREATED).headers(headers).build();
        }
    }

}

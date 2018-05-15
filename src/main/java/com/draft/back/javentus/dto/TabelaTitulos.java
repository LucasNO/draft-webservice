package com.draft.back.javentus.dto;

import lombok.Data;

/**
 *
 * @author lucas
 */

@Data
public class TabelaTitulos {
    
    private String nome;
    
    private Integer posicao;
    
    private Long quantidade;

    public TabelaTitulos(String nome, Integer posicao, Long quantidade) {
        this.nome = nome;
        this.posicao = posicao;
        this.quantidade = quantidade;
    }

}

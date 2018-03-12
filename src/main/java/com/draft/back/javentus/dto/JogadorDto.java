package com.draft.back.javentus.dto;

import lombok.Builder;
import lombok.Data;

/**
 *
 * @author lucas
 */
@Data
@Builder
public class JogadorDto {
    
    private int idJogador;
    
    private String nomeJogador;
    
    private int overall;

    private String posicao;

    private int idPosicao;

    private String nomeTime;

    private int idTime;
    
}

package com.draft.back.javentus.dto;

import java.util.List;
import lombok.Builder;
import lombok.Data;

/**
 *
 * @author lucas
 */
@Data
@Builder
public class TimeDto {
    
    private List<JogadorDto> jogadores;
    
    private int idTime;
    
    private String nomeTime;
}

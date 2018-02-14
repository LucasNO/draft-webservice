package com.draft.back.javentus.dto;

import com.draft.back.javentus.model.Confronto;
import java.util.List;
import lombok.Data;

/**
 *
 * @author lucas
 */
@Data
public class Rodada {
    
    private List<Confronto> confrontos;
    
    private Integer qtdJogosPorRodada;
    
}

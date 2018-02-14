package com.draft.back.javentus.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 *
 * @author lucas
 */
@Data
@Entity
@Table(name = "htr_historico_torneio")
public class HistoricoTorneio implements Serializable {

    private static final long serialVersionUID = 3850783810131851037L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "htr_id")
    private Integer id;
    
    @NotNull
    @Column(name = "htr_posicao")
    private int posicao;
    
    @Column(name = "htr_pontos")
    private Integer pontos;
    
    @Column(name = "htr_jogos")
    private Integer jogos;
    
    @Column(name = "htr_vitorias")
    private Integer vitorias;
    
    @Column(name = "htr_empates")
    private Integer empates;
    
    @Column(name = "htr_derrotas")
    private Integer derrotas;
    
    @Column(name = "htr_gols_pro")
    private Integer golsPro;
    
    @Column(name = "htr_gols_contra")
    private Integer golsContra;
    
    @Column(name = "htr_saldo_gols")
    private Integer saldoGols;
    
    @JoinColumn(name = "tim_id", referencedColumnName = "tim_id")
    @ManyToOne
    private Time time;
    
    @JoinColumn(name = "tor_id", referencedColumnName = "tor_id")
    @ManyToOne
    private Torneio torneio;
}

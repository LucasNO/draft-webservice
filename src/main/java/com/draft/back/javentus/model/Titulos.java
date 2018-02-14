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
import lombok.Data;

/**
 *
 * @author lucas
 */
@Data
@Entity
@Table(name = "tit_titulos")
public class Titulos implements Serializable {

    private static final long serialVersionUID = -4697202402529949200L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tit_id")
    private Integer id;
    
    @Column(name = "tit_primeiro")
    private Integer primeiro;
    
    @Column(name = "tit_segundo")
    private Integer segundo;
    
    @Column(name = "tit_terceiro")
    private Integer terceiro;
    
    @Column(name = "tit_quarto")
    private Integer quarto;
    
    @Column(name = "tit_quinto")
    private Integer quinto;
    
    @JoinColumn(name = "usr_id", referencedColumnName = "usr_id")
    @ManyToOne
    private Usuario usuario;
}

package com.draft.back.javentus.model;

import java.io.Serializable;
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
@Table(name = "tra_transferencia")
public class Transferencia implements Serializable {

    private static final long serialVersionUID = -5069657763103243151L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Time time1;
    
    @JoinColumn(name = "hic_time2", referencedColumnName = "tim_id")
    @ManyToOne
    private Time time2;
    
    @JoinColumn(name = "jog_jogador1", referencedColumnName = "jog_id")
    @ManyToOne
    private Jogador jogador1;
    
    @JoinColumn(name = "jog_jogador2", referencedColumnName = "jog_id")
    @ManyToOne
    private Jogador jogador2;
}

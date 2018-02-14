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
@Table(name = "hic_historico_confrontos")
public class HistoricoConfrontos implements Serializable {

    private static final long serialVersionUID = -721210409308182766L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hic_id")
    private Integer id;
    
    @Column(name = "hic_placar_time1")
    private Integer placarTime1;
    
    @Column(name = "hic_placar_time2")
    private Integer placarTime2;
    
    @JoinColumn(name = "hic_time1", referencedColumnName = "tim_id")
    @ManyToOne
    private Time time1;
    
    @JoinColumn(name = "hic_time2", referencedColumnName = "tim_id")
    @ManyToOne
    private Time time2;
    
    @JoinColumn(name = "tor_id", referencedColumnName = "tor_id")
    @ManyToOne
    private Torneio torneio;
}

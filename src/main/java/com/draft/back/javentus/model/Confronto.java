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
@Table(name = "con_confronto")
public class Confronto implements Serializable {

    private static final long serialVersionUID = 6702526916357618896L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "con_id")
    private Integer id;
    
    @Column(name = "con_placar1")
    private Integer placar1;
    
    @Column(name = "con_placar2")
    private Integer placar2;
    
    @JoinColumn(name = "fse_id", referencedColumnName = "fse_id")
    @ManyToOne
    private Fase fase;
    
    @JoinColumn(name = "tim_time1", referencedColumnName = "tim_id")
    @ManyToOne
    private Time time1;
    
    @JoinColumn(name = "tim_time2", referencedColumnName = "tim_id")
    @ManyToOne
    private Time time2;
    
}

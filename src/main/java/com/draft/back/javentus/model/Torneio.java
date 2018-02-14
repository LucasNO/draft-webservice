package com.draft.back.javentus.model;

import java.io.Serializable;
import javax.persistence.Basic;
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
@Table(name = "tor_torneio")
public class Torneio implements Serializable {

    private static final long serialVersionUID = 4415443622942430463L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tor_id")
    private Integer id;

//    @Basic(optional = false)
//    @NotNull
//    @Column(name = "tor_data")
//    @Temporal(TemporalType.DATE)
//    private Date data;

    @NotNull
    @Column(name = "tor_ativo")
    private Boolean ativo = Boolean.TRUE;

    @NotNull
    @Column(name = "tor_anoFifa")
    private int anoFifa;

    @ManyToOne
    @JoinColumn(name = "tto_id", referencedColumnName = "tto_id")
    private TipoTorneio tto;
}

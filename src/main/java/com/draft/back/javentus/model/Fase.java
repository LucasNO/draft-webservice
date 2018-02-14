package com.draft.back.javentus.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 *
 * @author lucas
 */
@Data
@Entity
@Table(name = "fse_fase")
public class Fase implements Serializable {

    private static final long serialVersionUID = -1463710929902130392L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fse_id")
    private Integer id;
    
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "fse_descricao")
    private String descricao;
    
//    @OneToMany(mappedBy = "fase")
//    private List<Confronto> confrontoList;
}

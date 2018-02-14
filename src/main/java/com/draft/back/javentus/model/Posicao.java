package com.draft.back.javentus.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 *
 * @author lucas
 */
@Data
@Entity
@Table(name = "pos_posicao")
public class Posicao implements Serializable {

    private static final long serialVersionUID = 4871331745146136864L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pos_id")
    private Integer id;

    @NotNull
    @Column(name = "pos_descricao")
    private String descricao;
}

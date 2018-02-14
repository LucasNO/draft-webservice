package com.draft.back.javentus.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 *
 * @author lucas
 */
@Data
@Entity
@Table(name = "usr_usuario")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 2174038559381173026L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usr_id")
    private Integer id;

    @NotNull
    @Column(name = "usr_nome")
    private String nome;

    @NotNull
    @Column(name = "usr_email")
    private String email;

    @NotNull
    @Column(name = "usr_senha")
    private String senha;

    @Column(name = "usr_ativo")
    private int ativo;

//    @OneToMany(mappedBy = "usr")
//    private List<Time> timeList;
}

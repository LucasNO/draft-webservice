package com.draft.back.javentus.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author lucas
 */
@Data
@Entity
@Table(name = "tim_time")
public class Time implements Serializable {

    private static final long serialVersionUID = -6972575022424001286L;

    @Id
    @Column(name = "tim_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn(name = "usr_id", referencedColumnName = "usr_id")
    @ManyToOne
    private Usuario usr;

    @Column(name = "tim_nome")
    private String nome;

    @OneToMany(mappedBy = "tim")
    private List<Jogador> jogadorList;
}

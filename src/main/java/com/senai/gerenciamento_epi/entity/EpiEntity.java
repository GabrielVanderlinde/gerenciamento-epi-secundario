package com.senai.gerenciamento_epi.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "epi")
@Data
@NoArgsConstructor
public class EpiEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEpi;

    @Column(nullable = false)
    private String nomeEpi;
    private String descricao;
    private Boolean statusEpi; // true=Disponivel

    @OneToMany(mappedBy = "epi")
    private List<EmprestimoEntity> emprestimos;

    public EpiEntity(String nome, String desc) {
        this.nomeEpi = nome;
        this.descricao = desc;
        this.statusEpi = true;
    }
}
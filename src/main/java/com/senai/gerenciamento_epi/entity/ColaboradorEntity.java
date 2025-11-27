package com.senai.gerenciamento_epi.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "colaborador")
@Data
@NoArgsConstructor
public class ColaboradorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idColaborador;

    @Column(nullable = false)
    private String nomeColaborador;

    private String setorColaborador;

    private String statusColaborador; // 1=Ativo, 0=Inativo

    @OneToMany(mappedBy = "colaborador")
    private List<EmprestimoEntity> emprestimos;

    public ColaboradorEntity(String nome, String setor) {
        this.nomeColaborador = nome;
        this.setorColaborador = setor;
        this.statusColaborador = "1";
    }
}
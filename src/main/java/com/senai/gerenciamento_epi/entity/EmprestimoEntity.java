package com.senai.gerenciamento_epi.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "emprestimo")
@Data
@NoArgsConstructor
public class EmprestimoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEmprestimo;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;
    private String status;

    @ManyToOne
    @JoinColumn(name = "id_colaborador")
    private ColaboradorEntity colaborador;

    @ManyToOne
    @JoinColumn(name = "id_epi")
    private EpiEntity epi;

    public EmprestimoEntity(ColaboradorEntity c, EpiEntity e) {
        this.colaborador = c;
        this.epi = e;
        this.dataEmprestimo = LocalDate.now();
        this.status = "ATIVO";
    }
}
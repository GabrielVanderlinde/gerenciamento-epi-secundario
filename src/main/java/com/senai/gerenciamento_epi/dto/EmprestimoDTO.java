package com.senai.gerenciamento_epi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class EmprestimoDTO {
    private Integer idEmprestimo;
    private Integer idColaborador;
    private String nomeColaborador;
    private Integer idEpi;
    private String nomeEpi;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;
    private String status;

    @Override
    public String toString() {
        return String.format("\n| EMPRESTIMO #%-3d | %s -> %s | STATUS: %s |",
                idEmprestimo, nomeColaborador, nomeEpi, status);
    }
}
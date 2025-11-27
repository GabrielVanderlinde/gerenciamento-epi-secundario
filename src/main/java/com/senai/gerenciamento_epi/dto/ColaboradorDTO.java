package com.senai.gerenciamento_epi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ColaboradorDTO {
    private Integer idColaborador;
    private String nomeColaborador;
    private String setorColaborador;
    private String statusColaborador;

    public ColaboradorDTO(String nome, String setor, String status) {
        this.nomeColaborador = nome;
        this.setorColaborador = setor;
        this.statusColaborador = status;
    }

    @Override
    public String toString() {
        String st = "1".equals(statusColaborador) ? "ATIVO" : "INATIVO";
        return String.format("\n| ID: %-3d | NOME: %-15s | SETOR: %-10s | STATUS: %s |",
                idColaborador, nomeColaborador, setorColaborador, st);
    }
}
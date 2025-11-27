package com.senai.gerenciamento_epi.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EpiDTO {
    private Integer idEpi;
    private String nomeEpi;
    private String descricao;
    private Boolean statusEpi;

    public EpiDTO(String nome, String desc, Boolean status) {
        this.nomeEpi = nome;
        this.descricao = desc;
        this.statusEpi = status;
    }

    @Override
    public String toString() {
        String st = Boolean.TRUE.equals(statusEpi) ? "DISPONIVEL" : "INDISPONIVEL";
        return String.format("\n| ID: %-3d | EPI: %-16s | STATUS: %s |", idEpi, nomeEpi, st);
    }
}
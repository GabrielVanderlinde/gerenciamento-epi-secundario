package com.senai.gerenciamento_epi.dto;

public class ColaboradorDTO {
    private Integer idColaborador;
    private String nomeColaborador;
    private String setorColaborador;
    private String statusColaborador;

    public ColaboradorDTO() {}
    public ColaboradorDTO(Integer id, String nome, String setor, String status) {
        this.idColaborador = id; this.nomeColaborador = nome; this.setorColaborador = setor; this.statusColaborador = status;
    }
    public ColaboradorDTO(String nome, String setor, String status) {
        this.nomeColaborador = nome; this.setorColaborador = setor; this.statusColaborador = status;
    }

    public Integer getIdColaborador() { return idColaborador; }
    public void setIdColaborador(Integer idColaborador) { this.idColaborador = idColaborador; }
    public String getNomeColaborador() { return nomeColaborador; }
    public void setNomeColaborador(String nomeColaborador) { this.nomeColaborador = nomeColaborador; }
    public String getSetorColaborador() { return setorColaborador; }
    public void setSetorColaborador(String setorColaborador) { this.setorColaborador = setorColaborador; }
    public String getStatusColaborador() { return statusColaborador; }
    public void setStatusColaborador(String statusColaborador) { this.statusColaborador = statusColaborador; }

    @Override
    public String toString() {
        return String.format("\n| ID: %-3d | NOME: %-15s | SETOR: %-10s | STATUS: %s |", idColaborador, nomeColaborador, setorColaborador, "1".equals(statusColaborador) ? "ATIVO" : "INATIVO");
    }
}
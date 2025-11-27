package com.senai.gerenciamento_epi.dto;

public class EpiDTO {
    private Integer idEpi;
    private String nomeEpi;
    private String descricao;
    private Boolean statusEpi;

    public EpiDTO() {}
    public EpiDTO(Integer id, String nome, String desc, Boolean status) {
        this.idEpi = id; this.nomeEpi = nome; this.descricao = desc; this.statusEpi = status;
    }
    public EpiDTO(String nome, String desc, Boolean status) {
        this.nomeEpi = nome; this.descricao = desc; this.statusEpi = status;
    }

    public Integer getIdEpi() { return idEpi; }
    public void setIdEpi(Integer idEpi) { this.idEpi = idEpi; }
    public String getNomeEpi() { return nomeEpi; }
    public void setNomeEpi(String nomeEpi) { this.nomeEpi = nomeEpi; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public Boolean getStatusEpi() { return statusEpi; }
    public void setStatusEpi(Boolean statusEpi) { this.statusEpi = statusEpi; }

    @Override
    public String toString() {
        return String.format("\n| ID: %-3d | EPI: %-16s | STATUS: %s |", idEpi, nomeEpi, Boolean.TRUE.equals(statusEpi) ? "DISPONIVEL" : "INDISPONIVEL");
    }
}
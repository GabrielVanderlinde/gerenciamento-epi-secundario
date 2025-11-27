package com.senai.gerenciamento_epi.entity;
import jakarta.persistence.*;
import java.util.List;

@Entity @Table(name = "epi")
public class EpiEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEpi;
    @Column(nullable = false) private String nomeEpi;
    private String descricao;
    private Boolean statusEpi;
    @OneToMany(mappedBy = "epi") private List<EmprestimoEntity> emprestimos;

    public EpiEntity() {}
    public EpiEntity(String nome, String desc) { this.nomeEpi = nome; this.descricao = desc; this.statusEpi = true; }

    public Integer getIdEpi() { return idEpi; }
    public void setIdEpi(Integer id) { this.idEpi = id; }
    public String getNomeEpi() { return nomeEpi; }
    public void setNomeEpi(String nome) { this.nomeEpi = nome; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String desc) { this.descricao = desc; }
    public Boolean getStatusEpi() { return statusEpi; }
    public void setStatusEpi(Boolean status) { this.statusEpi = status; }
}
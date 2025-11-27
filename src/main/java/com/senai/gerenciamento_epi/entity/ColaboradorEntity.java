package com.senai.gerenciamento_epi.entity;
import jakarta.persistence.*;
import java.util.List;

@Entity @Table(name = "colaborador")
public class ColaboradorEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idColaborador;
    @Column(nullable = false) private String nomeColaborador;
    private String setorColaborador;
    private String statusColaborador;
    @OneToMany(mappedBy = "colaborador") private List<EmprestimoEntity> emprestimos;

    public ColaboradorEntity() {}
    public ColaboradorEntity(String nome, String setor) { this.nomeColaborador = nome; this.setorColaborador = setor; this.statusColaborador = "1"; }

    public Integer getIdColaborador() { return idColaborador; }
    public void setIdColaborador(Integer id) { this.idColaborador = id; }
    public String getNomeColaborador() { return nomeColaborador; }
    public void setNomeColaborador(String nome) { this.nomeColaborador = nome; }
    public String getSetorColaborador() { return setorColaborador; }
    public void setSetorColaborador(String setor) { this.setorColaborador = setor; }
    public String getStatusColaborador() { return statusColaborador; }
    public void setStatusColaborador(String status) { this.statusColaborador = status; }
}
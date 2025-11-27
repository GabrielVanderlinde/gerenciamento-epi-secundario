package com.senai.gerenciamento_epi.entity;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity @Table(name = "emprestimo")
public class EmprestimoEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEmprestimo;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;
    private String status;
    @ManyToOne @JoinColumn(name = "id_colaborador") private ColaboradorEntity colaborador;
    @ManyToOne @JoinColumn(name = "id_epi") private EpiEntity epi;

    public EmprestimoEntity() {}
    public EmprestimoEntity(ColaboradorEntity c, EpiEntity e) {
        this.colaborador = c; this.epi = e; this.dataEmprestimo = LocalDate.now(); this.status = "ATIVO";
    }

    public Integer getIdEmprestimo() { return idEmprestimo; }
    public void setIdEmprestimo(Integer id) { this.idEmprestimo = id; }
    public LocalDate getDataEmprestimo() { return dataEmprestimo; }
    public void setDataEmprestimo(LocalDate data) { this.dataEmprestimo = data; }
    public LocalDate getDataDevolucao() { return dataDevolucao; }
    public void setDataDevolucao(LocalDate data) { this.dataDevolucao = data; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public ColaboradorEntity getColaborador() { return colaborador; }
    public void setColaborador(ColaboradorEntity c) { this.colaborador = c; }
    public EpiEntity getEpi() { return epi; }
    public void setEpi(EpiEntity e) { this.epi = e; }
}
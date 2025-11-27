package com.senai.gerenciamento_epi.dto;
import java.time.LocalDate;

public class EmprestimoDTO {
    private Integer idEmprestimo;
    private Integer idColaborador;
    private String nomeColaborador;
    private Integer idEpi;
    private String nomeEpi;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;
    private String status;

    public EmprestimoDTO() {}

    public Integer getIdEmprestimo() { return idEmprestimo; }
    public void setIdEmprestimo(Integer id) { this.idEmprestimo = id; }
    public Integer getIdColaborador() { return idColaborador; }
    public void setIdColaborador(Integer id) { this.idColaborador = id; }
    public String getNomeColaborador() { return nomeColaborador; }
    public void setNomeColaborador(String nome) { this.nomeColaborador = nome; }
    public Integer getIdEpi() { return idEpi; }
    public void setIdEpi(Integer id) { this.idEpi = id; }
    public String getNomeEpi() { return nomeEpi; }
    public void setNomeEpi(String nome) { this.nomeEpi = nome; }
    public LocalDate getDataEmprestimo() { return dataEmprestimo; }
    public void setDataEmprestimo(LocalDate data) { this.dataEmprestimo = data; }
    public LocalDate getDataDevolucao() { return dataDevolucao; }
    public void setDataDevolucao(LocalDate data) { this.dataDevolucao = data; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return String.format("\n| EMPRESTIMO #%-3d | %s -> %s | STATUS: %s |", idEmprestimo, nomeColaborador, nomeEpi, status);
    }
}
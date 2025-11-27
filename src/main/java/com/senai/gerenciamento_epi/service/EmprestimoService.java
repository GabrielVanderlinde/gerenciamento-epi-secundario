package com.senai.gerenciamento_epi.service;

import com.senai.gerenciamento_epi.dto.EmprestimoDTO;
import com.senai.gerenciamento_epi.entity.*;
import com.senai.gerenciamento_epi.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmprestimoService {
    private final EmprestimoRepository empRepo;
    private final ColaboradorRepository colabRepo;
    private final EpiRepository epiRepo;

    /**
     * Registra saída de equipamento.
     * Valida se o colaborador está ativo e se o EPI está disponível.
     */
    @Transactional
    public EmprestimoDTO criar(EmprestimoDTO dto) {
        ColaboradorEntity c = colabRepo.findById(dto.getIdColaborador()).orElseThrow();
        EpiEntity e = epiRepo.findById(dto.getIdEpi()).orElseThrow();

        if (!e.getStatusEpi()) throw new RuntimeException("EPI Indisponivel!");

        EmprestimoEntity emp = new EmprestimoEntity(c, e);
        if (dto.getDataDevolucao() != null) emp.setDataDevolucao(dto.getDataDevolucao());

        e.setStatusEpi(false);
        epiRepo.save(e);

        return toDTO(empRepo.save(emp));
    }

    @Transactional
    public EmprestimoDTO devolver(Integer id) {
        EmprestimoEntity emp = empRepo.findById(id).orElseThrow();
        if ("DEVOLVIDO".equals(emp.getStatus())) throw new RuntimeException("Ja devolvido!");

        emp.setStatus("DEVOLVIDO");
        emp.getEpi().setStatusEpi(true);
        epiRepo.save(emp.getEpi());

        return toDTO(empRepo.save(emp));
    }

    public List<EmprestimoDTO> listarAtivos() {
        return empRepo.findAtivos().stream().map(this::toDTO).collect(Collectors.toList());
    }

    private EmprestimoDTO toDTO(EmprestimoEntity e) {
        EmprestimoDTO dto = new EmprestimoDTO();
        dto.setIdEmprestimo(e.getIdEmprestimo());
        dto.setNomeColaborador(e.getColaborador().getNomeColaborador());
        dto.setNomeEpi(e.getEpi().getNomeEpi());
        dto.setDataEmprestimo(e.getDataEmprestimo());
        dto.setDataDevolucao(e.getDataDevolucao());
        dto.setStatus(e.getStatus());
        return dto;
    }
}
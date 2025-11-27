package com.senai.gerenciamento_epi.service;

import com.senai.gerenciamento_epi.dto.EpiDTO;
import com.senai.gerenciamento_epi.entity.EpiEntity;
import com.senai.gerenciamento_epi.repository.EpiRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EpiService {
    private final EpiRepository repo;

    public List<EpiDTO> listar() {
        return repo.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public EpiDTO criar(EpiDTO dto) {
        return toDTO(repo.save(new EpiEntity(dto.getNomeEpi(), dto.getDescricao())));
    }

    public EpiDTO atualizar(Integer id, EpiDTO dto) {
        EpiEntity e = repo.findById(id).orElseThrow();
        if (dto.getNomeEpi() != null) e.setNomeEpi(dto.getNomeEpi());
        if (dto.getDescricao() != null) e.setDescricao(dto.getDescricao());
        return toDTO(repo.save(e));
    }

    public void indisponibilizar(Integer id) {
        EpiEntity e = repo.findById(id).orElseThrow();
        e.setStatusEpi(false);
        repo.save(e);
    }

    private EpiDTO toDTO(EpiEntity e) {
        return new EpiDTO(e.getIdEpi(), e.getNomeEpi(), e.getDescricao(), e.getStatusEpi());
    }
}
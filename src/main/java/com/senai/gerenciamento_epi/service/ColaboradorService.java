package com.senai.gerenciamento_epi.service;

import com.senai.gerenciamento_epi.dto.ColaboradorDTO;
import com.senai.gerenciamento_epi.entity.ColaboradorEntity;
import com.senai.gerenciamento_epi.repository.ColaboradorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ColaboradorService {
    private final ColaboradorRepository repo;

    public List<ColaboradorDTO> listar() {
        return repo.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    /**
     * Cadastra um novo colaborador no banco de dados.
     * Verifica duplicidade de nome antes de salvar.
     */
    public ColaboradorDTO criar(ColaboradorDTO dto) {
        if (repo.existsByNomeColaborador(dto.getNomeColaborador()))
            throw new RuntimeException("Nome duplicado.");

        ColaboradorEntity entity = new ColaboradorEntity(dto.getNomeColaborador(), dto.getSetorColaborador());
        return toDTO(repo.save(entity));
    }

    public ColaboradorDTO atualizar(Integer id, ColaboradorDTO dto) {
        ColaboradorEntity e = repo.findById(id).orElseThrow();
        if (dto.getNomeColaborador() != null) e.setNomeColaborador(dto.getNomeColaborador());
        if (dto.getSetorColaborador() != null) e.setSetorColaborador(dto.getSetorColaborador());
        if (dto.getStatusColaborador() != null) e.setStatusColaborador(dto.getStatusColaborador());
        return toDTO(repo.save(e));
    }

    public void inativar(Integer id) {
        ColaboradorEntity e = repo.findById(id).orElseThrow();
        e.setStatusColaborador("0");
        repo.save(e);
    }

    private ColaboradorDTO toDTO(ColaboradorEntity e) {
        return new ColaboradorDTO(e.getIdColaborador(), e.getNomeColaborador(), e.getSetorColaborador(), e.getStatusColaborador());
    }
}
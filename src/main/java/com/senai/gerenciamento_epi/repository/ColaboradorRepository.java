package com.senai.gerenciamento_epi.repository;

import com.senai.gerenciamento_epi.entity.ColaboradorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColaboradorRepository extends JpaRepository<ColaboradorEntity, Integer> {
    boolean existsByNomeColaborador(String nome);
}
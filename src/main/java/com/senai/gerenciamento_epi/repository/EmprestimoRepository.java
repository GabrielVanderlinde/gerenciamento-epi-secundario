package com.senai.gerenciamento_epi.repository;

import com.senai.gerenciamento_epi.entity.EmprestimoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmprestimoRepository extends JpaRepository<EmprestimoEntity, Integer> {
    @Query("SELECT e FROM EmprestimoEntity e WHERE e.status = 'ATIVO'")
    List<EmprestimoEntity> findAtivos();
}
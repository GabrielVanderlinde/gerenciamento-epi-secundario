package com.senai.gerenciamento_epi.repository;
import com.senai.gerenciamento_epi.entity.EpiEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EpiRepository extends JpaRepository<EpiEntity, Integer> {
    boolean existsByNomeEpi(String nome);
}
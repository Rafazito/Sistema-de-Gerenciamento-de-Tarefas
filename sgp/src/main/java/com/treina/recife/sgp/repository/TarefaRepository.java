package com.treina.recife.sgp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.treina.recife.sgp.model.Projeto;
import com.treina.recife.sgp.model.Tarefa;
import com.treina.recife.sgp.model.Usuario;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
     /*List<Tarefa> findByResponsavelUserId(Long usuarioId);*/

    List<Tarefa>findByUsuario(Usuario usuario);

    List<Tarefa>findByUsuarioUserId(Long usuarioId);

    boolean existsByTitulo(String title);

    boolean existsByUsuarioUserId(Long usuarioId);

    /*Optional<Usuario> findByResponsavelUserId(Long usuarioId);*/

    List<Tarefa> findByProjetoProjectId(long projectId);
}

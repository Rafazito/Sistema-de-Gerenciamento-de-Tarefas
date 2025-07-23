package com.treina.recife.sgp.controller;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.treina.recife.sgp.model.Tarefa;
import com.treina.recife.sgp.service.ProjetoService;
import com.treina.recife.sgp.service.TarefaService;
import com.treina.recife.sgp.service.UsuarioService;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    @Autowired
    TarefaService tarefaService;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    ProjetoService projetoService;

    Logger logger = LogManager.getLogger(TarefaController.class);
    
    public TarefaController(TarefaService tarefaService, UsuarioService usuarioService, ProjetoService projetoService) {

        this.tarefaService = tarefaService;
        this.usuarioService = usuarioService;
        this.projetoService = projetoService;

    }

    @GetMapping
    public ResponseEntity<Page<Tarefa>> getTarefas(@PageableDefault(sort = "tarefaId", direction = Sort.Direction.ASC)Pageable pageable){

        Page<Tarefa> tarefas = tarefaService.getTarefas(pageable);

        if (tarefas.isEmpty()) {
            logger.info("Ainda não há tarefa cadastrada.");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(Page.empty());
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(tarefas);
        }

    }

    @GetMapping("/{tarefaId}")
    public ResponseEntity<Object> getTarefaById(@PathVariable(value = "tarefaId")long tarefaId) {

        Optional<Tarefa> tarefa = tarefaService.getTarefaById(tarefaId);
        
        if (tarefa.isEmpty()) {
            logger.error("Tarefa não encontrada");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa não encontrada");
        } else {
            logger.info(tarefa.get().toString());
            return ResponseEntity.status(HttpStatus.OK).body(tarefa.get());
        }
    
    }

}

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

import com.treina.recife.sgp.model.Projeto;
import com.treina.recife.sgp.service.ProjetoService;
import com.treina.recife.sgp.service.UsuarioService;

@RestController
@RequestMapping("/projetos")
public class ProjetoController {

   @Autowired
   ProjetoService projetoService;
    
    @Autowired
    UsuarioService usuarioService;

    Logger logger = LogManager.getLogger(ProjetoController.class);

    public ProjetoController(ProjetoService projetoService, UsuarioService usuarioService) {

        this.projetoService = projetoService;
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<Page<Projeto>> getProjetos(@PageableDefault(sort = "projectId", direction = Sort.Direction.ASC) Pageable pageable) {
        
        Page<Projeto> projetos = projetoService.getProjetos (pageable);

        if (projetos.isEmpty()) {
            logger.info("Ainda não há projeto cadastrado.");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(Page.empty());
        
        } else{
            return ResponseEntity.status(HttpStatus.OK).body(projetos);
        
        }

    }

    @GetMapping("/{projectId}")
    public ResponseEntity<Object> getProjetoById(@PathVariable(value = "projectId") long projectId) {

        Optional<Projeto> projeto = projetoService.getProjectById(projectId);

        if (projeto.isEmpty()) {
            logger.error("Projeto não encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Projeto não encontrado");       
        } else {
            logger.info(projeto.get().toString());
            return ResponseEntity.status(HttpStatus.OK).body(projeto.get());
        }
        
    }







}

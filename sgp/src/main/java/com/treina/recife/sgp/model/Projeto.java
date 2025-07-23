package com.treina.recife.sgp.model;

import java.io.Serializable;
import java.security.PrivateKey;
import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.Collate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.treina.recife.sgp.constants.StatusProjeto;
import com.treina.recife.sgp.constants.StatusUsuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Projeto")
public class Projeto implements Serializable {
private static final long serialVersionUID = 1L;
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "projectId")
private long projectId;

@Column(name = "NAME" , nullable = false)
private String nome;

@Column (name = "DESCRICAO" , nullable = false)
private String descricao;

@Column (name = "dataInicio" , nullable = false)
@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
private LocalDate dataInicio;

@Column (name = "dataConclusao" , nullable = false)
@JsonFormat (shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
private LocalDate dataConclusao;

@ManyToOne
@JoinColumn(name = "userId" , referencedColumnName = "userId")
private Usuario responsavel;

@Column(name = "status" , nullable = false)
@Enumerated(EnumType.STRING)
private StatusProjeto status;

}

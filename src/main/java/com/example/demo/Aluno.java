package com.example.demo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "ALUNO")
public class Aluno {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID",nullable = true)
	public Long ID;
	
	@Column(name = "NOME", nullable = true, columnDefinition = "varchar(40)")
	public String nome;
	
	@Column(name = "CPF", nullable = true, columnDefinition = "varchar(11)", unique = true)
	public String cpf;
	

	@Column(name = "nota1", nullable = true, columnDefinition = "numeric(4,2)")
	public float nota1;
	
	@Column(name = "nota2", nullable = true, columnDefinition = "numeric(4,2)")
	public float nota2;

}



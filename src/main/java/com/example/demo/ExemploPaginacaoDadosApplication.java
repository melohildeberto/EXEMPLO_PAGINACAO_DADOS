package com.example.demo;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@SpringBootApplication
public class ExemploPaginacaoDadosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExemploPaginacaoDadosApplication.class, args);
	}

//	@Primary
	@Bean
	public CommandLineRunner inserindoAlunos(RepositorioAluno repository) {
		return (args) -> {
			
			float mediaGeral = repository.findMediaGeral();
//			System.out.println("Média geral:" + mediaGeral);
			if (repository.findAll().size() == 0) {
				for (int i = 1; i < 101; i++) {
					Aluno a = new Aluno();
					a.nome = "Aluno " + i;
					a.cpf = "" + i;
					if(i % 2 == 0) {
						a.nota1 = 10;
						a.nota2 = 8;
					}else {
						a.nota1 = 6;
						a.nota2 = 4;
					}
					a = repository.save(a);
				}
				System.out.println("Cadastro Finalizado");
			}
		};
	}

//	@Bean
	public CommandLineRunner listandoAlunos(RepositorioAluno repository) {
		return (args) -> {
			for (Aluno aluno : repository.findAll()) {
				System.out.println(aluno.nome);
			}
		};
	}
	
//	@Bean
	public CommandLineRunner listandoAlunosPaginacao(RepositorioAluno repository) {
		return (args) -> {
			Pageable firstPageWithTwoElements = PageRequest.of(0, 2);
			Page<Aluno> listaAlunos = repository.findAll(firstPageWithTwoElements);
			
			for (Aluno aluno : listaAlunos) {
				System.out.println(aluno.nome);
			}
			
		};
	}
}

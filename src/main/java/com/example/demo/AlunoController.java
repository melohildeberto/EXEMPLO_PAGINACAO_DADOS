package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("aluno")
public class AlunoController {
	@Autowired
	RepositorioAluno repositorio;

	@GetMapping("/listall")
	public List<Aluno> listAll() {
		return repositorio.findAll();
	}

	@PostMapping("/listpaginacao")
	public ResponseEntity<AlunoResponsePaginacao> listPaginacao(@RequestBody AlunoRequestPaginacao paginacao) {
		AlunoResponsePaginacao resposta = new AlunoResponsePaginacao();
		if(paginacao.paginar) {
			Pageable pagina = PageRequest.of(paginacao.pagina, paginacao.qtdporpagina);
			Page<Aluno> alunos = repositorio.findAll(pagina);
			
			resposta.alunos = alunos.getContent();
			resposta.totalRegistros = repositorio.count();
			resposta.qtdAlunosPagina = resposta.alunos.size();
		}else {
			resposta.alunos = repositorio.findAll();
			resposta.totalRegistros =resposta.alunos.size();
			resposta.qtdAlunosPagina = resposta.totalRegistros;
		}
		 
		return new ResponseEntity<>(resposta, HttpStatus.OK);
	}
}

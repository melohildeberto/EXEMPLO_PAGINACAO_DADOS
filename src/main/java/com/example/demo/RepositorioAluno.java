package com.example.demo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RepositorioAluno extends JpaRepository<Aluno, Long>{
	
	@Query(value = "SELECT * FROM ALUNO ",
		    countQuery = "SELECT count(nota1) FROM ALUNO",
		    nativeQuery = true)
	Page<Aluno> findAll(Pageable pageable);
	@Query("select avg(nota1 + nota2) from ALUNO")
	float findMediaGeral();
	
}

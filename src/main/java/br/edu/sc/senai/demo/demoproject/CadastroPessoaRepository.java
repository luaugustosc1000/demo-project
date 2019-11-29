package br.edu.sc.senai.demo.demoproject;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CadastroPessoaRepository extends JpaRepository<CadastroPessoaEntity, Long>{

	CadastroPessoaEntity findById(long id);	
}
package br.edu.sc.senai.demo.demoproject;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PorcaoRepository extends JpaRepository<PorcaoEntity, Long>{

	PorcaoEntity findById(long id);
	
}
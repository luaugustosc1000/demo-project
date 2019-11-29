package br.edu.sc.senai.demo.demoproject;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HamburguerRepository extends JpaRepository<HamburguerEntity, Long>{

	HamburguerEntity findById(long id);
	
}

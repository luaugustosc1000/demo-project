package br.edu.sc.senai.demo.demoproject;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BuffetRepository extends JpaRepository<BuffetEntity, Long>{

	BuffetEntity findById(long id);
	
}

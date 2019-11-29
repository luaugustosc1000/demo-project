package br.edu.sc.senai.demo.demoproject;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<EnderecoEntity, Long> {

	EnderecoEntity findById(long id);

}

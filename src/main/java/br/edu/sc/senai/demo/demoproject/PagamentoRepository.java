package br.edu.sc.senai.demo.demoproject;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<PagamentoEntity, Long> {

	PagamentoEntity findById(long id);
}

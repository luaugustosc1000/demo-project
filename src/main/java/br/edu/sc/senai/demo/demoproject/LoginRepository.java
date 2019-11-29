package br.edu.sc.senai.demo.demoproject;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<LoginEntity, Long>{

	LoginEntity findById(long id);
}

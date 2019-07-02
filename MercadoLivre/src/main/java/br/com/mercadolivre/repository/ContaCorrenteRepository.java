package br.com.mercadolivre.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.mercadolivre.model.Conta;

@Repository
public interface ContaCorrenteRepository extends CrudRepository<Conta, Long>, JpaRepository<Conta, Long>{

	Optional<Conta> findByCpfIgnoreCaseContaining(String cpf);

}

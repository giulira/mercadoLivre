package br.com.mercadolivre.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.mercadolivre.model.TransacaoBancaria;

@Repository
public interface TransacaoBancariaRepository  extends CrudRepository<TransacaoBancaria, Long>, JpaRepository<TransacaoBancaria, Long>{
}

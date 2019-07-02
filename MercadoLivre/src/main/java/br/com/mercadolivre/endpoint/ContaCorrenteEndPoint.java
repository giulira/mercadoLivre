package br.com.mercadolivre.endpoint;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.mercadolivre.model.Conta;
import br.com.mercadolivre.model.TransacaoBancaria;
import br.com.mercadolivre.repository.ContaCorrenteRepository;
import br.com.mercadolivre.repository.TransacaoBancariaRepository;

@RestController
@RequestMapping("transacao")
public class ContaCorrenteEndPoint {
	@Autowired
	private ContaCorrenteRepository contaRepository;
	
	@Autowired
	private TransacaoBancariaRepository transacaoRepository;

	@RequestMapping(method=RequestMethod.POST, path= "/creditarCC")
	public ResponseEntity<?> creditarCC(@RequestBody TransacaoBancaria transacao){
		 creditarValorConta(transacao); 
		 debitarValorConta(transacao); 
		 return new ResponseEntity<>(transacaoRepository.save(transacao),HttpStatus.OK);
	}
	
	public ResponseEntity<?> creditarValorConta(TransacaoBancaria transacao) {
		Optional<Conta> cDestino = findConta(transacao.getCpfDestino());
		Conta conta = new Conta(); 
		if(cDestino.isPresent()) {
			conta = cDestino.get();
			conta.setSaldo(conta.getSaldo().add(transacao.getValor()));
			return new ResponseEntity(contaRepository.save(conta), HttpStatus.OK);
		}else {
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<?> debitarValorConta(TransacaoBancaria transacao) {
		Optional<Conta> cOrigem = findConta(transacao.getCpfOrigem());
		Conta conta = new Conta(); 
		if(cOrigem.isPresent()) {
			conta = cOrigem.get();
			conta.setSaldo(conta.getSaldo().subtract(transacao.getValor()));
			return new ResponseEntity(contaRepository.save(conta), HttpStatus.OK);
		}else {
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	private Optional<Conta> findConta(String cpf) {
		return contaRepository.findByCpfIgnoreCaseContaining(cpf);
	}
	

}

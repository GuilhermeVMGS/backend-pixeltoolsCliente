package br.unibh.sdm.backend_usuario.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import br.unibh.sdm.backend_usuario.entidades.Cliente;
import br.unibh.sdm.backend_usuario.persistencia.ClienteRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ClienteTests {
    
    private static Logger LOGGER = LoggerFactory.getLogger(ClienteTests.class);
    private SimpleDateFormat df = new SimpleDateFormat("dd/mm/yyyy");
	    
    
	@Autowired
	private ClienteRepository repository;

	@Test
	public void teste1Criacao() throws ParseException {
		LOGGER.info("Criando objetos...");
		Cliente c1 = new Cliente("Guilherme", "guilhermevmgs@gmail.com", "31992694183", "12607136601");
		repository.save(c1);

		LOGGER.info("Pesquisado todos");
		Iterable<Cliente> lista = repository.findAll();
		assertNotNull(lista.iterator());
		for (Cliente cliente : lista) {
			LOGGER.info(cliente.toString());
		}
		LOGGER.info("Pesquisado um objeto");
		List<Cliente> result = repository.findByNome("Guilherme");
		assertEquals(result.size(), 1);
		assertEquals(result.get(0).getCpf(), "12607136601");
		LOGGER.info("Encontrado: {}", result.get(0));
	}


	@Test
	public void teste2Exclusao() throws ParseException {
		Iterable<Cliente> lista = repository.findAll();
		for (Cliente cliente : lista) {
			LOGGER.info("Excluindo cliente cujo CPF e: "+cliente.getCpf());
			repository.delete(cliente);
		}
		lista = repository.findAll();
		assertEquals(lista.iterator().hasNext(), false);
		LOGGER.info("Exclusão feita com sucesso");
	}
}

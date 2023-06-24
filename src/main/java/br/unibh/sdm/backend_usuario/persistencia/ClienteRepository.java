package br.unibh.sdm.backend_usuario.persistencia;

import org.springframework.data.repository.CrudRepository;

import br.unibh.sdm.backend_usuario.entidades.Cliente;


import java.util.List;


public interface ClienteRepository extends CrudRepository<Cliente, Long>{
    
    List<Cliente> findByNome (String nome);
}
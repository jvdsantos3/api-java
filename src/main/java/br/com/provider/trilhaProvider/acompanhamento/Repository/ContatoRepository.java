package br.com.provider.trilhaProvider.acompanhamento.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;

import br.com.provider.trilhaProvider.acompanhamento.Models.Contato;

public interface ContatoRepository extends JpaRepository<Contato, Long> {

    List<Contato> findAllByNomeContains(String nome);

}

package br.com.provider.trilhaProvider.acompanhamento.DTO;

import java.sql.Date;

import br.com.provider.trilhaProvider.acompanhamento.enums.ContatoGenero;
import lombok.Data;

@Data
public class ContatoDto {

  private String nome;

  private String cpf;

  private String telefone;

  private Date data_nascimento;

  private String cep;

  private String cidade;

  private String uf;

  private String numero;

  private String endereco;

  private String bairro;

  private String complemento;

  private ContatoGenero genero;

  private String criadorUid;

  private String criadorNome;

}

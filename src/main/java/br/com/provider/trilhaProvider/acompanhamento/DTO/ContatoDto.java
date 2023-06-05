package br.com.provider.trilhaProvider.acompanhamento.DTO;

import java.sql.Date;

import javax.validation.constraints.NotEmpty;

import br.com.provider.trilhaProvider.acompanhamento.enums.ContatoGenero;
import lombok.Data;

@Data
public class ContatoDto {

  @NotEmpty
  private String nome;

  @NotEmpty
  private String cpf;

  @NotEmpty
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

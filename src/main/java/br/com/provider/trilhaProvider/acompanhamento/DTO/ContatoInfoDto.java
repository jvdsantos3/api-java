package br.com.provider.trilhaProvider.acompanhamento.DTO;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ContatoInfoDto {

  private long id;

  private String nome;

  private String cpf;

  private String telefone;

  private LocalDate data_nascimento;

  private String cep;

  private String cidade;

  private String uf;

  private String numero;

  private String endereco;

  private String bairro;

  private String complemento;

  private String genero;

  private String criadorUid;

  private String criadorNome;

}

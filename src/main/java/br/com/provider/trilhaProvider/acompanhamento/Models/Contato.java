/*

Modelagem			Banco de dados			JAVA				DTO
contato				tb_Contato				contato				contato
 

*/

package br.com.provider.trilhaProvider.acompanhamento.Models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.com.provider.trilhaProvider.acompanhamento.enums.ContatoGenero;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_contato")
public class Contato implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @NotNull
    @Column(name = "nome")
    private String nome;

    @NotNull
    @Column(name = "cpf")
    private String cpf;

    @NotNull
    @Column(name = "telefone")
    private String telefone;

    @NotNull
    @Column(name = "data_nascimento")
    private Date dataNascimento;

    @NotNull
    @Column(name = "cep")
    private String cep;

    @NotNull
    @Column(name = "cidade")
    private String cidade;

    @NotNull
    @Column(name = "uf")
    private String uf;

    @NotNull
    @Column(name = "numero")
    private String numero;

    @NotNull
    @Column(name = "endereco")
    private String endereco;

    @NotNull
    @Column(name = "bairro")
    private String bairro;

    @Column(name = "complemento")
    private String complemento;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "genero")
    private ContatoGenero genero;

    @NotNull
    @Column(name = "criadorUid")
    private String criadorUid;

    @NotNull
    @Column(name = "criadorNome")
    private String criadorNome;

}
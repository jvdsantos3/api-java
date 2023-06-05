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
import javax.validation.constraints.NotEmpty;
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

    @NotEmpty
    @Column(name = "nome")
    private String nome;

    @NotEmpty
    @Column(name = "cpf")
    private String cpf;

    @NotEmpty
    @Column(name = "telefone")
    private String telefone;

    @NotNull
    @Column(name = "data_nascimento")
    private Date dataNascimento;

    @NotEmpty
    @Column(name = "cep")
    private String cep;

    @NotEmpty
    @Column(name = "cidade")
    private String cidade;

    @NotEmpty
    @Column(name = "uf")
    private String uf;

    @NotEmpty
    @Column(name = "numero")
    private String numero;

    @NotEmpty
    @Column(name = "endereco")
    private String endereco;

    @NotEmpty
    @Column(name = "bairro")
    private String bairro;

    @Column(name = "complemento")
    private String complemento;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "genero")
    private ContatoGenero genero;

    @NotEmpty
    @Column(name = "criadorUid")
    private String criadorUid;

    @NotEmpty
    @Column(name = "criadorNome")
    private String criadorNome;

}
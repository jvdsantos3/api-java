
package br.com.provider.trilhaProvider.acompanhamento.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.provider.trilhaProvider.acompanhamento.DTO.ContatoDto;
import br.com.provider.trilhaProvider.acompanhamento.DTO.ContatoInfoDto;
import br.com.provider.trilhaProvider.acompanhamento.Models.Contato;
import br.com.provider.trilhaProvider.acompanhamento.Repository.ContatoRepository;

@Service
public class ContatoService {

  /**     
   */
  @Autowired
  ContatoRepository contatoRepository;

  // public void setContatoRepository(ContatoRepository contatoRepository) {
  // this.contatoRepository = contatoRepository;
  // }

  /**
   * @param id
   * @return
   */
  public ContatoInfoDto getContatoByID(Long id) {
    return contatoRepository
        .findById(id)
        .map(contato -> {
          return contatoToContatoInfoDto(contato);
        })
        .orElse(null);
  }

  public List<ContatoInfoDto> getContatos(String nome) {
    if (nome == null) {
      return contatoListToContatoInfoDtoList(contatoRepository.findAll());
    } 

    return contatoListToContatoInfoDtoList(contatoRepository.findAllByNomeContains(nome));
  }

  public ContatoInfoDto saveContato(ContatoDto contatoDto) {
    Contato contato = contatoDtoToContato(contatoDto);

    return contatoToContatoInfoDto(contatoRepository.save(contato));
  }

  public ContatoInfoDto updateById(ContatoDto contatoDto, Long id) {
    return contatoRepository
        .findById(id)
        .map(model -> {
          model.setNome(contatoDto.getNome());
          model.setCpf(contatoDto.getCpf());
          model.setTelefone(contatoDto.getTelefone());
          model.setDataNascimento(contatoDto.getData_nascimento());
          model.setCep(contatoDto.getCep());
          model.setCidade(contatoDto.getCidade());
          model.setUf(contatoDto.getUf());
          model.setNumero(contatoDto.getNumero());
          model.setEndereco(contatoDto.getEndereco());
          model.setBairro(contatoDto.getBairro());
          model.setComplemento(contatoDto.getComplemento());
          model.setGenero(contatoDto.getGenero());

          return contatoToContatoInfoDto(contatoRepository.save(model));
        })
        .orElseThrow(() -> new RuntimeException("ID do contato inválido."));
  }

  public void deleteContato(Long id) {
    contatoRepository
        .findById(id)
        .map(contato -> {
          contatoRepository.delete(contato);

          return contato;
        })
        .orElseThrow(() -> new RuntimeException("ID do contato inválido."));
  }

  private Contato contatoDtoToContato(ContatoDto contatoDto) {
    Contato retorno = new Contato();
    retorno.setNome(contatoDto.getNome());
    retorno.setCpf(contatoDto.getCpf());
    retorno.setTelefone(contatoDto.getTelefone());
    retorno.setDataNascimento(contatoDto.getData_nascimento());
    retorno.setCep(contatoDto.getCep());
    retorno.setCidade(contatoDto.getCidade());
    retorno.setUf(contatoDto.getUf());
    retorno.setNumero(contatoDto.getNumero());
    retorno.setEndereco(contatoDto.getEndereco());
    retorno.setBairro(contatoDto.getBairro());
    retorno.setComplemento(contatoDto.getComplemento());
    retorno.setGenero(contatoDto.getGenero());
    retorno.setCriadorUid(contatoDto.getCriadorUid());
    retorno.setCriadorNome(contatoDto.getCriadorNome());

    return retorno;
  }

  private ContatoInfoDto contatoToContatoInfoDto(Contato contato) {
    ContatoInfoDto retorno = new ContatoInfoDto();
    retorno.setId(contato.getId());
    retorno.setNome(contato.getNome());
    retorno.setCpf(contato.getCpf());
    retorno.setTelefone(contato.getTelefone());
    retorno.setData_nascimento(contato.getDataNascimento());
    retorno.setCep(contato.getCep());
    retorno.setCidade(contato.getCidade());
    retorno.setUf(contato.getUf());
    retorno.setNumero(contato.getNumero());
    retorno.setEndereco(contato.getEndereco());
    retorno.setBairro(contato.getBairro());
    retorno.setComplemento(contato.getComplemento());
    retorno.setGenero(contato.getGenero().name());
    retorno.setCriadorUid(contato.getCriadorUid());
    retorno.setCriadorNome(contato.getCriadorNome());

    return retorno;
  }

  private List<ContatoInfoDto> contatoListToContatoInfoDtoList(List<Contato> contatos) {
    if (contatos.isEmpty()) {
      return Collections.emptyList();
    }

    return contatos
        .stream()
        .map(contato -> {
          return contatoToContatoInfoDto(contato);
        })
        .collect(Collectors.toList());
  }

}
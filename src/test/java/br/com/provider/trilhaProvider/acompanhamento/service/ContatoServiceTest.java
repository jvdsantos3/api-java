package br.com.provider.trilhaProvider.acompanhamento.service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.provider.trilhaProvider.acompanhamento.DTO.ContatoDto;
import br.com.provider.trilhaProvider.acompanhamento.DTO.ContatoInfoDto;
import br.com.provider.trilhaProvider.acompanhamento.Models.Contato;
import br.com.provider.trilhaProvider.acompanhamento.Repository.ContatoRepository;
import br.com.provider.trilhaProvider.acompanhamento.enums.ContatoGenero;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ContatoServiceTest {

  /**
   *
   */
  private static final int INDEX = 0;
  private static final long ID = 1L;
  private static final String NOME = "Nome";
  private static final String CPF = "53514774242";
  private static final String TELEFONE = "8590909090";
  private static final LocalDate NOW = LocalDate.now();
  private static final String CEP = "69901761";
  private static final String CIDADE = "Rio Branco";
  private static final String UF = "AC";
  private static final String NUMERO = "1000";
  private static final String ENDERECO = "Travessa do Barbeiro";
  private static final String BAIRRO = "Vitória";
  private static final String COMPLEMENTO = null;
  private static final ContatoGenero GENEROENUM = ContatoGenero.Masculino;
  private static final String GENEROSTRING = "Masculino";
  private static final String CRIADORUID = "fvLv5ZAsWmdGcf4aTv3m3OH0Wk12";
  private static final String CRIADORNOME = "Criador Nome";

  @InjectMocks
  private ContatoService service;

  @Mock
  private ContatoRepository repository;

  private Contato contato;
  private ContatoDto contatoDto;
  private ContatoInfoDto contatoInfoDto;
  private Optional<Contato> optionalContato;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);

    startContato();
  }

  @Test
  void whenGetContatoByIdThenReturnAnContatoInfoDtoInstance() {
    when(repository.findById(anyLong())).thenReturn(optionalContato);

    ContatoInfoDto response = service.getContatoByID(ID);

    assertNotNull(response);

    assertEquals(ContatoInfoDto.class, response.getClass());
    assertEquals(ID, response.getId());    
    assertEquals(NOME, response.getNome());
    assertEquals(CPF, response.getCpf());
    assertEquals(TELEFONE, response.getTelefone());
    assertEquals(NOW, response.getData_nascimento());
    assertEquals(CEP, response.getCep());
    assertEquals(CIDADE, response.getCidade());
    assertEquals(UF, response.getUf());
    assertEquals(ENDERECO, response.getEndereco());
    assertEquals(BAIRRO, response.getBairro());
    assertEquals(COMPLEMENTO, response.getComplemento());
    assertEquals(GENEROSTRING, response.getGenero());
    assertEquals(CRIADORUID, response.getCriadorUid());
    assertEquals(CRIADORNOME, response.getCriadorNome());
  }

  @Test
  void whenGetContatosWithoutNomeThenReturnAnListOfContatos() {
    when(repository.findAll()).thenReturn(List.of(contato));

    List<ContatoInfoDto> response = service.getContatos(null);

    assertNotNull(response);

    assertEquals(1, response.size());
    assertEquals(ContatoInfoDto.class, response.get(INDEX).getClass());
    assertEquals(ID, response.get(INDEX).getId());    
    assertEquals(NOME, response.get(INDEX).getNome());
    assertEquals(CPF, response.get(INDEX).getCpf());
    assertEquals(TELEFONE, response.get(INDEX).getTelefone());
    assertEquals(NOW, response.get(INDEX).getData_nascimento());
    assertEquals(CEP, response.get(INDEX).getCep());
    assertEquals(CIDADE, response.get(INDEX).getCidade());
    assertEquals(UF, response.get(INDEX).getUf());
    assertEquals(ENDERECO, response.get(INDEX).getEndereco());
    assertEquals(BAIRRO, response.get(INDEX).getBairro());
    assertEquals(COMPLEMENTO, response.get(INDEX).getComplemento());
    assertEquals(GENEROSTRING, response.get(INDEX).getGenero());
    assertEquals(CRIADORUID, response.get(INDEX).getCriadorUid());
    assertEquals(CRIADORNOME, response.get(INDEX).getCriadorNome());
  }

  @Test
  void whenGetContatosWithNomeThenReturnAnListOfContatos() {
    when(repository.findAllByNomeContains(NOME)).thenReturn(List.of(contato));

    List<ContatoInfoDto> response = service.getContatos(NOME);

    assertNotNull(response);

    assertEquals(1, response.size());
    assertEquals(ContatoInfoDto.class, response.get(INDEX).getClass());
    assertEquals(ID, response.get(INDEX).getId());    
    assertEquals(NOME, response.get(INDEX).getNome());
    assertEquals(CPF, response.get(INDEX).getCpf());
    assertEquals(TELEFONE, response.get(INDEX).getTelefone());
    assertEquals(NOW, response.get(INDEX).getData_nascimento());
    assertEquals(CEP, response.get(INDEX).getCep());
    assertEquals(CIDADE, response.get(INDEX).getCidade());
    assertEquals(UF, response.get(INDEX).getUf());
    assertEquals(ENDERECO, response.get(INDEX).getEndereco());
    assertEquals(BAIRRO, response.get(INDEX).getBairro());
    assertEquals(COMPLEMENTO, response.get(INDEX).getComplemento());
    assertEquals(GENEROSTRING, response.get(INDEX).getGenero());
    assertEquals(CRIADORUID, response.get(INDEX).getCriadorUid());
    assertEquals(CRIADORNOME, response.get(INDEX).getCriadorNome());
  }

  @Test
  void whenGetContatosWithInvalidNomeThenReturnAnEmptyList() {
    when(repository.findAllByNomeContains("Teste")).thenReturn(Collections.emptyList());

    List<ContatoInfoDto> response = service.getContatos("Teste");

    assertNotNull(response);

    assertEquals(0, response.size());
  }

  @Test
  void whenSaveContatoThenReturnAnContatoInstance() {
    when(repository.save(any())).thenReturn(contato);

    ContatoInfoDto response = service.saveContato(contatoDto);

    assertNotNull(response);

    assertEquals(ContatoInfoDto.class, response.getClass());
    assertEquals(ID, response.getId());    
    assertEquals(NOME, response.getNome());
    assertEquals(CPF, response.getCpf());
    assertEquals(TELEFONE, response.getTelefone());
    assertEquals(NOW, response.getData_nascimento());
    assertEquals(CEP, response.getCep());
    assertEquals(CIDADE, response.getCidade());
    assertEquals(UF, response.getUf());
    assertEquals(ENDERECO, response.getEndereco());
    assertEquals(BAIRRO, response.getBairro());
    assertEquals(COMPLEMENTO, response.getComplemento());
    assertEquals(GENEROSTRING, response.getGenero());
    assertEquals(CRIADORUID, response.getCriadorUid());
    assertEquals(CRIADORNOME, response.getCriadorNome());
  }

  @Test
  void deleteWithSucess() {
    when(repository.findById(anyLong())).thenReturn(optionalContato);
    doNothing().when(repository).delete(any());
    service.deleteContato(ID);
    verify(repository, times(1)).delete(any());
  }

  private void startContato() {
    // Contato
    contato = new Contato();

    contato.setId(ID);
    contato.setNome(NOME);
    contato.setCpf(CPF);
    contato.setTelefone(TELEFONE);
    contato.setDataNascimento(NOW);
    contato.setCep(CEP);
    contato.setCidade(CIDADE);
    contato.setUf(UF);
    contato.setNumero(NUMERO);
    contato.setEndereco(ENDERECO);
    contato.setBairro(BAIRRO);
    contato.setComplemento(COMPLEMENTO);
    contato.setGenero(GENEROENUM);
    contato.setCriadorUid(CRIADORUID);
    contato.setCriadorNome(CRIADORNOME);

    // ContatoDto
    contatoDto = new ContatoDto();

    contatoDto.setNome(NOME);
    contatoDto.setCpf(CPF);
    contatoDto.setTelefone(TELEFONE);
    contatoDto.setData_nascimento(NOW);
    contatoDto.setCep(CEP);
    contatoDto.setCidade(CIDADE);
    contatoDto.setUf(UF);
    contatoDto.setNumero(NUMERO);
    contatoDto.setEndereco(ENDERECO);
    contatoDto.setBairro(BAIRRO);
    contatoDto.setComplemento(COMPLEMENTO);
    contatoDto.setGenero(GENEROENUM);
    contatoDto.setCriadorUid(CRIADORUID);
    contatoDto.setCriadorNome(CRIADORNOME);

    // ContatoInfoDto
    contatoInfoDto = new ContatoInfoDto();

    contatoInfoDto.setId(ID);
    contatoInfoDto.setNome(NOME);
    contatoInfoDto.setCpf(CPF);
    contatoInfoDto.setTelefone(TELEFONE);
    contatoInfoDto.setData_nascimento(NOW);
    contatoInfoDto.setCep(CEP);
    contatoInfoDto.setCidade(CIDADE);
    contatoInfoDto.setUf(UF);
    contatoInfoDto.setNumero(NUMERO);
    contatoInfoDto.setEndereco(ENDERECO);
    contatoInfoDto.setBairro(BAIRRO);
    contatoInfoDto.setComplemento(COMPLEMENTO);
    contatoInfoDto.setGenero(GENEROSTRING);
    contatoInfoDto.setCriadorUid(CRIADORUID);
    contatoInfoDto.setCriadorNome(CRIADORNOME);

    // OptionalContato
    optionalContato = Optional.of(contato);
  }
}

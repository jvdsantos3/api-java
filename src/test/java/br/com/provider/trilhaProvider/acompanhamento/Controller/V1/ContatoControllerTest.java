package br.com.provider.trilhaProvider.acompanhamento.Controller.V1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.provider.trilhaProvider.acompanhamento.DTO.ContatoDto;
import br.com.provider.trilhaProvider.acompanhamento.DTO.ContatoInfoDto;
import br.com.provider.trilhaProvider.acompanhamento.service.ContatoService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class ContatoControllerTest {

  @MockBean
  private ContatoService service;

  @Autowired
  private MockMvc mvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  @DisplayName("Deve retornar o contato criado")
  public void testCreateNewContato() throws Exception {
    long id = 1L;

    ContatoInfoDto contato = criaContatoInfoDto(id);

    when(service.getContatoByID(id)).thenReturn(null);
    when(service.saveContato(any(ContatoDto.class))).thenReturn(contato);

    mvc.perform(
      post("/contatos")
      .contentType(MediaType.APPLICATION_JSON)
      .content(objectMapper.writeValueAsString(contato))
    )
      .andExpect(status().isCreated())
      .andDo(print());
  }

  // @Test
  // @DisplayName("Deve retornar o contato atualizado")
  // public void testUpdateContato() throws Exception {
  //   long id = 1L;

  //   ContatoInfoDto contato = criaContatoInfoDto(id);

  //   when(service.getContatoByID(id)).thenReturn(null);
  //   when(service.saveContato(any(ContatoDto.class))).thenReturn(contato);

  //   mvc.perform(
  //     put("/contatos/{id}", id)
  //     .contentType(MediaType.APPLICATION_JSON)
  //     .content(objectMapper.writeValueAsString(contato))
  //   )
  //     .andExpect(status().isOk())
  //     .andDo(print());
  // }

  @Test
  @DisplayName("Deve retornar o contato pelo ID")
  public void testFindById() throws Exception {
    long id = 1L;

    ContatoInfoDto contato = criaContatoInfoDto(id);

    when(service.getContatoByID(id)).thenReturn(contato);

    mvc.perform(get("/contatos/{id}", id))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.id").value(id))      
      .andExpect(jsonPath("$.nome").value(contato.getNome()))      
      .andExpect(jsonPath("$.cpf").value(contato.getCpf()))
      .andExpect(jsonPath("$.telefone").value(contato.getTelefone()))
      .andExpect(jsonPath("$.data_nascimento").value(contato.getData_nascimento().toString()))
      .andExpect(jsonPath("$.cep").value(contato.getCep()))
      .andExpect(jsonPath("$.cidade").value(contato.getCidade()))      
      .andExpect(jsonPath("$.uf").value(contato.getUf()))
      .andExpect(jsonPath("$.numero").value(contato.getNumero()))
      .andExpect(jsonPath("$.endereco").value(contato.getEndereco()))
      .andExpect(jsonPath("$.bairro").value(contato.getBairro()))
      .andExpect(jsonPath("$.complemento").value(contato.getComplemento()))      
      .andExpect(jsonPath("$.genero").value(contato.getGenero()))
      .andExpect(jsonPath("$.criadorUid").value(contato.getCriadorUid()))
      .andExpect(jsonPath("$.criadorNome").value(contato.getCriadorNome()))
      .andDo(print());
  }

  @Test
  @DisplayName("Deve retornar um lista de contatos")
  public void testGetContatosWithoutNome() throws Exception {
    List<ContatoInfoDto> contatos = new ArrayList<>();

    ContatoInfoDto contato1 = criaContatoInfoDto(1L);    
    ContatoInfoDto contato2 = criaContatoInfoDto(2L);

    contatos.add(contato1);    
    contatos.add(contato2);

    when(service.getContatos(null)).thenReturn(contatos);

    mvc.perform(get("/contatos"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$[0].id").value(contato1.getId()))        
      .andExpect(jsonPath("$[1].id").value(contato2.getId()))  
      .andDo(print());
  }

  @Test
  @DisplayName("Deve retornar status no content")
  public void testdeleteContato() throws Exception {
    long id = 1L;
    
    doNothing().when(service).deleteContato(anyLong());

    mvc.perform(delete("/contatos/{id}", id))
      .andExpect(status().isNoContent())
      .andDo(print());
  }

  private ContatoInfoDto criaContatoInfoDto(Long id) {
    ContatoInfoDto contatoInfoDto = new ContatoInfoDto();

    contatoInfoDto.setId(id);
    contatoInfoDto.setNome("Nome");
    contatoInfoDto.setCpf("53514774242");
    contatoInfoDto.setTelefone("8590909090");
    contatoInfoDto.setData_nascimento(LocalDate.now());    
    contatoInfoDto.setCep("69901761");
    contatoInfoDto.setCidade("Rio Branco");
    contatoInfoDto.setUf("AC");
    contatoInfoDto.setNumero("1000");
    contatoInfoDto.setEndereco("Travessa do Barbeiro");
    contatoInfoDto.setBairro("Vitória");
    contatoInfoDto.setComplemento(null);
    contatoInfoDto.setGenero("Masculino");
    contatoInfoDto.setCriadorUid("fvLv5ZAsWmdGcf4aTv3m3OH0Wk12");
    contatoInfoDto.setCriadorNome("Criador Nome");

    return contatoInfoDto;
  }
}

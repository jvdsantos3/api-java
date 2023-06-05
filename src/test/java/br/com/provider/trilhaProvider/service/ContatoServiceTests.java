package br.com.provider.trilhaProvider.service;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.provider.trilhaProvider.acompanhamento.DTO.ContatoDto;
import br.com.provider.trilhaProvider.acompanhamento.Models.Contato;
import br.com.provider.trilhaProvider.acompanhamento.Repository.ContatoRepository;
import br.com.provider.trilhaProvider.acompanhamento.service.ContatoService;

public class ContatoServiceTests {

        private ContatoService gestorService;

        @Mock
        private ContatoRepository gestorRepository;

        @Before
        public void setupMock() {

                MockitoAnnotations.initMocks(this);
                gestorService = new ContatoService();
                gestorService.setContatoRepository(gestorRepository);
        }

        private Contato criarGestorGet(Long id) {
                Contato contato = new Contato();

                contato.setId(id);
                contato.setNomeContato("Nome");
                contato.setDataAniversario(new Date());

                return contato;
        }

        private ContatoDto criarDto(Contato contato) {

                ContatoDto retorno = new ContatoDto();

                retorno.setId_Contato(contato.getId());
                retorno.setNomeContato(contato.getNomeContato());
                retorno.setDataAniversario(contato.getDataAniversario());

                return retorno;
        }

        @Test
        public void testGetAllGestor() {

                List<Contato> contatos = new ArrayList<>();

                contatos.add(criarGestorGet(1L));
                contatos.add(criarGestorGet(2L));

                when(gestorRepository.findAll()).thenReturn(contatos);
                List<ContatoDto> retornoContatos = gestorService.GetAll();

                assertTrue("A lista não pode ser nula: ", retornoContatos.size() > 0);

                assertTrue("Id não pode ser nulo: ", retornoContatos.get(0).getId_Contato() > 0);

                assertTrue("Nome do contato não pode ser nulo: : ", retornoContatos.get(0)
                                .getNomeContato().equals(contatos.get(0).getNomeContato()));

                assertTrue("Data de admição não pode ser nulo: ", retornoContatos.get(0).getDataAniversario()
                                .equals(contatos.get(0).getDataAniversario()));

        }

}

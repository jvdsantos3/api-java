package br.com.provider.trilhaProvider.acompanhamento.Controller.V1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.provider.trilhaProvider.acompanhamento.DTO.ContatoDto;
import br.com.provider.trilhaProvider.acompanhamento.DTO.ContatoInfoDto;
import br.com.provider.trilhaProvider.acompanhamento.service.ContatoService;

@RestController
@RequestMapping("/contatos")
public class ContatoController {

  @Autowired
  private ContatoService contatoService;

  @CrossOrigin
  @GetMapping("/{id}")
  public ResponseEntity<ContatoInfoDto> findById(@PathVariable Long id) {
    try {
      ContatoInfoDto contato = contatoService.getContatoByID(id);

      if (contato == null) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
      }

      return new ResponseEntity<>(contato, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @CrossOrigin
  @GetMapping("")
  public ResponseEntity<List<ContatoInfoDto>> getAllContatos() {
    try {
      List<ContatoInfoDto> contatos = contatoService.GetAllContatos();

      return new ResponseEntity<>(contatos, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @CrossOrigin
  @GetMapping("filtro")
  public ResponseEntity<List<ContatoInfoDto>> searchByNome(@RequestParam String nome) {
    try {
      List<ContatoInfoDto> contatos = contatoService.searchByNome(nome);

      return new ResponseEntity<>(contatos, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

  }

  @CrossOrigin
  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping
  public ResponseEntity<ContatoInfoDto> createNewContato(@RequestBody ContatoDto contato) {
    try {
      System.out.println(contato);
      ContatoInfoDto contatoInfoDto = contatoService.saveContato(contato);

      return new ResponseEntity<>(contatoInfoDto, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @CrossOrigin
  @PutMapping("/{id}")
  public ResponseEntity<ContatoInfoDto> updateContato(@RequestBody ContatoDto contato, @PathVariable Long id) {
    try {
      ContatoInfoDto contatoInfoDto = contatoService.updateById(contato, id);

      if (contatoInfoDto == null) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }

      return new ResponseEntity<>(contatoInfoDto, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

  @CrossOrigin
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public ResponseEntity<Void> deleteContato(@PathVariable Long id) {
    try {
      contatoService.deleteContato(id);

      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}

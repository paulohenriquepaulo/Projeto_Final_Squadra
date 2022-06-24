package br.com.squadra.app.controller;

import br.com.squadra.app.dto.pessoa.*;
import br.com.squadra.app.mapper.PessoaMapper;
import br.com.squadra.app.model.Pessoa;
import br.com.squadra.app.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    private PessoaService service;

    @Autowired
    private PessoaMapper mapper;

    @PostMapping
    public ResponseEntity<List<PessoaResponseDTO>> cadastrarPessoa(@RequestBody @Valid PessoaResquestDTO pessoaResquestDTO) {
        List<Pessoa> pessoas = service.cadastrarPessoa(mapper.toPessoa(pessoaResquestDTO));
        List<PessoaResponseDTO> dtos = pessoas.stream().map(pessoa -> mapper.toPessoaPorCodigoResponseDTO(pessoa))
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @PutMapping
    public ResponseEntity<List<PessoaResponseDTO>> atualizarPessoa(@RequestBody @Valid PessoaRequestAtualizarDTO pessoaRequestAtualizarDTO) {
        List<Pessoa> pessoas = service.atulaziarPessoa(mapper.converterListaEnderecoAtualizar(pessoaRequestAtualizarDTO));
        List<PessoaResponseDTO> dtos = pessoas.stream().map(pessoa -> mapper.toPessoaPorCodigoResponseDTO(pessoa))
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping
    public ResponseEntity<?> buscarPessoa(
            @RequestParam(required = false) Long codigoPessoa,
            @RequestParam(required = false) String login,
            @RequestParam(required = false) Integer status) {
        Object retorno = service.buscarPessoa(codigoPessoa, login, status);
        if (retorno instanceof Pessoa) {
            return ResponseEntity.ok(mapper.toPessoaPorCodigoResponseDTO((Pessoa) retorno));
        } else {
            List<PessoaResponseDTO> dtos = ((List<Pessoa>) retorno).stream().map(pessoa -> mapper.toPessoaPorCodigoResponseDTO(pessoa))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(dtos);
        }
    }

}

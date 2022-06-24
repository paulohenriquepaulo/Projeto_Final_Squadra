package br.com.squadra.app.controller;

import br.com.squadra.app.dto.uf.UfRequestAtualizarDTO;
import br.com.squadra.app.dto.uf.UfRequestDTO;
import br.com.squadra.app.dto.uf.UfResponseDTO;
import br.com.squadra.app.mapper.UfMapper;
import br.com.squadra.app.model.Uf;
import br.com.squadra.app.service.UfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/uf")
public class UfController {

    @Autowired
    private UfService service;

    @Autowired
    private UfMapper mapper;

    @PostMapping
    public ResponseEntity<List<UfResponseDTO>> cadastrarUf(@RequestBody @Valid UfRequestDTO ufDTO) {
        List<Uf> ufs = service.cadastrarUf(mapper.toUf(ufDTO));
        List<UfResponseDTO> dtos = ufs.stream().map(uf -> mapper.toUfResponseDTO(uf))
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @PutMapping
    public ResponseEntity<List<UfResponseDTO>> atualizarUf(@RequestBody @Valid UfRequestAtualizarDTO ufRequestAtualizarDTO) {
        List<Uf> ufs = service.atulaziarUf(mapper.toUf(ufRequestAtualizarDTO));
        List<UfResponseDTO> dtos = ufs.stream().map(uf -> mapper.toUfResponseDTO(uf))
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping
    public ResponseEntity<?> buscarUf(
            @RequestParam(required = false) Integer codigoUF,
            @RequestParam(required = false) String sigla,
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) Integer status) {
        Object retorno = service.buscarUf(codigoUF, sigla, nome, status);
        if (retorno instanceof Uf) {
            return ResponseEntity.ok(mapper.toUfResponseDTO((Uf) retorno));
        } else {
            List<UfResponseDTO> dtos = ((List<Uf>)retorno).stream().map(uf -> mapper.toUfResponseDTO(uf))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(dtos);
        }
    }

    @DeleteMapping("/{sigla}")
    public ResponseEntity<List<UfResponseDTO>> deletarPorSigla(@PathVariable String sigla) {
        List<Uf> ufs = service.deletarPorSigla(sigla);
        List<UfResponseDTO> dtos = ufs.stream().map(uf -> mapper.toUfResponseDTO(uf))
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

}

package br.com.squadra.app.controller;

import br.com.squadra.app.dto.bairro.BairroResponseDTO;
import br.com.squadra.app.dto.bairro.BairroResquestAtualizarDTO;
import br.com.squadra.app.dto.bairro.BairroResquestDTO;
import br.com.squadra.app.mapper.BairroMapper;
import br.com.squadra.app.model.Bairro;
import br.com.squadra.app.service.BairroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bairro")
public class BairroController {

    @Autowired
    private BairroService service;

    @Autowired
    private BairroMapper mapper;

    @PostMapping
    public ResponseEntity<List<BairroResponseDTO>> cadastrarBairro(@RequestBody @Valid BairroResquestDTO bairroResquestDTO) {
        List<Bairro> bairros = service.cadastrarBairro(mapper.toBairro(bairroResquestDTO));
        List<BairroResponseDTO> dtos = bairros.stream().map(bairro -> mapper.toBairroResponseDTO(bairro))
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @DeleteMapping("/{codigoBairro}")
    public ResponseEntity<List<BairroResponseDTO>> deletarBairro(@PathVariable Long codigoBairro) {
        List<Bairro> bairros = service.deletarPorCodigo(codigoBairro);
        List<BairroResponseDTO> dtos = bairros.stream().map(bairro -> mapper.toBairroResponseDTO(bairro))
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @PutMapping
    public ResponseEntity<List<BairroResponseDTO>> atualizarBairro(@RequestBody @Valid BairroResquestAtualizarDTO resquestAtualizarDTO) {
        List<Bairro> bairros =  service.atulaziarBairro(mapper.toBairro(resquestAtualizarDTO));
        List<BairroResponseDTO> dtos = bairros.stream().map(bairro -> mapper.toBairroResponseDTO(bairro))
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping
    public ResponseEntity<?> buscarBairro(
            @RequestParam(required = false) Long codigoBairro,
            @RequestParam(required = false) Long codigoMunicipio,
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) Integer status) {
        Object retorno = service.buscarBairro(codigoBairro, codigoMunicipio, nome, status);
        if (retorno instanceof Bairro) {
            return ResponseEntity.ok(mapper.toBairroResponseDTO((Bairro) retorno));
        } else {
            List<BairroResponseDTO> dtos = ((List<Bairro>)retorno).stream().map(bairro -> mapper.toBairroResponseDTO(bairro))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(dtos);
        }
    }

}

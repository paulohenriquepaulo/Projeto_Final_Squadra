package br.com.squadra.app.controller;

import br.com.squadra.app.dto.municipio.MunicipioResponseDTO;
import br.com.squadra.app.dto.municipio.MunicipioResquestAtualizarDTO;
import br.com.squadra.app.dto.municipio.MunicipioResquestDTO;
import br.com.squadra.app.mapper.MunicipioMapper;
import br.com.squadra.app.model.Municipio;
import br.com.squadra.app.service.MunicipioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/municipio")
public class MunicipioController {

    @Autowired
    private MunicipioService service;

    @Autowired
    private MunicipioMapper mapper;

    @PostMapping
    public ResponseEntity<List<MunicipioResponseDTO>> cadastrarMunicipio(@RequestBody @Valid MunicipioResquestDTO municipioResquestDTO) {
        List<Municipio> municipios = service.cadastrarMunicipio(mapper.toMunicipio(municipioResquestDTO));
        List<MunicipioResponseDTO> dtos = municipios.stream().map(municipio -> mapper.toMunicipioResponseDTO(municipio))
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @DeleteMapping("/{codigoMunicipio}")
    public ResponseEntity<List<MunicipioResponseDTO>> deletarPorCodigoMunicipio(@PathVariable Long codigoMunicipio) {
        List<Municipio> municipios = service.deletarPorCodigo(codigoMunicipio);
        List<MunicipioResponseDTO> dtos = municipios.stream().map(municipio -> mapper.toMunicipioResponseDTO(municipio))
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @PutMapping
    public ResponseEntity<List<MunicipioResponseDTO>> atualizarMunicipio(@RequestBody @Valid MunicipioResquestAtualizarDTO municipioResquestAtualizarDTO) {
        List<Municipio> municipios = service.atualizarMunicipio(mapper.toMunicipio(municipioResquestAtualizarDTO));
        List<MunicipioResponseDTO> dtos = municipios.stream().map(municipio -> mapper.toMunicipioResponseDTO(municipio))
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }


    @GetMapping
    public ResponseEntity<?> buscarMunicipio(
            @RequestParam(required = false) Long codigoMunicipio,
            @RequestParam(required = false) Integer codigoUF,
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) Integer status) {
        Object retorno = service.buscarMunicipio(codigoMunicipio, codigoUF, nome, status);
        if (retorno instanceof Municipio) {
            return ResponseEntity.ok(mapper.toMunicipioResponseDTO((Municipio) retorno));
        } else {
            List<MunicipioResponseDTO> dtos = ((List<Municipio>)retorno).stream().map(municipio -> mapper.toMunicipioResponseDTO(municipio))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(dtos);
        }
    }

}

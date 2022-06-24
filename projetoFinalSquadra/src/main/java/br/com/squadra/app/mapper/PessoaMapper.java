package br.com.squadra.app.mapper;

import br.com.squadra.app.dto.endereco.EnderecoBairroResponseDTO;
import br.com.squadra.app.dto.endereco.EnderecoResponseDTO;
import br.com.squadra.app.dto.endereco.EnderecoResquestAtualizarDTO;
import br.com.squadra.app.dto.endereco.EnderecoResquestDTO;
import br.com.squadra.app.dto.pessoa.*;
import br.com.squadra.app.model.Endereco;
import br.com.squadra.app.model.Pessoa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface PessoaMapper extends BaseMapper {

    @Mapping(target = "enderecos", source = "enderecos", qualifiedByName = "converterListaEnderecoModel")
    @Mapping(target = "status", source = "status", qualifiedByName = "statusEnumConverter")
    Pessoa toPessoa(PessoaResquestDTO pessoaResquestDTO);

    @Mapping(target = "status", source = "status", qualifiedByName = "statusIntegerConverter")
    @Mapping(target = "enderecos", source = "enderecos", qualifiedByName = "converterListaEnderecoDTO")
    PessoaResponseCadastroDTO toPessoaResponseDTO(Pessoa pessoa);

    @Mapping(target = "status", source = "status", qualifiedByName = "statusIntegerConverter")
    @Mapping(target = "enderecos", source = "enderecos", qualifiedByName = "converterListaEnderecoPorCodigoDTO")
    PessoaResponseDTO toPessoaPorCodigoResponseDTO(Pessoa pessoa);

    @Mapping(target = "enderecos", source = "enderecos", qualifiedByName = "converterListaEnderecoAtualizar")
    @Mapping(target = "status", source = "status", qualifiedByName = "statusEnumConverter")
    Pessoa converterListaEnderecoAtualizar(PessoaRequestAtualizarDTO PessoaRequestAtualizarDTO);

    @Named("converterListaEnderecoModel")
    default List<Endereco> converterListaEnderecoModel(List<EnderecoResquestDTO> enderecos) {
        return enderecos.stream().map(this::mapEnderecoModel).collect(Collectors.toList());
    }

    @Named("converterListaEnderecoDTO")
    default List<EnderecoResponseDTO> converterListaEnderecoDTO(List<Endereco> enderecos) {
        return enderecos.stream().map(this::mapEnderecoDTO).collect(Collectors.toList());
    }

    @Named("converterListaEnderecoPorCodigoDTO")
    default List<EnderecoBairroResponseDTO> converterListaEnderecoPorCodigoDTO(List<Endereco> enderecos) {
        return enderecos.stream().map(this::mapEnderecoPorCodigo).collect(Collectors.toList());
    }

    @Named("converterListaEnderecoAtualizar")
    default List<Endereco> converterListaEnderecoAtualizar(List<EnderecoResquestAtualizarDTO> enderecos) {
        return enderecos.stream().map(this::mapEnderecoModelAtualizar).collect(Collectors.toList());
    }

    @Mapping(target = "bairro.codigoBairro", source = "codigoBairro")
    Endereco mapEnderecoModel(EnderecoResquestDTO enderecoResquestDTO);

    @Mapping(target = "bairro.codigoBairro", source = "codigoBairro")
    @Mapping(target = "pessoa.codigoPessoa", source = "codigoPessoa")
    Endereco mapEnderecoModelAtualizar(EnderecoResquestAtualizarDTO enderecoResquestDTO);


    @Mapping(target = "codigoBairro", source = "bairro.codigoBairro")
    @Mapping(target = "codigoPessoa", source = "pessoa.codigoPessoa")
    EnderecoResponseDTO mapEnderecoDTO(Endereco endereco);

    @Mapping(target = "codigoBairro", source = "bairro.codigoBairro")
    @Mapping(target = "codigoPessoa", source = "pessoa.codigoPessoa")
    @Mapping(target = "bairro.status", source = "bairro.status", qualifiedByName = "statusIntegerConverter")
    @Mapping(target = "bairro.municipio.status", source = "bairro.municipio.status", qualifiedByName = "statusIntegerConverter")
    @Mapping(target = "bairro.municipio.uf.status", source = "bairro.municipio.uf.status", qualifiedByName = "statusIntegerConverter")
    @Mapping(target = "bairro.codigoMunicipio", source = "bairro.municipio.codigoMunicipio")
    @Mapping(target = "bairro.municipio.codigoUF", source = "bairro.municipio.uf.codigoUF")
    EnderecoBairroResponseDTO mapEnderecoPorCodigo(Endereco endereco);


}

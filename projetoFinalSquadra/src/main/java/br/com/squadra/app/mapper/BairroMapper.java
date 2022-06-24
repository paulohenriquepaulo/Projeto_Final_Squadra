package br.com.squadra.app.mapper;

import br.com.squadra.app.dto.bairro.BairroResponseDTO;
import br.com.squadra.app.dto.bairro.BairroResquestAtualizarDTO;
import br.com.squadra.app.dto.bairro.BairroResquestDTO;
import br.com.squadra.app.model.Bairro;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BairroMapper extends BaseMapper {

    @Mapping(target = "municipio.codigoMunicipio", source = "codigoMunicipio")
    @Mapping(target = "status", source = "status", qualifiedByName = "statusEnumConverter")
    Bairro toBairro(BairroResquestDTO bairroResquestDTO);

    @Mapping(target = "status", source = "status", qualifiedByName = "statusEnumConverter")
    @Mapping(target = "codigoBairro", source = "codigoBairro")
    @Mapping(target = "municipio.codigoMunicipio", source = "codigoMunicipio")
    Bairro toBairro(BairroResquestAtualizarDTO bairroResquestAtualizarDTO);

    @Mapping(target = "status", source = "status", qualifiedByName = "statusIntegerConverter")
    @Mapping(target = "codigoMunicipio", source = "municipio.codigoMunicipio")
    BairroResponseDTO toBairroResponseDTO(Bairro bairro);

}

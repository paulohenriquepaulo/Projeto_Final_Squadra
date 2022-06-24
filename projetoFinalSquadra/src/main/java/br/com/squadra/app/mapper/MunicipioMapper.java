package br.com.squadra.app.mapper;

import br.com.squadra.app.dto.municipio.MunicipioResponseDTO;
import br.com.squadra.app.dto.municipio.MunicipioResquestAtualizarDTO;
import br.com.squadra.app.dto.municipio.MunicipioResquestDTO;
import br.com.squadra.app.model.Municipio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MunicipioMapper extends BaseMapper {

    @Mapping(target = "status", source = "status", qualifiedByName = "statusEnumConverter")
    @Mapping(target = "uf.codigoUF", source = "codigoUF")
    Municipio toMunicipio(MunicipioResquestDTO municipioResquestDTO);

    @Mapping(target = "status", source = "status", qualifiedByName = "statusEnumConverter")
    @Mapping(target = "uf.codigoUF", source = "codigoUF")
    @Mapping(target = "codigoMunicipio", source = "codigoMunicipio")
    Municipio toMunicipio(MunicipioResquestAtualizarDTO municipioResquestAtualizarDTO);


    @Mapping(target = "status", source = "status", qualifiedByName = "statusIntegerConverter")
    @Mapping(target = "codigoUF", source = "uf.codigoUF")
    @Mapping(target = "codigoMunicipio", source = "codigoMunicipio")
    MunicipioResponseDTO toMunicipioResponseDTO(Municipio municipio);


}

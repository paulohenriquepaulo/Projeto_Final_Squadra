package br.com.squadra.app.mapper;

import br.com.squadra.app.dto.uf.UfRequestAtualizarDTO;
import br.com.squadra.app.dto.uf.UfRequestDTO;
import br.com.squadra.app.dto.uf.UfResponseDTO;
import br.com.squadra.app.model.Uf;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UfMapper extends BaseMapper {

    @Mapping(target = "status", source = "status", qualifiedByName = "statusEnumConverter")
    Uf toUf(UfRequestDTO ufDTO);

    @Mapping(target = "status", source = "status", qualifiedByName = "statusEnumConverter")
    Uf toUf(UfRequestAtualizarDTO ufRequestAtualizarDTO);


    @Mapping(target = "status", source = "status", qualifiedByName = "statusIntegerConverter")
    UfResponseDTO toUfResponseDTO(Uf uf);

}

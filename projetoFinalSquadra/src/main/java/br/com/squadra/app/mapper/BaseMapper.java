package br.com.squadra.app.mapper;

import br.com.squadra.app.model.Status;
import org.mapstruct.Named;

public interface BaseMapper {

    @Named("statusIntegerConverter")
    default Integer statusIntegerConverter(Status status) {
        return status.getCodigo();
    }

    @Named("statusEnumConverter")
    default Status statusEnumConverter(Integer codigoStatus) {
        return Status.values()[codigoStatus - 1];
    }

}

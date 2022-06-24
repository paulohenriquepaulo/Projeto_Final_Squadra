package br.com.squadra.app.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<Status, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Status status) {
        if (status == null) {
            return null;
        }
        return status.getCodigo();
    }

    @Override
    public Status convertToEntityAttribute(Integer codigo) {
        if (codigo == null) {
            return null;
        }
        return Status.values()[codigo - 1];
    }

}
package br.com.squadra.app.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@MappedSuperclass
public class BaseEntity {

    @Column(name = "STATUS")
    @NotNull(message = "O status não pode ser nulo ou vazio!")
    private Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}

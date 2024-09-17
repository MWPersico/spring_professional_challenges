package com.devsuperior.rest_crud.dto;

import com.devsuperior.rest_crud.entities.Client;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class ClientDTO implements Serializable {
    private static final long serialVersionUID = 1l;

    private final Long id;
    private final String name;
    private final String cpf;
    private final Double income;
    private final LocalDate birthDate;
    private final Integer children;

    public ClientDTO(Long id, String name, String cpf, Double income, LocalDate birthDate, Integer children) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.income = income;
        this.birthDate = birthDate;
        this.children = children;
    }

    public ClientDTO(Client client){
        this(
                client.getId(),
                client.getName(),
                client.getCpf(),
                client.getIncome(),
                client.getBirthDate(),
                client.getChildren()
        );
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public Double getIncome() {
        return income;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Integer getChildren() {
        return children;
    }
}

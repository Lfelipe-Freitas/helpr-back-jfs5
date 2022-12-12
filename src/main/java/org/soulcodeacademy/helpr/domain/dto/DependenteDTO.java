package org.soulcodeacademy.helpr.domain.dto;

import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

public class DependenteDTO {

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @CPF(message = "CPF é inválido")
    @NotBlank(message = "CPF é obrigatório")
    private String cpf;

    @NotNull(message = "A data de nascimento é obrigatória ")
    private LocalDate dataNasc;

    @NotBlank(message = "A escolaridade é obrigatória ")
    private String escolaridade;

    @NotNull(message = "O id do responsável é obrigatória")
    private Integer idResponsavel;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(LocalDate dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getEscolaridade() {
        return escolaridade;
    }

    public void setEscolaridade(String escolaridade) {
        this.escolaridade = escolaridade;
    }

    public Integer getIdResponsavel() {
        return idResponsavel;
    }

    public void setIdResponsavel(Integer idResponsavel) {
        this.idResponsavel = idResponsavel;
    }
}


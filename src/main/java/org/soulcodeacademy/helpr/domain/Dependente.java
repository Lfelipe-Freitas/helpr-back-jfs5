package org.soulcodeacademy.helpr.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

//N dependentes pode estar relacionado a 1 funcionario
//Relação N:1
@Entity
public class Dependente {

    @Id //Chave primária PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Auto Increment
    private Integer idDependente;

    @Column(nullable = false, length = 120)
    private String nome;

    @Column(nullable = false, unique = true, length = 11)
    private String cpf;

    @Column(nullable = false, length = 10)
    private LocalDate dataNasc;

    @Column(nullable = false, length = 300)
    private String escolaridade;



    @ManyToOne // N:1
    @JoinColumn (name = "id_responsavel")
    private Funcionario funcionario;

    public Dependente() {
    }

    public Dependente(Integer idDependente, String nome, String cpf, LocalDate dataNasc, String escolaridade, Integer idResponsavel) {
        this.idDependente = idDependente;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNasc = dataNasc;
        this.escolaridade = escolaridade;
        this.funcionario = new Funcionario();
        this.funcionario.setId(idResponsavel);
    }

    public Dependente(String nome, String cpf, LocalDate parse, String escolaridade, Integer idResponsavel) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNasc = parse;
        this.escolaridade = escolaridade;
        this.funcionario = new Funcionario();
        this.funcionario.setId(idResponsavel);
    }

    public Integer getIdDependente() {
        return idDependente;
    }

    public void setIdDependente(Integer idDependente) {
        this.idDependente = idDependente;
    }

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

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;

    }


}

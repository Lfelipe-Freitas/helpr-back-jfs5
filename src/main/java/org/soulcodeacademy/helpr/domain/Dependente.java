package org.soulcodeacademy.helpr.domain;

import javax.persistence.*;

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

    @Column(nullable = false, length = 300)
    private String escolaridade;

    @ManyToOne // N:1
    @JoinColumn (name = "id_responsavel", nullable = false)
    private Funcionario idResponsavel;

    public Dependente() {
    }

    public Dependente(Integer idDependente, String nome, String cpf, String escolaridade, Funcionario responsavel) {
        this.idDependente = idDependente;
        this.nome = nome;
        this.cpf = cpf;
        this.escolaridade = escolaridade;
        this.idResponsavel = responsavel;
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

    public String getEscolaridade() {
        return escolaridade;
    }

    public void setEscolaridade(String escolaridade) {
        this.escolaridade = escolaridade;
    }

    public Funcionario getResponsavel() {
        return idResponsavel;
    }

    public void setResponsavel(Funcionario responsavel) {
        this.idResponsavel = responsavel;
    }
}

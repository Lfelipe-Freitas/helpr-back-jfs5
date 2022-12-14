package org.soulcodeacademy.helpr.services;

import org.soulcodeacademy.helpr.domain.Cargo;
import org.soulcodeacademy.helpr.domain.Dependente;
import org.soulcodeacademy.helpr.domain.Funcionario;
import org.soulcodeacademy.helpr.domain.dto.DependenteDTO;
import org.soulcodeacademy.helpr.repositories.DependenteRepository;
import org.soulcodeacademy.helpr.repositories.FuncionarioRepository;
import org.soulcodeacademy.helpr.services.errors.ParametrosInsuficientesError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class DependenteService {
    @Autowired
    private DependenteRepository dependenteRepository;

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private FuncionarioRepository funcionarioRepository;




    // Listar todos
    public List<Dependente> listarDependente(){
        return this.dependenteRepository.findAll();
    }

    //Listar id;

    public Dependente getDependente(Integer idDependente){
        Optional<Dependente> dependente = this.dependenteRepository.findById(idDependente);

        if(dependente.isEmpty()) {
            throw new RuntimeException("O dependente não foi encontrado!");
        }else{
            return dependente.get();
        }

    }
    //Filtrar por cpf;
    public Dependente getDependenteByCPF(String cpf){
        Dependente dependente = this.dependenteRepository.findByCpf(cpf);

        return dependente;
    }



    //Filtrar por faixa de datas;
    public List<Dependente> getByFaixaData(LocalDate data1, LocalDate data2){

        return dependenteRepository.buscarEntreDatas(data1, data2);
    }



    //Salvar dependente (deve ser menor de idade, checar isso); <---

    public Dependente salvar(DependenteDTO dto){
        Funcionario funcionario = this.funcionarioService.getFuncionario(dto.getIdResponsavel());

        Dependente dependenteNovo = new Dependente( dto.getNome(), dto.getCpf(), LocalDate.parse(dto.getDataNasc()),dto.getEscolaridade(),dto.getIdResponsavel());

        Period period = Period.between(LocalDate.parse(dto.getDataNasc()), LocalDate.now());

        if(period.getYears() >= 18){
            throw new RuntimeException("Não é permitido registrar dependentes maiores de 18 anos.");
        }

        if(!funcionarioRepository.exists(Example.of(dependenteNovo.getFuncionario()))){
            funcionarioRepository.save(dependenteNovo.getFuncionario());
        }

        return this.dependenteRepository.save(dependenteNovo);
    }





    //Atualizar dependente (não permitir mudar de responsável); <---

    public Dependente atualizar(Integer idDependente, DependenteDTO dto) {

        Dependente DependenteAtual = new Dependente();

        DependenteAtual.setIdDependente(idDependente);
        DependenteAtual.setNome(dto.getNome());
        DependenteAtual.setCpf(dto.getCpf());
        DependenteAtual.setDataNasc(dto.getDataNasc);
        DependenteAtual.setEscolaridade("escolaridade");
        DependenteAtual.setFuncionario(new Funcionario());
        DependenteAtual.getFuncionario().setId(dto.getIdResponsavel());

        Dependente dadosAntigos = dependenteRepository.findById(idDependente).get();

        if (dadosAntigos == null || dadosAntigos.getFuncionario().getId() != DependenteAtual.getFuncionario().getId()) {
            throw new RuntimeException("Não é permitido alterar o responsavel do dependente.");
        }

        return this.dependenteRepository.save(DependenteAtual);
    }

    //Deletar dependente;

    public void deletar(Integer idDependente) {
        this.dependenteRepository.deleteById(idDependente);
    }


    //Listar os dependentes de um funcionário por id.
    public List<Dependente> getDependenteByFuncionarioId(Integer idFuncionario){
        return this.dependenteRepository.findByFuncionarioId(idFuncionario);
    }

}

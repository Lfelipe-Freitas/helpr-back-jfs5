package org.soulcodeacademy.helpr.services;

import org.soulcodeacademy.helpr.domain.Cargo;
import org.soulcodeacademy.helpr.domain.Dependente;
import org.soulcodeacademy.helpr.domain.Funcionario;
import org.soulcodeacademy.helpr.domain.dto.DependenteDTO;
import org.soulcodeacademy.helpr.repositories.DependenteRepository;
import org.soulcodeacademy.helpr.services.errors.ParametrosInsuficientesError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class DependenteService {
    @Autowired
    DependenteRepository dependenteRepository;

    @Autowired
    FuncionarioService funcionarioService;




    // Listar todos
    public List<Dependente> listarDependente(){
        return this.dependenteRepository.findAll();
    }

    //Listar um dependente pelo id;

    public Dependente getDependente(Integer idDependente){
        Optional<Dependente> dependente = this.dependenteRepository.findById(idDependente);

        if(dependente.isEmpty()) {
            throw new RuntimeException("O dependente não foi encontrado!");
        }else{
            return dependente.get();
        }

    }
    //Filtrar por cpf;
    public List<Dependente> listarPorCpf(String valor) {
        return this.dependenteRepository.findByCpf(valor);
    }

    //Filtrar por faixa de datas;
    public List<Dependente> listarDependenteData(String data1, String data2) {
        return this.dependenteRepository.findByListarPorData(data1, data2);
    }

    //Salvar dependente (deve ser menor de idade, checar isso); <---

    public Dependente salvar(DependenteDTO dto) {
        Funcionario funcionario = this.funcionarioService.getFuncionario(dto.getIdResponsavel());
        Dependente dependente = new Dependente(null, dto.getNome(), dto.getCpf(), dto.getDataNasc(), dto.getEscolaridade(), dto.getIdResponsavel());

        Integer idade = (LocalDate.now()
                .minusDays(dto.getDataNasc().getDayOfMonth())
                .minusMonths(dto.getDataNasc().getMonthValue())
                .minusYears(dto.getDataNasc().getYear())).getYear();

        if (idade < 18) {

            throw new ParametrosInsuficientesError("O dependente não pode ser cadastrado!");
        } else {
            return this.dependenteRepository.save(dependente);

        }




    }

    //Atualizar dependente (não permitir mudar de responsável); <---

    public Dependente atualizar(Integer idDependente, DependenteDTO dto) {
        Dependente dependenteAtual = this.getDependente(idDependente);

        dependenteAtual.setNome(dto.getNome());
        dependenteAtual.setCpf(dto.getCpf());
        dependenteAtual.setDataNasc(dto.getDataNasc());
        dependenteAtual.setEscolaridade(dto.getEscolaridade());

        Dependente atualizado = this.dependenteRepository.save(dependenteAtual);
        return atualizado;
    };

    //Deletar dependente;

    public void deletar(Integer idDependente) {
        Dependente dependente = this.getDependente(idDependente);
        this.dependenteRepository.delete(dependente);
    }


    //Listar os dependentes de um funcionário por id.
    public List<Dependente> listarPorFuncionario(Integer idFuncionario) {
        Funcionario idResponsavel= this.funcionarioService.getFuncionario(idFuncionario);
        return this.dependenteRepository.findByIdResponsavel(idResponsavel.getId());
    }

}

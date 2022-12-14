package org.soulcodeacademy.helpr.controllers;

import org.soulcodeacademy.helpr.domain.Dependente;
import org.soulcodeacademy.helpr.domain.dto.DependenteDTO;
import org.soulcodeacademy.helpr.domain.Funcionario;
import org.soulcodeacademy.helpr.domain.dto.FuncionarioDTO;
import org.soulcodeacademy.helpr.services.DependenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
public class DependenteController {

    @Autowired
    private DependenteService dependenteService;

    //Listar todos
    @GetMapping("/dependentes")
    public List<Dependente> listarDependente() {
        return this.dependenteService.listarDependente();
    }

    //Listar por Id

    @GetMapping("/dependentes/{idDependente}")
    public Dependente getDependente(@PathVariable Integer idDependente) {
        return this.dependenteService.getDependente(idDependente);
    }


    //Listar por cpf
    @GetMapping("/dependentes/cpf/{cpf}")
    public Dependente getCpf(@PathVariable String cpf) {
        return this.dependenteService.getDependenteByCPF(cpf);
    }


    //Listar por datas
    @GetMapping("/dependentes/faixa/{data1}/{data2}")
    public List<Dependente> listarDependentesPorData(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data1,
                                                     @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data2) {
        return this.dependenteService.getByFaixaData(data1, data2);
    }

    //Salvar dependente ;

    @PostMapping("/dependentes")
    public Dependente salvar(@Valid @RequestBody DependenteDTO dto) {
        return dependenteService.salvar(dto);
    }

    //Atualizar dependente
    @PutMapping("/dependentes/{idDependente}")
    public Dependente atualizar(@PathVariable Integer idDependente, @Valid @RequestBody DependenteDTO dto) {
        Dependente atualizado = this.dependenteService.atualizar(idDependente, dto);
        return atualizado;
    }


    //Deletar dependente
    @DeleteMapping("/dependentes/{idDependente}")
    public void deletar(@PathVariable Integer idDependente) {
        this.dependenteService.deletar(idDependente);
    }

    //Listar funcionario pelo id
    @GetMapping("/dependentes/funcionarios/{idFuncionario}")
    public List<Dependente> listarDependenteByFuncionarioId(@PathVariable Integer idFuncionario){

        return dependenteService.getDependenteByFuncionarioId(idFuncionario);
    }

}





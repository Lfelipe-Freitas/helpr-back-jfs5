package org.soulcodeacademy.helpr.controllers;

import org.soulcodeacademy.helpr.domain.Dependente;
import org.soulcodeacademy.helpr.domain.Funcionario;
import org.soulcodeacademy.helpr.domain.dto.DependenteDTO;
import org.soulcodeacademy.helpr.domain.dto.FuncionarioDTO;
import org.soulcodeacademy.helpr.services.DependenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class DependenteController {

    @Autowired
    DependenteService dependenteService;

    //Listar todos
    @GetMapping("/dependentes")
    public List<Dependente> listarDependente() {
        return this.dependenteService.listarDependente();
    }

    //Listar por Id

    @GetMapping("/dependente/{idDependente}")
    public Dependente getDependente(@PathVariable Integer idDependente) {
        return this.dependenteService.getDependente(idDependente);
    }


    //Listar por cpf
    @GetMapping("/dependente/cpf/{cpf}")
    public List<Dependente> listarPorCpf(@RequestParam String valor) {
        return this.dependenteService.listarPorCpf(valor);
    }

    //Listar por datas
    @GetMapping("/dependente/faixa/{data1}/{data2}")
    public List<Dependente> listarDependenteData(@RequestParam String data1, @RequestParam String data2) {
        return this.dependenteService.listarDependenteData(data1, data2);


    }

    //Salvar dependente ;

    @PostMapping("/dependentes")
    public Dependente salvar(@Valid @RequestBody DependenteDTO dto) {
        Dependente dependente = this.dependenteService.salvar(dto);
        return dependente;
    }

}

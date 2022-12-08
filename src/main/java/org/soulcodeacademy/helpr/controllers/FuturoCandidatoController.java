package org.soulcodeacademy.helpr.controllers;
import org.soulcodeacademy.helpr.domain.FuturoCandidato;
import org.soulcodeacademy.helpr.domain.dto.FuturoCandidatoDTO;
import org.soulcodeacademy.helpr.services.FuturoCandidatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class FuturoCandidatoController {
    @Autowired
    private FuturoCandidatoService futuroCandidatoService;

    // /futurocandidato (GET)
    @GetMapping("/futurocandidato")
    public List<FuturoCandidato> listar() {
        return this.futuroCandidatoService.listar();
    }



    // /futurocandidato/{id} (GET)
    @GetMapping("/futurocandidato/{idFuturocandidato}")
    public FuturoCandidato getFuturoCandidato(@PathVariable Integer idFuturoCandidato) {
        return this.futuroCandidatoService.getfuturoCandidato(idFuturoCandidato);
    }

    // POST = Representa inserção de dados
    @PostMapping("/futuroCandidato")
    public FuturoCandidato salvar(@Valid @RequestBody FuturoCandidatoDTO dto) {
       FuturoCandidato futuroCandidato = this.futuroCandidatoService.salvar(dto) ;
        return futuroCandidato;
    }

    // PUT = Representa substituição de dados
    @PutMapping("/futurocandidato/{idFuturoCandidato}")
    public FuturoCandidato atualizar(@PathVariable Integer idFuturoCandidato, @Valid @RequestBody FuturoCandidatoDTO dto) {
       FuturoCandidato atualizado = this.futuroCandidatoService.atualizar(idFuturoCandidato, dto);
        return atualizado;
    }

    @DeleteMapping("/futurocandidato/{idFuturoCandidato}")
    public void deletar(@PathVariable Integer idFuturoCandidato) {
        this.futuroCandidatoService.deletar(idFuturoCandidato);
    }
}



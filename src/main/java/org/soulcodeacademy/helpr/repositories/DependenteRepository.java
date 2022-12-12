package org.soulcodeacademy.helpr.repositories;

import org.soulcodeacademy.helpr.domain.Dependente;
import org.soulcodeacademy.helpr.domain.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DependenteRepository extends JpaRepository<Dependente, Integer> {

    List <Dependente> findByIdDependenteBetween(Double data1, Double data2); //Filtrar por data

    List<Dependente> findByCpf(String cpf); // Filtrar por cpf

    List<Dependente> findByEscolaridade(String escolaridade); //Filtrar por escolaridade

    List<Dependente> findByIdResponsavel(Funcionario responsavel);// Filtrar por responsável


}

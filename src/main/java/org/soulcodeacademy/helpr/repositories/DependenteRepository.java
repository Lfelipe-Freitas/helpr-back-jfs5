package org.soulcodeacademy.helpr.repositories;

import org.soulcodeacademy.helpr.domain.Dependente;
import org.soulcodeacademy.helpr.domain.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface DependenteRepository extends JpaRepository<Dependente, Integer> {

    public Dependente findByCpf(String cpf); // Filtrar por cpf

    List<Dependente> findByEscolaridade(String escolaridade); //Filtrar por escolaridade

    @Query(value = "SELECT * FROM dependente WHERE dataNasc BETWEEN :data1 AND :data2", nativeQuery = true)
    public List<Dependente> buscarEntreDatas(LocalDate data1, LocalDate data2);

    List<Dependente> findByFuncionarioId(Integer id);
}

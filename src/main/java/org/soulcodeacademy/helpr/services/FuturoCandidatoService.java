package org.soulcodeacademy.helpr.services;
import org.soulcodeacademy.helpr.domain.FuturoCandidato;
import org.soulcodeacademy.helpr.domain.dto.FuturoCandidatoDTO;
import org.soulcodeacademy.helpr.domain.enums.SetorFuturoCandidato;
import org.soulcodeacademy.helpr.repositories.FuturoCandidatoRepository;
import org.soulcodeacademy.helpr.services.errors.ParametrosInsuficientesError;
import org.soulcodeacademy.helpr.services.errors.RecursoNaoEncontradoError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;


    @Service // instanciar automaticamente minha classe
    public class FuturoCandidatoService {
        @Autowired
        private FuturoCandidatoRepository futuroCandidatoRepository;

        @Autowired
        private CargoService cargoService;

        public List<FuturoCandidato> listar() {
            return this.futuroCandidatoRepository.findAll();
        }



        public FuturoCandidato getfuturoCandidato(Integer IdFuturoCandidato) {
            // Optional = pode existir ou não a entidade
            Optional<FuturoCandidato> futuroCandidato = this.futuroCandidatoRepository.findById(IdFuturoCandidato);

            if (futuroCandidato.isEmpty()) {
                throw new RecursoNaoEncontradoError("O futuro candidato não foi encontrado!");
            } else {
                return futuroCandidato.get(); // pega o valor da entidade encontrada
            }
        }

        public FuturoCandidato salvar(FuturoCandidatoDTO dto) {

            FuturoCandidato futuroCandidato= new FuturoCandidato( null, dto.getNomeCompleto(), dto.getEmail(), dto.getDescricaoHabilidade(),dto.getSetor() );
          FuturoCandidato salvo = this.futuroCandidatoRepository.save(futuroCandidato);

            return salvo;
        }

        public FuturoCandidato atualizar(Integer idFuturoCandidato, FuturoCandidatoDTO dto) {
            // Busca o funcionario com o idFuncionario
          FuturoCandidato candidatoAtual = this.getfuturoCandidato(idFuturoCandidato);


            candidatoAtual.setNomeCompleto(dto.getNomeCompleto());
            candidatoAtual.setEmail(dto.getEmail());
            candidatoAtual.setDescricaoHabilidades(dto.getDescricaoHabilidade());


            if (dto.getIdFuturoCandidato() == null) {
                throw new ParametrosInsuficientesError("idFuturoCandidato é obrigatório");
            } else {


                switch (dto.getSetor()) { // Escolha o valor de getStatus()
                    case MARKETING -> {
                        candidatoAtual.setSetor(SetorFuturoCandidato.MARKETING);

                    }
                    case DESENVOLVIMENTO -> {
                        candidatoAtual.setSetor(SetorFuturoCandidato.DESENVOLVIMENTO);

                    }
                    case MANUTENCAO -> {
                        candidatoAtual.setSetor(SetorFuturoCandidato.MANUTENCAO);

                    }
                    case RECURSOS_HUMANOS -> {
                        candidatoAtual.setSetor(SetorFuturoCandidato.RECURSOS_HUMANOS);

                    }
                    case SUSTENTACAO -> {
                        candidatoAtual.setSetor(SetorFuturoCandidato.SUSTENTACAO);
                    }
            }

          FuturoCandidato atualizado = this.futuroCandidatoRepository.save(candidatoAtual);
            return atualizado;
        }



    }
        public void deletar (Integer IdFuturoCandidato){
            FuturoCandidato futuroCandidato = this.getfuturoCandidato(IdFuturoCandidato);
            this.futuroCandidatoRepository.delete(futuroCandidato);

        }


}

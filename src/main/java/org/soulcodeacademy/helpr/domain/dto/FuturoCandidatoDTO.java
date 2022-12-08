package org.soulcodeacademy.helpr.domain.dto;

import org.soulcodeacademy.helpr.domain.enums.SetorFuturoCandidato;

import javax.validation.constraints.NotNull;

public class FuturoCandidatoDTO extends UsuarioDTO{

    @NotNull(message = "idFuturoCandidato é obrigatório")
    private  Integer IdFuturoCandidato;

    @NotNull(message = "Descriçõ de Habilidade é obrigatória")
    private String descricaoHabilidade;

    @NotNull(message = "Setor é obrigatório ")
    private SetorFuturoCandidato setor ;

    @NotNull(message = "Nome é obrigatório")
    private String NomeCompleto;

    public Integer getIdFuturoCandidato() {
        return IdFuturoCandidato;
    }

    public void setIdFuturoCandidato(Integer idFuturoCandidato) {
        IdFuturoCandidato = idFuturoCandidato;
    }

    public String getDescricaoHabilidade() {
        return descricaoHabilidade;
    }

    public void setDescricaoHabilidade(String descricaoHabilidade) {
        this.descricaoHabilidade = descricaoHabilidade;
    }

    public SetorFuturoCandidato getSetor() {
        return setor;
    }

    public void setSetor(SetorFuturoCandidato setor) {
        this.setor = setor;
    }

    public String getNomeCompleto() {
        return NomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        NomeCompleto = nomeCompleto;
    }
}
 //Integer idFuturoCandidato, String nomeCompleto, String email, String descricaoHabilidades, SetorFuturoCandidato setor
package br.com.med.cia.api.medico;

import br.com.med.cia.api.endereco.DadosEndereco;

import javax.validation.constraints.NotNull;

public record DadosAtualizacaoMedico(
        @NotNull Long id,
        String nome,
        String telefone,
        DadosEndereco endereco) {
}

package br.com.med.cia.api.domain.paciente;

import br.com.med.cia.api.domain.endereco.DadosEndereco;
import javax.validation.constraints.NotNull;

public record DadosAtualizacaoPaciente(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco) {
}

package br.com.med.cia.api.domain.consulta.validacoes;

import br.com.med.cia.api.domain.consulta.DadosAgendamentoConsulta;

public interface ValidadorAgendamentoDeConsulta {

    void validar(DadosAgendamentoConsulta dados);
}

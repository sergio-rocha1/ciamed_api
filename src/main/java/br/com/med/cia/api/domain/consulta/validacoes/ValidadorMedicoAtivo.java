package br.com.med.cia.api.domain.consulta.validacoes;

import br.com.med.cia.api.domain.ValidacaoException;
import br.com.med.cia.api.domain.consulta.DadosAgendamentoConsulta;
import br.com.med.cia.api.domain.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoAtivo implements ValidadorAgendamentoDeConsulta{

    @Autowired
    private MedicoRepository repository;

    public void validar(DadosAgendamentoConsulta dados) {
        //escolha do medico opcional
        if(dados.idMedico() == null) {
            return;
        }

        Boolean medicoEstaAtivo = repository.findAtivoById(dados.idMedico());
        if(!medicoEstaAtivo) {
            throw new ValidacaoException("Consulta não pode ser agendada com médico excluido!");
        }
    }
}

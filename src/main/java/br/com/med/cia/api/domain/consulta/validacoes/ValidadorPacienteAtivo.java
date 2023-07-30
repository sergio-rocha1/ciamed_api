package br.com.med.cia.api.domain.consulta.validacoes;

import br.com.med.cia.api.domain.ValidacaoException;
import br.com.med.cia.api.domain.consulta.DadosAgendamentoConsulta;
import br.com.med.cia.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteAtivo implements ValidadorAgendamentoDeConsulta{

    @Autowired
    private PacienteRepository repository;

    public void validar(DadosAgendamentoConsulta dados) {

        Boolean pacienteEstaAtivo = repository.findAtivoById(dados.idPaciente());
        if(!pacienteEstaAtivo) {
            throw new ValidacaoException("Consulta não pode ser agendada com paciente excluido!");
        }
    }
}

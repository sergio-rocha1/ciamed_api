package br.com.med.cia.api.domain.consulta.validacoes;

import br.com.med.cia.api.domain.ValidacaoException;
import br.com.med.cia.api.domain.consulta.DadosAgendamentoConsulta;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Component
public class ValidadorHorarioFuncionamentoClinica implements ValidadorAgendamentoDeConsulta{

    public void validar(DadosAgendamentoConsulta dados) {

        LocalDateTime dataConsulta = dados.data();

        boolean domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        boolean antesDaAberturaClinica = dataConsulta.getHour() < 7;
        boolean depoisDoEncerramentoClinica = dataConsulta.getHour() > 18;

        if(domingo || antesDaAberturaClinica || depoisDoEncerramentoClinica){
            throw new ValidacaoException("Consulta fora do horário de funcioonamento da clinica");
        }
    }
}

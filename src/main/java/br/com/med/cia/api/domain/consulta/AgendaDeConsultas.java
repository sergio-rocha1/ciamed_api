package br.com.med.cia.api.domain.consulta;

import br.com.med.cia.api.domain.ValidacaoException;
import br.com.med.cia.api.domain.consulta.validacoes.ValidadorAgendamentoDeConsulta;
import br.com.med.cia.api.domain.medico.Medico;
import br.com.med.cia.api.domain.medico.MedicoRepository;
import br.com.med.cia.api.domain.paciente.Paciente;
import br.com.med.cia.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AgendaDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private List<ValidadorAgendamentoDeConsulta> validadores;

    public DadosDetalhamentoConsulta agendar(DadosAgendamentoConsulta dados) {
        if(!pacienteRepository.existsById(dados.idPaciente())){
            throw new ValidacaoException("ID do Paciente informado não existe!");
        }

        if(dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())){
            throw new ValidacaoException("ID do Medico informado não existe!");
        }

        validadores.forEach(v -> v.validar(dados));

        Medico medico = escolherMedico(dados);
        Paciente paciente = pacienteRepository.getById(dados.idPaciente());

        Consulta consulta = new Consulta(null, medico, paciente, dados.data(), null);
        consultaRepository.save(consulta);

        return new DadosDetalhamentoConsulta(consulta);
    }

    private Medico escolherMedico(DadosAgendamentoConsulta dados) {
        if(dados.idMedico() != null) {
            return medicoRepository.getById(dados.idMedico());
        }
        if(dados.especialidade() == null) {
            throw new ValidacaoException("Especialidade é obrigatória quando médico não for escolhido!");
        }

        List<Medico> medicos = medicoRepository.escolherMedicoAletorioLivreNaData(dados.especialidade(), dados.data());

        if(medicos.isEmpty()) {
            throw new ValidacaoException("Nenhum médico disponível para esse horário!");
        }

        // Obtenha o primeiro resultado
        return medicos.get(0);
    }

    public void cancelar(DadosCancelamentoConsulta dados){
        if(!consultaRepository.existsById(dados.idConsulta())){
            throw new ValidacaoException("ID da consulta informada não existe!");
        }

        Consulta consulta = consultaRepository.getById(dados.idConsulta());
        consulta.cancelar(dados.motivo());
    }
}

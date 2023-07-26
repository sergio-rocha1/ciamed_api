package br.com.med.cia.api.domain.consulta;

import br.com.med.cia.api.domain.ValidacaoException;
import br.com.med.cia.api.domain.medico.Medico;
import br.com.med.cia.api.domain.medico.MedicoRepository;
import br.com.med.cia.api.domain.paciente.Paciente;
import br.com.med.cia.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendaDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    public void agendar(DadosAgendamentoConsulta dados) {
        if(!pacienteRepository.existsById(dados.idPaciente())){
            throw new ValidacaoException("ID do Paciente informado não existe!");
        }

        if(dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())){
            throw new ValidacaoException("ID do Medico informado não existe!");
        }

        Medico medico = escolherMedico(dados);
        Paciente paciente = pacienteRepository.getById(dados.idPaciente());

        Consulta consulta = new Consulta(null, medico, paciente, dados.data());

        consultaRepository.save(consulta);
    }

    private Medico escolherMedico(DadosAgendamentoConsulta dados) {
        if(dados.idMedico() != null) {
            return medicoRepository.getById(dados.idMedico());
        }
        if(dados.especialidade() == null) {
            throw new ValidacaoException("Especialidade é obrigatória quando médico não for escolhido!");
        }
        return medicoRepository.escolherMedicoAletorioLivreNaData(dados.especialidade(), dados.data());
    }
}

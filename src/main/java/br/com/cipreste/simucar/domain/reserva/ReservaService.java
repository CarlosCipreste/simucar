package br.com.cipreste.simucar.domain.reserva;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cipreste.simucar.domain.cliente.ClienteModel;
import br.com.cipreste.simucar.domain.cliente.ClienteRepository;
import br.com.cipreste.simucar.domain.handlers.exceptions.ResourceAlreadyExistsException;
import br.com.cipreste.simucar.domain.handlers.exceptions.ResourceNotFoundException;
import br.com.cipreste.simucar.domain.veiculo.VeiculoModel;
import br.com.cipreste.simucar.domain.veiculo.VeiculoRepository;
import jakarta.transaction.Transactional;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private VeiculoRepository veiculoRepository;

    public List<ReservaResponseDTO> findAll() {
        List<ReservaModel> clientes = reservaRepository.findAll();
        List<ReservaResponseDTO> clientesDTO = clientes.stream()
                .map(ReservaResponseDTO::toDTO)
                .collect(Collectors.toList());
        return clientesDTO;
    }

    public ReservaModel findReservaById(Long id) {
        Optional<ReservaModel> reservaExists = reservaRepository.findById(id);

        if (reservaExists.isEmpty())
            throw new ResourceNotFoundException("Reserva", "Reserva não encontrada no id especificado");

        return reservaExists.get();
    }

    @Transactional
    public ReservaModel save(ReservaSaveDTO reserva) {
        Optional<ReservaModel> reservaExists = reservaRepository.findByClienteAndVeiculoAndPeriodo(
                reserva.clienteId(),
                reserva.veiculoId(),
                reserva.dataInicio(),
                reserva.dataFim());

        if (reservaExists.isPresent()) {
            throw new ResourceAlreadyExistsException("Reserva",
                    "Já existe uma reserva para este cliente e veículo no período selecionado.");
        } else {
            // Buscar o cliente e o veículo no banco de dados
            ClienteModel cliente = clienteRepository.findById(reserva.clienteId())
                    .orElseThrow(() -> new ResourceNotFoundException("Cliente", "id:"+reserva.clienteId()));
            VeiculoModel veiculo = veiculoRepository.findById(reserva.veiculoId())
                    .orElseThrow(() -> new ResourceNotFoundException("Veículo", "id:" + reserva.veiculoId()));

            // Criar a nova reserva
            ReservaModel newReserva = new ReservaModel(reserva);
            newReserva.setVeiculo(veiculo);
            newReserva.setCliente(cliente);

            return reservaRepository.save(newReserva);
        }
    }

    @Transactional
    public ReservaModel update(ReservaModel reserva) {

        Optional<ReservaModel> reservaExists = reservaRepository.findById(null);

        if (reservaExists.isEmpty())
            throw new ResourceNotFoundException("Reserva", "Reserva não encontrada no id especificado");

        ReservaModel existingReserva = reservaExists.get();
        existingReserva.setCarroDevolvido(reserva.isCarroDevolvido());
        existingReserva.setCarroPego(reserva.isCarroPego());
        existingReserva.setCliente(reserva.getCliente());
        existingReserva.setVeiculo(reserva.getVeiculo());
        existingReserva.setDataFim(reserva.getDataFim());
        existingReserva.setDataFim(reserva.getDataFim());

        return reservaRepository.save(existingReserva);

    }

}

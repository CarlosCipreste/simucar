package br.com.cipreste.simucar.domain.reserva;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("reservas")
public class ReservaController {
    
    @Autowired
    private ReservaService reservaService;

    @GetMapping
    public ResponseEntity<List<ReservaResponseDTO>> listReserva() {
        
        List<ReservaResponseDTO> reserva = reservaService.findAll();
        
        return ResponseEntity.ok().body(reserva);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ReservaResponseDTO> saveReserva(@RequestBody @Valid ReservaSaveDTO reserva, UriComponentsBuilder buider) {
        
        ReservaModel savedReserva = reservaService.save(reserva);
        URI uri = buider.path("reservas/{id}").buildAndExpand(savedReserva.getId()).toUri();
        ReservaResponseDTO reservaDTO = ReservaResponseDTO.toDTO(savedReserva);
        return ResponseEntity.created(uri).body(reservaDTO);

    }

}

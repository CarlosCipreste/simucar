package br.com.cipreste.simucar.domain.veiculo;

import br.com.cipreste.simucar.domain.enums.VehicleType;

public record VeiculoDTO(
    String marca,
    String modelo,
    VehicleType tipo,
    String numPlaca
) {
    
    public static VeiculoDTO toDTO(VeiculoModel veiculoModel){
        return new VeiculoDTO(veiculoModel.getMarca(), veiculoModel.getModelo(), veiculoModel.getTipo(), veiculoModel.getNumPlaca());
    }

}

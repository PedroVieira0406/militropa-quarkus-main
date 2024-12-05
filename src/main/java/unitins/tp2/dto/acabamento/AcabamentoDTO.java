package unitins.tp2.dto.acabamento;

import jakarta.validation.constraints.NotBlank;
import unitins.tp2.model.Acabamento;


public record AcabamentoDTO(
        @NotBlank(message = "insira o nome corretamente")
        String nome
        ) {
    public static AcabamentoDTO valueOf(Acabamento acabamento) {
        return new AcabamentoDTO(
            acabamento.getNome());
    }
}
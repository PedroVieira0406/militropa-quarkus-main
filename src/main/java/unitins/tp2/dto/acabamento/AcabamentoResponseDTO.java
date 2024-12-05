package unitins.tp2.dto.acabamento;

import unitins.tp2.model.Acabamento;

public record AcabamentoResponseDTO(
                Long id,
                String nome
                
) {
        public static AcabamentoResponseDTO valueOf(Acabamento acabamento) {
                return new AcabamentoResponseDTO(
                                acabamento.getId(),
                                acabamento.getNome()
                                );
        }
}
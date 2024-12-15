package unitins.tp2.dto.cliente;

import unitins.tp2.dto.endereco.EnderecoResponseDTO;
import unitins.tp2.dto.usuario.UsuarioResponseDTO;
import unitins.tp2.model.Cliente;

public record ClienteResponseDTO(
                Long id,
                String nome,
                String cpf,
                String email,
                String numeroRegistro_posse_porte,
                EnderecoResponseDTO endereco,
                UsuarioResponseDTO usuario) {

        public static ClienteResponseDTO valueOf(Cliente cliente) {
                return new ClienteResponseDTO(
                                cliente.getId(),
                                cliente.getNome(),
                                cliente.getCpf(),
                                cliente.getEmail(),
                                cliente.getNumeroRegistro_posse_porte(),
                                cliente.getEndereco() != null ? EnderecoResponseDTO.valueOf(cliente.getEndereco())
                                                : null,
                                UsuarioResponseDTO.valueOf(cliente.getUsuario()));
        }
}
package unitins.tp2.dto.cliente;

import unitins.tp2.dto.usuario.UsuarioResponseDTO;
import unitins.tp2.model.Cliente;

public record ClientePedidoDTO(
                Long id,
                String nome,
                String cpf,
                String numeroRegistro_posse_porte,
                UsuarioResponseDTO usuario) {

        public static ClientePedidoDTO valueOf(Cliente cliente) {
                return new ClientePedidoDTO(
                                cliente.getId(),
                                cliente.getNome(),
                                cliente.getCpf(),
                                cliente.getNumeroRegistroPossePorte(),
                                UsuarioResponseDTO.valueOf(cliente.getUsuario()));
        }
}
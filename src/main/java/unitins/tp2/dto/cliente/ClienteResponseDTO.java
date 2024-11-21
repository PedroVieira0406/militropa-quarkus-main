package unitins.tp2.dto.cliente;

import java.util.List;

import unitins.tp2.dto.endereco.EnderecoResponseDTO;
import unitins.tp2.dto.usuario.UsuarioResponseDTO;
import unitins.tp2.model.Cliente;

public record ClienteResponseDTO(
        Long id,
        String nome,
        String cpf,
        String email,
        String numeroRegistro_posse_porte,
        List<String> listaTelefones,
        List<EnderecoResponseDTO> enderecos,
        UsuarioResponseDTO usuario) {

    public static ClienteResponseDTO valueOf(Cliente cliente) {
        return new ClienteResponseDTO(
                cliente.getId(),
                cliente.getNome(),
                cliente.getCpf(),
                cliente.getEmail(),
                cliente.getNumeroRegistro_posse_porte(),
                cliente.getListaTelefones().stream()
                        .map(t -> String.valueOf(t)).toList(),
                cliente.getListaEnderecos().stream()
                        .map(e -> EnderecoResponseDTO.valueOf(e)).toList(),
                UsuarioResponseDTO.valueOf(cliente.getUsuario()));

    }
}
package unitins.tp2.dto.funcionario;

import unitins.tp2.dto.endereco.EnderecoResponseDTO;
import unitins.tp2.dto.usuario.UsuarioResponseDTO;
import unitins.tp2.model.Funcionario;

public record FuncionarioResponseDTO(
            Long id,
            String nome,
            String cpf,
            String email,
            String matricula,
            String telefone,
            EnderecoResponseDTO endereco,
            UsuarioResponseDTO usuario) {
                
        public static FuncionarioResponseDTO valueOf(Funcionario funcionario) {
            return new FuncionarioResponseDTO(
                    funcionario.getId(),
                    funcionario.getNome(),
                    funcionario.getCpf(),
                    funcionario.getEmail(),
                    funcionario.getMatricula(),
                    funcionario.getTelefone(),
                    EnderecoResponseDTO.valueOf(funcionario.getEndereco()),
                    UsuarioResponseDTO.valueOf(funcionario.getUsuario()));
    }
}

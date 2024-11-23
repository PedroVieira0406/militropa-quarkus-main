package unitins.tp2.dto.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public record UsuarioDTO(
        @NotBlank(message = "O campo login não pode estar em branco")
        @Size(min = 4,max =15, message = "O tamanho do login deve ser entre 4 e 15 caracteres.")
        String login,
        @NotBlank(message = "O campo senha não pode estar em branco")
        String senha,
        Integer perfil
) {
        
}

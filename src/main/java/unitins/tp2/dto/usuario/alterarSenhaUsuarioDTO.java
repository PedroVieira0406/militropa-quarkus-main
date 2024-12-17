package unitins.tp2.dto.usuario;

import jakarta.validation.constraints.NotBlank;

public record AlterarSenhaUsuarioDTO(

        @NotBlank(message = "O campo senha antiga não pode ser nulo.")
        String senhaAntiga,
        @NotBlank(message = "O campo senha não pode ser nulo.")
        String senhaNova
) {
}

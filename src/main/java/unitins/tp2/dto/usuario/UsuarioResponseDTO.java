package unitins.tp2.dto.usuario;

import unitins.tp2.model.Perfil;
import unitins.tp2.model.Usuario;

public record UsuarioResponseDTO(
        Long id,
        String login,
        String senha,
        Perfil perfil) {

    public static UsuarioResponseDTO valueOf(Usuario usuario) {
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getLogin(),
                usuario.getSenha(),
                usuario.getPerfil());
    }

    public Perfil getPerfil() {
        return perfil;
    }

}

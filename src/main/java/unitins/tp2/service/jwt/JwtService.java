package unitins.tp2.service.jwt;

import unitins.tp2.dto.usuario.AuthUsuarioDTO;
import unitins.tp2.dto.usuario.UsuarioResponseDTO;

public interface JwtService {
    
    public String generateJwt(AuthUsuarioDTO authDTO, UsuarioResponseDTO dto);
    
} 

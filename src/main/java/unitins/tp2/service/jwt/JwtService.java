package unitins.tp2.service.jwt;

import unitins.tp2.dto.usuario.UsuarioResponseDTO;

public interface JwtService {
    
    public String generateJwt(UsuarioResponseDTO dto);
    
} 

package unitins.tp2.service.jwt;

import java.time.Duration;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
import unitins.tp2.dto.usuario.AuthUsuarioDTO;
import unitins.tp2.dto.usuario.UsuarioResponseDTO;

@ApplicationScoped
public class JwtServiceImpl implements JwtService {

    private static final Duration EXPIRATION_TIME = Duration.ofDays(365);

    @Override
    public String generateJwt(AuthUsuarioDTO authDTO, UsuarioResponseDTO dto) {
        Instant now = Instant.now();
        Instant expiryDate = now.plus(EXPIRATION_TIME);

        Set<String> roles = new HashSet<String>();
    
        if(authDTO.perfil() == 1){
            roles.add("User");
        }else if (authDTO.perfil() == 2){
            roles.add("Admin");
        }

        return Jwt.issuer("unitins-jwt")
            .claim("id", dto.id())
            .subject(dto.login())
            .groups(roles)
            .expiresAt(expiryDate)
            .sign();
    }
    
}
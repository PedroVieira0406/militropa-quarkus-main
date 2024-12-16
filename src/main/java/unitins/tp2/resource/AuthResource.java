package unitins.tp2.resource;

import org.jboss.logging.Logger;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import unitins.tp2.dto.usuario.AuthUsuarioDTO;
import unitins.tp2.dto.usuario.UsuarioResponseDTO;
import unitins.tp2.service.cliente.ClienteService;
import unitins.tp2.service.funcionario.FuncionarioService;
import unitins.tp2.service.hash.HashService;
import unitins.tp2.service.jwt.JwtService;
import unitins.tp2.service.usuario.UsuarioService;
import unitins.tp2.validation.ValidationException;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/auth")
public class AuthResource {

    @Inject
    public UsuarioService usuarioService;

    @Inject
    public ClienteService cService;

    @Inject
    public FuncionarioService fService;

    @Inject
    public HashService hashService;

    @Inject
    public JwtService jwtService;

    private static final Logger LOG = Logger.getLogger(AuthResource.class);

    @POST
    public Response login(AuthUsuarioDTO dto) {
        try {
            String hashSenha = hashService.getHashSenha(dto.senha());

            UsuarioResponseDTO usuario = null;

            if (dto.perfil() == 1) {
                usuario = cService.login(dto.login(), hashSenha);
            } else if (dto.perfil() == 2) {
                usuario = fService.login(dto.login(), hashSenha);
            } else {
                return Response.status(Status.NOT_FOUND).header("Perfil", "perfis: 1-user ou 2-admin").build();
            }

            if (usuario != null) {
                return Response.ok(usuario).header("Authorization", jwtService.generateJwt(dto, usuario))
                        .status(Status.CREATED)
                        .build();
            } else {
                return Response.status(Status.NOT_FOUND).build();
            }
        } catch (Exception e) {
            LOG.error("Erro durante o login. Verifique seu login ou senha!");
            throw new ValidationException("Verificando",
                    "Erro durante o login. Verifique seu login ou senha! - Executando AuthResource_Login");
        }

    }
}
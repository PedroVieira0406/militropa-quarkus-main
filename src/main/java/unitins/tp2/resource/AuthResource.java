package unitins.tp2.resource;

import org.jboss.logging.Logger;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import unitins.tp2.dto.usuario.AuthUsuarioDTO;
import unitins.tp2.dto.usuario.UsuarioResponseDTO;
import unitins.tp2.service.cliente.ClienteService;
import unitins.tp2.service.funcionario.FuncionarioService;
import unitins.tp2.service.hash.HashService;
import unitins.tp2.service.jwt.JwtService;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/auth")
public class AuthResource {

    @Inject
    public FuncionarioService funcionarioService;

    @Inject
    public ClienteService clienteService;

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

            // Verifica o perfil para autenticar o usuário
            if (dto.perfil() == 2) {
                usuario = funcionarioService.login(dto.login(), hashSenha);
            } else if (dto.perfil() == 1) {
                usuario = clienteService.login(dto.login(), hashSenha);
            } else {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Perfil inválido. Utilize 1 para Funcionário ou 2 para Cliente.")
                        .build();
            }

            // Se o usuário foi autenticado com sucesso, retorna a resposta com o JWT
            if (usuario != null) {
                return Response.ok(usuario)
                        .header("Authorization", jwtService.generateJwt(dto, usuario))
                        .status(Response.Status.OK)
                        .build();
            } else {
                return Response.status(Response.Status.UNAUTHORIZED)
                        .entity("Login ou senha inválidos.")
                        .build();
            }
        } catch (Exception e) {
            LOG.error("Erro durante o login. Verifique seu login ou senha!", e);

            // Retorna um erro com uma mensagem mais clara sobre o que aconteceu
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro durante o processo de login. Tente novamente mais tarde.")
                    .build();
        }
    }
}


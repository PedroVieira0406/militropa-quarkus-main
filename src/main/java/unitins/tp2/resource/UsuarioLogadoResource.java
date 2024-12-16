package unitins.tp2.resource;

import org.eclipse.microprofile.jwt.JsonWebToken;
// import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import io.quarkus.logging.Log;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import unitins.tp2.dto.usuario.AlterarLoginUsuarioDTO;
import unitins.tp2.dto.usuario.AlterarSenhaUsuarioDTO;
import unitins.tp2.service.usuario.UsuarioService;

@Path("/usuariologado")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@APIResponse(responseCode = "403", description = "Você não tem permissão para acessar esse recurso.")
public class UsuarioLogadoResource {

    @Inject
    JsonWebToken jwt;

    @Inject
    UsuarioService usuarioService;

    @GET
//   @RolesAllowed({ "User", "Admin" })
    public Response getMeuUsuario() {

        // Obtendo o login pelo token jwt
        String login = jwt.getSubject();
        Log.info("Pegando o usuario logado string: " + login);
        Log.info("Pegando o usuário logado");
        return Response.ok(usuarioService.findByLogin(login)).build();
    }
    @PATCH
    @Path("/alterar-senha")
    @RolesAllowed({"User"})
    public Response alterarSenha(AlterarSenhaUsuarioDTO dto) {
        try {
            Log.info("Senha alterada com sucesso");
            usuarioService.alterarSenha(dto);
            return Response.status(Status.NO_CONTENT).build();
        } catch (Exception e) {
            Log.error("Erro ao tentar alterar senha");
            return Response.status(Status.NOT_FOUND).entity("Erro ao tentar alterar senha").build();
        }
    }

    @PATCH
    @RolesAllowed({"User"})
    @Path("/alterar-login")
    public Response alterarLogin(AlterarLoginUsuarioDTO dto) {
        try {
            Log.info("Login alterado com sucesso.");
            usuarioService.alterarLogin(dto);
            return Response.status(Status.NO_CONTENT).build();
        } catch (Exception e) {
            Log.error("Erro ao tentar alterar Login.", e);
            return Response.status(Status.NOT_FOUND).entity("Erro ao tentar alterar Login").build();
        }
    }

}

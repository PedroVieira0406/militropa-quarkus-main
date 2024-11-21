package unitins.tp2.resource;

import org.eclipse.microprofile.jwt.JsonWebToken;
// import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import unitins.tp2.dto.usuario.alterarLoginUsuarioDTO;
import unitins.tp2.dto.usuario.alterarSenhaUsuarioDTO;
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

    @Path("/usuariologado/alterarsenha")
    @PUT
//    @RolesAllowed({"User", "Admin"})
    public Response putInfos(alterarSenhaUsuarioDTO senhaUsuarioDTO){
        String login = jwt.getSubject();
        Log.info("Pegando o usuario logado string: " + login);
        Log.info("Alterando a senha do usuário logado");
        usuarioService.alterarSenha(senhaUsuarioDTO, login);
        return Response.noContent().build();
    }

    @Path("/usuariologado/alterarlogin")
    @PUT
//    @RolesAllowed({"User", "Admin"})
    public Response putInfos(alterarLoginUsuarioDTO loginUsuarioDTO){
        String login = jwt.getSubject();
        Log.info("Pegando o usuario logado string: " + login);
        Log.info("Alterando o login do usuário logado");
        usuarioService.alterarLogin(loginUsuarioDTO, login);
        return Response.noContent().build();
    }

}

package unitins.tp2.resource;

import org.eclipse.microprofile.jwt.JsonWebToken;

import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import unitins.tp2.dto.cliente.ClienteDTO;
import unitins.tp2.dto.cliente.ClienteResponseDTO;
import unitins.tp2.service.cliente.ClienteService;
import unitins.tp2.service.endereco.EnderecoServiceImpl;

@Path("/clientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClienteResource {

    @Inject
    ClienteService service;

    @Inject
    EnderecoServiceImpl enderecoImpl;

    @Inject
    JsonWebToken jwt;

    @POST
//    @RolesAllowed({"Admin"})
    public Response insert(ClienteDTO dto) {
        try {
            Log.info("Cadastrando uma cliente: " + dto.nome());
            ClienteResponseDTO responseDTO = service.insert(dto);
            return Response.status(Response.Status.CREATED).entity(responseDTO).build();
        } catch (Exception e) {
            Log.error("Erro ao cadastrar uma cliente: ", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao cadastrar a cliente.").build();
        }
    }

    @PUT
    @Transactional
//    @RolesAllowed({ "User"})
    @Path("/{id}")
    //public Response update(ClienteDTO dto, @PathParam("id") Long id) {
    public Response update(ClienteDTO dto) {
        String login = jwt.getSubject();
        Long idCliente = service.findByUsuario(login).id();
        Log.info("Atualizando dados de um cliente."+dto.nome());
        service.update(dto, idCliente);
        return Response.status(Status.NO_CONTENT).build();
    }
    
    @DELETE
    @Transactional
//    @RolesAllowed({ "Admin" })
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        Log.info("Deletando um cliente."+ id);
        service.delete(id);
        return Response.status(Status.NO_CONTENT).build();
    }

    @GET
//    @RolesAllowed({"User","Admin"})
    public Response findAll(@QueryParam("page") @DefaultValue("0") int page,
    @QueryParam("pageSize") @DefaultValue("100") int pageSize) {
    Log.info("Buscando todos os Clientes cadastrados.");
    return Response.ok(service.findAll(page, pageSize)).build();
    }

    @GET
//    @RolesAllowed({ "Admin" })
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        Log.info("Buscando um cliente expecificando o id."+ id);
        return Response.ok(service.findById(id)).build();
    }

    @GET
//    @RolesAllowed({ "Admin" })
    @Path("/search/nome/{nome}")
    public Response findByNome(@PathParam("nome") String nome) {
        Log.info("Buscando um cliente expecificando o nome." + nome);
        return Response.ok(service.findByNome(nome)).build();
    }

    @GET
//    @RolesAllowed({"User", "Admin"})
    @Path("/dados-pessoais")
    public Response findByDadosPessoais(){
        String login = jwt.getSubject();
        Log.info(login+" Verificando os dados pessoais."  );
        return Response.ok(service.findByUsuario(login)).build();
    }

    @GET
    @Path("/count")
    public Response count() {
        return Response.ok(service.count()).build();
    }
}

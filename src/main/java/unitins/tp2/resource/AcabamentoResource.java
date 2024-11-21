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
import unitins.tp2.dto.acabamento.AcabamentoDTO;
import unitins.tp2.dto.acabamento.AcabamentoResponseDTO;
import unitins.tp2.service.acabamento.AcabamentoService;

@Path("/acabamentos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AcabamentoResource {

    @Inject
    AcabamentoService service;

    @Inject
    JsonWebToken jwt;

    @Transactional
    @POST
//    @RolesAllowed({"Admin"})
    public Response insert(AcabamentoDTO dto) {
        try {
            
            AcabamentoResponseDTO responseDTO = service.insert(dto);
            return Response.status(Response.Status.CREATED).entity(responseDTO).build();
        } catch (Exception e) {
            Log.error("Erro ao cadastrar uma arma: ", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao cadastrar a arma.").build();
        }
    }

    @PUT
    @Transactional
    @Path("/{id}")
//    @RolesAllowed({"User","Admin"})
    public AcabamentoResponseDTO update(AcabamentoDTO dto, @PathParam("id") Long id) {
        Log.info("Atualizando um Acabamento: "+id);
        return service.update(dto, id);
    }

    @DELETE
    @Transactional
    @Path("/{id}")
//    @RolesAllowed({"User","Admin"})
    public void delete(@PathParam("id") Long id) {
        Log.info("Deletando um Acabamento:" +id);
        service.delete(id);
    }

    @GET
//    @RolesAllowed({"User","Admin"})
    public Response findAll(@QueryParam("page") @DefaultValue("0") int page,
    @QueryParam("pageSize") @DefaultValue("100") int pageSize) {
    Log.info("Buscando todos os Acabamentos cadastrados.");
    return Response.ok(service.findAll(page, pageSize)).build();
    }

    @GET
    @Path("/{id}")
//    @RolesAllowed({"Admin"})
    public Response findById(@PathParam("id") Long id){
        Log.info("Buscando um acabamento expecificado pelo id: "+id);
        return Response.ok(service.findById(id)).build();
    }

    @GET
    @Path("/search/nome/{nome}")
//    @RolesAllowed({"User","Admin"})
    public Response findByNome(@PathParam("nome") String nome){
        Log.info("Buscando um endereco expecificado pelo nome: "+nome);
        return Response.ok(service.findByNome(nome)).build();
    }

    @GET
    @Path("/count")
    public Response count() {
        return Response.ok(service.count()).build();
    }
}

package unitins.tp2.resource;

import java.util.List;

import org.jboss.logging.Logger;

import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
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
import unitins.tp2.dto.funcionario.FuncionarioDTO;
import unitins.tp2.dto.funcionario.FuncionarioResponseDTO;
import unitins.tp2.service.endereco.EnderecoServiceImpl;
import unitins.tp2.service.funcionario.FuncionarioService;

@Path("/funcionarios")    
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FuncionarioResource {
    @Inject
    FuncionarioService service;
    
    @Inject
    EnderecoServiceImpl enderecoImpl;

    private static final Logger LOG = Logger.getLogger(FuncionarioResource.class);

    @POST
//    @RolesAllowed({"Admin"})
    public Response insert(@Valid FuncionarioDTO dto){
        LOG.info("Cadastrando um funcionario");
        FuncionarioResponseDTO retorno = service.insert(dto);
        return Response.status(201).entity(retorno).build();
    }

    @PUT
    @Transactional
//    @RolesAllowed({"Admin"})
    @Path("/{id}")
    public Response update (FuncionarioDTO dto, @PathParam("id") Long id) {
        LOG.info("Atulizando um funcionario");
        service.update(dto, id);
        return Response.status(Status.NO_CONTENT).build();
    }

    @DELETE
    @Transactional
//    @RolesAllowed({"Admin"})
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id){
        LOG.info("Deletando um funcionario");
        service.delete(id);
        return Response.status(Status.NO_CONTENT).build();
    }


    @GET
//    @RolesAllowed({"Admin"})
    @Path("/search/matricula/{matricula}")
    public Response findByMatricula(@PathParam("matricula") String matricula) {
        LOG.info("Buscando um funcionario expecifiando a matricula.");
        List<FuncionarioResponseDTO> Funcionarios = service.findByMatricula(matricula);
        return Response.ok(Funcionarios).build();
    }


    @GET
//    @RolesAllowed({"User","Admin"})
    public Response findAll(@QueryParam("page") @DefaultValue("0") int page,
    @QueryParam("pageSize") @DefaultValue("100") int pageSize) {
    Log.info("Buscando todos os Funcionarios cadastrados.");
    return Response.ok(service.findAll(page, pageSize)).build();
    }

    @GET
//    @RolesAllowed({"Admin"})
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id){
        LOG.info("Buscando um funcionario expecifiando o id.");
        return Response.ok(service.findById(id)).build();
    }

    @GET
//    @RolesAllowed({"Admin"})
    @Path("/search/nome/{nome}")
    public Response findByNome(@PathParam("nome") String nome){
        LOG.info("Buscando um funcionario expecifiando o nome.");
        return Response.ok(service.findByNome(nome)).build();
    }

    @GET
    @Path("/count")
    public Response count() {
        return Response.ok(service.count()).build();
    }
}

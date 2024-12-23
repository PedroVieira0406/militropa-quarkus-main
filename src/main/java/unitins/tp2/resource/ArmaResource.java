package unitins.tp2.resource;

import java.io.IOException;

import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;
import jakarta.ws.rs.core.Response.Status;
import unitins.tp2.dto.arma.ArmaDTO;
import unitins.tp2.dto.arma.ArmaResponseDTO;
import unitins.tp2.form.ImageForm;
import unitins.tp2.model.TipoArma;
import unitins.tp2.service.arma.ArmaFileServiceImpl;
import unitins.tp2.service.arma.ArmaService;

@Path("/armas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ArmaResource {

    @Inject
    ArmaFileServiceImpl fileService;

    @Inject
    ArmaService service;

    
    @Transactional
    @POST
//    @RolesAllowed({"Admin"})
    public Response insert(ArmaDTO dto) {
        try {
            Log.info("Cadastrando uma arma: " + dto.getNome());
            ArmaResponseDTO responseDTO = service.insert(dto);
            return Response.status(Response.Status.CREATED).entity(responseDTO).build();
        } catch (Exception e) {
            Log.error("Erro ao cadastrar uma arma: ", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao cadastrar a arma.").build();
        }
    }

    @PUT
    @Transactional
    @Path("/{id}")
//    @RolesAllowed({"Admin"})
    public Response update(ArmaDTO dto, @PathParam("id") Long id) {
        try {
            Log.info("Atualizando dados da arma: " + dto.getNome());
            ArmaResponseDTO updatedDTO = service.update(dto, id);
            return Response.ok(updatedDTO).build();
        } catch (Exception e) {
            Log.error("Erro ao atualizar a arma: ", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao atualizar a arma.").build();
        }
    }

    @DELETE
    @Transactional
    @Path("/{id}")
//    @RolesAllowed({"Admin"})
    public Response delete(@PathParam("id") Long id) {
        try {
            Log.info("Deletando a arma com ID: " + id);
            service.delete(id);
            return Response.noContent().build();
        } catch (Exception e) {
            Log.error("Erro ao deletar a arma: ", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao deletar a arma.").build();
        }
    }

    @GET
//    @RolesAllowed({"User","Admin"})
    public Response findAll(@QueryParam("page") @DefaultValue("0") int page,
    @QueryParam("pageSize") @DefaultValue("100") int pageSize) {
    Log.info("Buscando todos as Armas cadastradas.");
    return Response.ok(service.findAll(page, pageSize)).build();
    }

    @GET
    @Path("/{id}")
//    @RolesAllowed({"Admin"})
    public Response findById(@PathParam("id") Long id) {
        try {
            Log.info("Buscando arma pelo ID: " + id);
            return Response.ok(service.findById(id)).build();
        } catch (Exception e) {
            Log.error("Erro ao buscar a arma pelo ID: ", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao buscar a arma.").build();
        }
    }

    @GET
    @Path("/image/download/{nomeImagem}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response download(@PathParam("nomeImagem") String nomeImagem) {
        ResponseBuilder response = Response.ok(fileService.download(nomeImagem));
        response.header("Content-Disposition", "attachment;filename=" + nomeImagem);
        return response.build();
    }

    @PATCH
    @Path("/image/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response salvarImagem(@MultipartForm ImageForm form) {

        try {
            fileService.salvar(form.getId(), form.getNomeImagem(), form.getImagem());
            return Response.noContent().build();
        } catch (IOException e) {
            return Response.status(Status.CONFLICT).build();
        }

    }

    @GET
    @Path("/count")
    public Response count() {
        return Response.ok(service.count()).build();
    }

    @GET
    @Path("/search/nome/{nome}")
//    @RolesAllowed({"User", "Admin"})
    public Response findByNome(@QueryParam("page") @DefaultValue("0") int page,
    @QueryParam("pageSize") @DefaultValue("100") int pageSize, @PathParam("nome") String nome) {
        Log.info("Busca de um arma especificado pelo nome.");
        return Response.ok(service.findByNome(page, pageSize, nome)).build();
    }

    @GET
    @Path("/count/search/{nome}")
    public Response countByNome(@PathParam("nome") String nome) {
        return Response.ok(service.countByNome(nome)).build();
    }

    @GET
    @Path("/tipoArmas")
    public Response getTipoArma() {
        return Response.ok(TipoArma.values()).build();
    }
}

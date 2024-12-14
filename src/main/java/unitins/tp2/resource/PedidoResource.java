package unitins.tp2.resource;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;

import io.quarkus.logging.Log;
import io.quarkus.security.identity.SecurityIdentity;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import unitins.tp2.dto.pedido.PedidoDTO;
import unitins.tp2.model.FormaDePagamento;
import unitins.tp2.service.cliente.ClienteService;
import unitins.tp2.service.pedido.PedidoService;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/pedidos")
public class PedidoResource {

    private static final Logger LOG = Logger.getLogger(PedidoResource.class);

    @Inject
    public PedidoService pedidoService;

    @Inject
    SecurityIdentity securityIdentity;

    @Inject
    JsonWebToken jwt;

    @Inject
    ClienteService clienteService;

    @POST
//    @RolesAllowed({ "User"})
    public Response insert(@Valid PedidoDTO dto) {
        LOG.info("Executando criação de pedido");
        String login = jwt.getSubject();
        Long idCliente = clienteService.findByUsuario(login).id();

        return Response.status(Status.CREATED).entity(pedidoService.insert(dto, idCliente)).build();
    }

    @GET
//    @RolesAllowed({"User","Admin"})   
    public Response findAll(@QueryParam("page") @DefaultValue("0") int page,
    @QueryParam("pageSize") @DefaultValue("100") int pageSize) {
    Log.info("Buscando todos os Pedidos cadastrados.");
    return Response.ok(pedidoService.findAll(page, pageSize)).build();
    }

    @GET
    @Path("/{id}")
//    @RolesAllowed({"Admin" })
    public Response findById(@PathParam("id") Long id) {
        LOG.infof("Executando o findById");
        return Response.ok(pedidoService.findById(id)).build();
    }

    @GET
    @Path("/search/cliente/{id}")
//    @RolesAllowed({ "User", "Admin" })
    public Response findByCliente(@PathParam("id") Long idCliente) {
        return Response.ok(pedidoService.findByCliente(idCliente)).build();
    }

    @PATCH
    @Path("/alterarStatusPagamento/{idPedido}")
//    @RolesAllowed({ "User" })
    public Response alterarStatusPagamento(@PathParam("idPedido") Long idPedido) {
        LOG.info("Executando alteração de status de pagamento");
        pedidoService.alterarStatusPagamento(idPedido);
        return Response.noContent().build();
    }

    @GET
    @Path("/meusPedidos")
//    @RolesAllowed({"User"})
    public Response meusPedidos(){
        LOG.info("Executando o método meusPedidos() de pedido. ");
        try {
            return Response.ok(pedidoService.meusPedidos()).build();
        } catch (NotFoundException e) {
            return Response.status(Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/count")
    public Response count() {
        return Response.ok(pedidoService.count()).build();
    }

        @DELETE
    @Transactional
    @Path("/{id}")
//    @RolesAllowed({"Admin"})
    public Response delete(@PathParam("id") Long id) {
        try {
            Log.info("Deletando o pedido com ID: " + id);
            pedidoService.delete(id);
            return Response.noContent().build();
        } catch (Exception e) {
            Log.error("Erro ao deletar a pedido: ", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao deletar o pedido.").build();
        }
    }

    @GET
    @Path("/formaPagamento")
    public Response getFormaPagamento() {
        return Response.ok(FormaDePagamento.values()).build();
    }
}

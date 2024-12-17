package unitins.tp2.resource;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;

import java.util.List;


import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import unitins.tp2.dto.pedido.PedidoDTO;
import unitins.tp2.dto.pedido.PedidoResponseDTO;
import unitins.tp2.model.Cliente;
import unitins.tp2.model.Pedido;
import unitins.tp2.repository.ClienteRepository;
import unitins.tp2.repository.PedidoRepository;
import unitins.tp2.service.cliente.ClienteService;
import unitins.tp2.service.pedido.PedidoService;
import unitins.tp2.validation.ValidationException;
import jakarta.ws.rs.WebApplicationException;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/pedidos")
public class PedidoResource {

    @Inject
    public PedidoService pedidoservice;

    @Inject
    public ClienteService clienteservice;

    @Inject
    ClienteRepository clienteRepository;

    @Inject
    PedidoRepository pedidoRepository;

    @Inject
    JsonWebToken tokenJwt;

    private static final Logger LOG = Logger.getLogger(PedidoResource.class);

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        LOG.info("Buscando com id - Executando PedidoResource_FindById");
        return Response.ok(pedidoservice.findById(id)).build();
    }

    @GET
    public Response findAll() {
        LOG.info("Buscando todos os pedidos - Executando PedidoResource_FindAll");
        return Response.ok(pedidoservice.findAll()).build();
    }

    @GET
    @Path("/search/clientes/{idCliente}")
    public Response findByCliente(@PathParam("idCliente") Long idCliente) {
        LOG.info("Buscando com id do cliente - Executando PedidoResource_FindByCliente");
        return Response.ok(pedidoservice.findByCliente(idCliente)).build();
    }

    @POST
    @RolesAllowed({ "User" })
    public Response create(@Valid PedidoDTO dto) {

        try {
            String login = tokenJwt.getName();

            if (!pedidoservice.clienteAutenticado(login, dto.idCliente()))
                throw new ValidationException("Valificando cliente para criacao de pedido",
                        "você não tem permissão para realizar pedido. - Executando PedidoResource_create");

            LOG.info("Executando PedidoResource_create");
            return Response.status(Status.CREATED).entity(pedidoservice.create(dto)).build();
        } catch (Exception e) {
            LOG.error("Erro ao criar pedido - Executando PedidoResource_create", e);
            return Response.status(Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/search/cancelar-Pedido")
    @RolesAllowed({ "User" })
    public Response cancelarPedido() {

        try {
            String login = tokenJwt.getName();
            Cliente cliente = clienteRepository.findByLogin(login);

            if (!pedidoservice.clienteAutenticado(login, cliente.getId()))
                throw new ValidationException("Validando cliente para cancelamento de pedido",
                        "Você não tem permissão para cancelar pedido. - Executando PedidoResource_cancelarPedido");

            pedidoservice.cancelarPedido(cliente.getId());
            LOG.info("Pedido cancelada com sucesso. - Executando PedidoResource_cancelarPedido");
            return Response.status(Status.NO_CONTENT).build();
        } catch (Exception e) {
            LOG.error("Erro ao cancelar compra. - Executando PedidoResource_cancelarPedido", e);
            return Response.status(Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/search/meus-Pedidos")
    @RolesAllowed({ "User" })
    public Response meusPedidos() {
        try {
            LOG.info("Meus Pedido. - Executando PedidoResource_meusPedidos");
            String login = tokenJwt.getName();
            Cliente cliente = clienteRepository.findByLogin(login);

            if (!pedidoservice.clienteAutenticado(login, cliente.getId()))
                throw new ValidationException("Validando cliente para visualizar pedidos",
                        "Você não tem permissão para ver os pedidos");

            return Response.ok(pedidoservice.meusPedidos()).build(); // Correção aqui
        } catch (Exception e) {
            LOG.error("Erro ao visualizar meus pedidos", e);
            return Response.status(Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @PATCH
    @Path("/search/pagar-Boleto")
    @RolesAllowed({ "User" })
    public Response pagarBoleto() {

        try {

            String login = tokenJwt.getName();
            Cliente cliente = clienteRepository.findByLogin(login);

            if (!pedidoservice.clienteAutenticado(login, cliente.getId()))
                throw new ValidationException("ValidandoPagamento",
                        "Você não tem permissão para realizar pagamento via boleto de pedido. - Executando PedidoResource_pagarBoleto");

            pedidoservice.pagamentoBoleto(cliente.getId());

            LOG.info("Pagamento com boleto realizado com sucesso. - Executando PedidoResource_pagarBoleto");
            return Response.status(Status.ACCEPTED).build();
        } catch (Exception e) {
            LOG.error("Erro ao realizar pagamento com boleto - Executando PedidoResource_pagarBoleto");
            return Response.status(Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @PATCH
    @Path("/search/pagar-Pix")
    @RolesAllowed({ "User" })
    public Response pagarPix() {
        try {
            String login = tokenJwt.getName();
            Cliente cliente = clienteRepository.findByLogin(login);

            if (!pedidoservice.clienteAutenticado(login, cliente.getId()))
                throw new ValidationException("ValidandoPagamento",
                        "Você não tem permissão para realizar pagamento via pix de pedido. - Executando PedidoResource_pagarPix");

            pedidoservice.pagamentoPix(cliente.getId());

            LOG.info("Pagamento do pix realizado com sucesso. - Executando PedidoResource_pagarPix");
            return Response.status(Status.ACCEPTED).build();
        } catch (Exception e) {
            LOG.error("Erro ao realizar pagamento com pix - Executando PedidoResource_pagarPix");
            return Response.status(Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/carrinho/{idCliente}")
    public PedidoResponseDTO getCarrinho(@PathParam("idCliente") Long idCliente) {
        Cliente cliente = clienteRepository.findById(idCliente);
        Pedido pedido = pedidoRepository.findByClienteNaoFinalizado(cliente);
        if (pedido == null) {
            throw new WebApplicationException("Nenhum carrinho encontrado para este cliente.", 404);
        }
        return PedidoResponseDTO.valueOf(pedido);
    }

    @GET
    @Path("/pedidos-realizados/{idCliente}")
    public List<PedidoResponseDTO> getPedidosRealizados(@PathParam("idCliente") Long idCliente) {
        Cliente cliente = clienteRepository.findById(idCliente);
        List<Pedido> pedidos = pedidoRepository.findByClienteFinalizado(cliente);
        if (pedidos.isEmpty()) {
            throw new WebApplicationException("Nenhum pedido realizado encontrado para este cliente.", 404);
        }
        return pedidos.stream()
                .map(PedidoResponseDTO::valueOf)
                .toList();
    }
}
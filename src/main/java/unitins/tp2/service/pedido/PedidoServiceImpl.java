package unitins.tp2.service.pedido;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.microprofile.jwt.JsonWebToken;

import io.quarkus.security.identity.SecurityIdentity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import unitins.tp2.dto.itemPedido.ItemPedidoDTO;
import unitins.tp2.dto.pedido.PedidoDTO;
import unitins.tp2.dto.pedido.PedidoResponseDTO;
import unitins.tp2.model.Arma;
import unitins.tp2.model.Cliente;
import unitins.tp2.model.ItemPedido;
import unitins.tp2.model.Pedido;
import unitins.tp2.model.Boleto;
import unitins.tp2.model.Pix;
import unitins.tp2.repository.ArmaRepository;
import unitins.tp2.repository.ClienteRepository;
import unitins.tp2.repository.PedidoRepository;
import unitins.tp2.repository.PixRepository;
import unitins.tp2.repository.BoletoRepository;
import unitins.tp2.repository.ItemPedidoRepository;
import unitins.tp2.validation.ValidationException;

@ApplicationScoped
public class PedidoServiceImpl implements PedidoService {

    @Inject
    public PedidoRepository pedidoRepository;

    @Inject
    public ItemPedidoRepository itemPedidoRepository;

    @Inject
    public ClienteRepository clienteRepository;

    @Inject
    public ArmaRepository armaRepository;

    @Inject
    public BoletoRepository boletoRepository;

    @Inject
    public PixRepository pixRepository;

    @Inject
    SecurityIdentity securityIdentity;

    @Inject
    JsonWebToken tokenJwt;

    @Override
    @Transactional
    public PedidoResponseDTO create(@Valid PedidoDTO dto) {
        String username = securityIdentity.getPrincipal().getName();

        Cliente cliente = clienteRepository.findById(dto.idCliente());
        if (cliente == null) {
            throw new ValidationException("Buscando Cliente",
                    "Cliente não encontrado - Executando PedidoServiceImpl_create");
        }

        if (!clienteAutenticado(username, dto.idCliente())) {
            throw new ValidationException("Verificando...",
                    "Você não tem autorização para realizar o pedido. - Executando PedidoServiceImpl_create");
        }

        Pedido pedidoExistente = pedidoRepository.findByClienteNaoFinalizado(cliente);
        if (pedidoExistente != null) {
            throw new ValidationException("Buscando Pedido",
                    "Já existe um pedido em aberto. Pague seu ultimo pedido ou delete para fazer um novo. - Executando PedidoServiceImpl_create");
        }

        Pedido pedido = new Pedido();

        pedido.setCliente(cliente);
        pedido.setDataPedido(LocalDateTime.now());

        List<ItemPedido> itens = new ArrayList<>();
        Double valorTotal = 0.0;

        for (ItemPedidoDTO itemDTO : dto.itens()) {
            ItemPedido item = new ItemPedido();
            item.setQuantidade(itemDTO.quantidade());

            if (itemDTO.idArma() != null) {
                Arma arma = armaRepository.findById(itemDTO.idArma());
                if (arma == null) {
                    throw new ValidationException("Buscando Arma",
                            "Arma não encontrado - Executando Pedido Create em PedidoServiceIMPL");
                }
                if (item.getQuantidade() > arma.getQtdNoEstoque()) {
                    throw new ValidationException("Verificando Estoque", "Arma não tem estoque suficiente");
                }

                item.setArma(arma);
                item.setSubTotal((arma.getPreco() - calcularDesconto(item)) * item.getQuantidade());
            }

            itens.add(item);

            valorTotal += calcularValorTotal(item);
        }

        pedido.setItens(itens);
        pedido.setValorTotal(valorTotal);

        pedidoRepository.persist(pedido);
        return PedidoResponseDTO.valueOf(pedido);
    }

    private Double calcularValorTotal(ItemPedido item) {
        if (item.getArma() != null) {
            return (item.getArma().getPreco() - calcularDesconto(item)) * item.getQuantidade();
        }
        throw new ValidationException("calcularValorTotal", "Não há Arma em Item");
    }

    private Double calcularDesconto(ItemPedido item) {
        Double desconto = 0.0;
        if (item.getArma() != null) {
            if (item.getQuantidade() >= 3) {
                desconto = (item.getArma().getPreco() * 0.10);
            }
        } else {
            throw new ValidationException("calcularDesconto",
                    "Não há Arma para calcular o desconto");
        }
        return desconto;
    }

    @Override
    @Transactional
    public void cancelarPedido(Long idCliente) {
        Pedido pedido = pedidoRepository.findByClienteNaoFinalizado(clienteRepository.findById(idCliente));
        if (pedido == null)
            throw new ValidationException("cancelarPedido", "Não há nenhuma compra em andamento");
        for (ItemPedido itemPedido : pedido.getItens()) {
            itemPedidoRepository.delete(itemPedido);
            if (itemPedido.getArma() != null) {
                itemPedido.getArma().aumentandoEstoque(itemPedido.getQuantidade());
            }
        }
        pedidoRepository.delete(pedido);
    }

    @Override
    public void finalizarPedido(Long idPedido) throws NullPointerException {
        Pedido pedido = pedidoRepository.findById(idPedido);
        if (pedido == null)
            throw new ValidationException("finalizarPedido", "Não há nenhuma compra em andamento");
        if (pedido.getItens().size() == 0)
            throw new ValidationException("finalizarPedido", "Não há nenhum item dentro do carrinho");
        pedido.setDataPedido(LocalDateTime.now());
        for (ItemPedido itemPedido : pedido.getItens()) {
            if (itemPedido.getArma() != null) {
                if (itemPedido.getArma().getQtdNoEstoque() < itemPedido.getQuantidade()) {
                    throw new ValidationException("finalizarPedido",
                            "quantidade de arma em estoque insuficiente para a quantidade requisitada. Não é possível finalizar a compra");
                } else {
                    itemPedido.getArma().diminuindoEstoque(itemPedido.getQuantidade());
                }
            }
        }
        pedido.setIfPedidoFeito(true);
    }

    @Override
    @Transactional
    public List<PedidoResponseDTO> meusPedidos() {
        String login = tokenJwt.getName();
        List<PedidoResponseDTO> pedidos = pedidoRepository.find("cliente.usuario.login", login).stream()
                .map(e -> PedidoResponseDTO.valueOf(e)).toList();

        if (pedidos.isEmpty()) {
            throw new ValidationException("Meus pedidos",
                    "Você ainda não fez nenhum pedido. - Executando PedidoServiceImpl_meusPedidos");
        }

        return pedidos;
    }

    @Override
    public PedidoResponseDTO findById(Long id) {
        return PedidoResponseDTO.valueOf(pedidoRepository.findById(id));
    }

    @Override
    @Transactional
    public void pagamentoBoleto(Long idCliente) {
        Cliente cliente = clienteRepository.findById(idCliente);
        Pedido pedido = validar(cliente);
        Boleto pagamento = new Boleto(pedido.getValorTotal(), pedido.getCliente().getUsuario().getLogin(),
                pedido.getCliente().getCpf());
        boletoRepository.persist(pagamento);
        pedido.setFormaPagamento(pagamento);
        if (pedido.getFormaPagamento() == null) {
            throw new ValidationException("PagamentoBoleto",
                    "Não foi efetuado nenhum pagamento - Executando PedidoServiceImpl_pagamentoBoleto");
        }
        finalizarPedido(pedido.getId());
    }

    @Override
    @Transactional
    public void pagamentoPix(Long idCliente) {
        Cliente cliente = clienteRepository.findById(idCliente);
        Pedido pedido = validar(cliente);
        Pix pagamento = new Pix(pedido.getValorTotal(), pedido.getCliente().getUsuario().getLogin(),
                pedido.getCliente().getCpf());
        pixRepository.persist(pagamento);
        pedido.setFormaPagamento(pagamento);
        if (pedido.getFormaPagamento() == null) {
            throw new ValidationException("PagamentoPix",
                    "Não foi efetuado nenhum pagamento - Executando PedidoServiceImpl_pagamentoPix");
        }
        finalizarPedido(pedido.getId());
    }

    private Pedido validar(Cliente cliente) {

        Pedido pedido = pedidoRepository.findByClienteNaoFinalizado(cliente);

        if (pedido == null) {
            throw new ValidationException("ValidandoCliente", "Não há nenhuma pedido em andamento");
        }
        if (pedido.getItens().size() == 0) {
            throw new ValidationException("ValidandoCliente", "Não há nenhum item dentro do carrinho");
        }
        return pedido;
    }

    @Override
    public List<PedidoResponseDTO> findAll() {

        return pedidoRepository
                .listAll()
                .stream()
                .map(e -> PedidoResponseDTO.valueOf(e)).toList();
    }

    public boolean clienteAutenticado(String login, Long idCliente) {
        Cliente clienteAutenticado = clienteRepository.findByLogin(login);
        return clienteAutenticado != null && clienteAutenticado.getId().equals(idCliente);
    }

    @Override
    public List<PedidoResponseDTO> findByCliente(Long idCliente) {
        return pedidoRepository.findByCliente(idCliente).stream().map(e -> PedidoResponseDTO.valueOf(e)).toList();
    }

}
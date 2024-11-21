package unitins.tp2.dto.pedido;
import java.util.List;

import unitins.tp2.dto.itemPedido.ItemPedidoDTO;

    public record PedidoDTO (
    List<ItemPedidoDTO> itens,
    Integer idFormaDePagamento)
{ }
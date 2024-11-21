package unitins.tp2.dto.pedido;

import java.time.LocalDateTime;
import java.util.List;

import unitins.tp2.dto.cliente.ClientePedidoDTO;
import unitins.tp2.dto.itemPedido.ItemPedidoResponseDTO;
import unitins.tp2.model.FormaDePagamento;
import unitins.tp2.model.Pedido;
import unitins.tp2.model.Status;

public record PedidoResponseDTO(
        Long idPedido,
        ClientePedidoDTO cliente,
        LocalDateTime data,
        Double totalPedido,
        FormaDePagamento pagamento,
        Status status,
        List<ItemPedidoResponseDTO> itens


) {
    public static PedidoResponseDTO valueOf(Pedido pedido) {
        List<ItemPedidoResponseDTO> lista = pedido.getItens()
                                                        .stream()
                                                                .map(ItemPedidoResponseDTO::valueOf)
                                                                        .toList();
                return new PedidoResponseDTO(
                        pedido.getId(), 
                        ClientePedidoDTO.valueOf(pedido.getCliente()),
                        pedido.getDataHora(),
                        pedido.getTotalPedido(),
                        pedido.getFormaDePagamento(),
                        pedido.getStatus(),
                        lista
                );  
    }
}

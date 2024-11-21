package unitins.tp2.dto.itemPedido;

import unitins.tp2.model.ItemPedido;

public record ItemPedidoResponseDTO(
    String nome,
    Integer quantidade,
    Long idArma
) { 
    public static ItemPedidoResponseDTO valueOf(ItemPedido item){
        return new ItemPedidoResponseDTO(
            item.getArma().getNome(),
            item.getQuantidade(), 
            item.getArma().getId());
    }
}
package unitins.tp2.service.pedido;

import java.util.List;

import jakarta.validation.Valid;
import unitins.tp2.dto.pedido.PedidoDTO;
import unitins.tp2.dto.pedido.PedidoResponseDTO;

public interface PedidoService {

    public List<PedidoResponseDTO> findAll();
    public PedidoResponseDTO findById(Long id);
    public List<PedidoResponseDTO> findByCliente(Long idCliente);
    public PedidoResponseDTO create(@Valid PedidoDTO dto);
    public void cancelarPedido(Long idCliente);
    public void finalizarPedido(Long idPedido);
    public void pagamentoBoleto(Long idCliente);
    public void pagamentoPix(Long idCliente);
    boolean clienteAutenticado(String login, Long idCliente);
    public List<PedidoResponseDTO> meusPedidos();
}
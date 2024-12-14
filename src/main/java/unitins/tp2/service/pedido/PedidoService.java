package unitins.tp2.service.pedido;

import java.util.List;

import jakarta.validation.Valid;
import unitins.tp2.dto.pedido.PedidoDTO;
import unitins.tp2.dto.pedido.PedidoResponseDTO;

public interface PedidoService {

        public PedidoResponseDTO insert(@Valid PedidoDTO dto, Long idCliente);
        public PedidoResponseDTO findById(Long id);
        public List<PedidoResponseDTO> findAll(int page, int pageSize);
        public List<PedidoResponseDTO> findByCliente(Long idCliente);
        public void delete(Long id);
        public void alterarStatusPagamento(Long id);
    
        public List<PedidoResponseDTO> meusPedidos();

        public long count();
    
    }
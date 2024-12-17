package unitins.tp2.repository;

import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import unitins.tp2.model.Cliente;
import unitins.tp2.model.Pedido;

@ApplicationScoped
public class PedidoRepository implements PanacheRepository<Pedido> {

    public List<Pedido> findByCliente(Long idCliente) {
        return find("cliente.id", idCliente).list();
    }
 
    public Pedido findByClienteNaoFinalizado(Cliente cliente) {
        if (cliente == null) {
            return null;
        }

        return find("FROM Pedido p WHERE p.cliente = ?1 AND p.ifPedidoFeito = false", cliente).firstResult();
    }

    public List<Pedido> findByClienteFinalizado(Cliente cliente) {
        if (cliente == null) {
            return null;
        }

        return find("FROM Pedido p WHERE p.cliente = ?1 AND p.ifPedidoFeito = true", cliente).list();
    }

    public List<Pedido> findAllWithItens() {
        return find("SELECT p FROM Pedido p LEFT JOIN FETCH p.itens").list();
    }

    public Pedido findByIdWithItens(Long id) {
        return find("SELECT p FROM Pedido p LEFT JOIN FETCH p.itens WHERE p.id = ?1", id).firstResult();
    }
}
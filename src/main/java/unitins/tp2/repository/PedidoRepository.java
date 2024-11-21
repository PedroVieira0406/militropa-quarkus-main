package unitins.tp2.repository;

import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import unitins.tp2.model.Pedido;

@ApplicationScoped
public class PedidoRepository implements PanacheRepository<Pedido> {   
    public List<Pedido> findByCliente (Long clienteId){
        return find("cliente.id", clienteId).list();
    }
}
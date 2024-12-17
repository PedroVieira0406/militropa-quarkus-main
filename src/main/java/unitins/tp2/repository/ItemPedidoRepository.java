package unitins.tp2.repository;

import unitins.tp2.model.Arma;
import unitins.tp2.model.ItemPedido;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ItemPedidoRepository implements PanacheRepository<ItemPedido>  {
    public ItemPedido findByArma (Arma arma) {

        if (arma == null)
            return null;

        return find("FROM ItemPedido WHERE arma = ?1", arma).firstResult();
    }
}
package unitins.tp2.repository;

import jakarta.enterprise.context.ApplicationScoped;
import unitins.tp2.model.Boleto;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class BoletoRepository implements PanacheRepository<Boleto>{
    
}
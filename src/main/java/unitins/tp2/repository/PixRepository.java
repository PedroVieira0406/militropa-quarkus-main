package unitins.tp2.repository;

import jakarta.enterprise.context.ApplicationScoped;
import unitins.tp2.model.Pix;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class PixRepository implements PanacheRepository<Pix>{
    
}
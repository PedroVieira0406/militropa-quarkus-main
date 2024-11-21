package unitins.tp2.repository;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.NoResultException;
import unitins.tp2.model.Arma;

@ApplicationScoped 
public class ArmaRepository implements PanacheRepository<Arma>{
    public PanacheQuery<Arma> findByNome (String nome) {
        if(nome == null)
            return null;
        return find("UPPER (nome) LIKE?1","%" + nome.toUpperCase()+ "%") ;
    }

    public Arma findById(Long id) {
        try {
            return find("id = ?1", id).singleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }
}

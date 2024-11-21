package unitins.tp2.repository;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.NoResultException;
import unitins.tp2.model.Produto;

@ApplicationScoped 
public class ProdutoRepository implements PanacheRepository<Produto>{
public PanacheQuery<Produto> findByNome (String nome) {
        if(nome == null)
            return null;
        return find("UPPER (nome) LIKE?1","%" + nome.toUpperCase()+ "%") ;
    }

    public Produto findById(Long id) {
        try {
            return find("id = ?1", id).singleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }
}
